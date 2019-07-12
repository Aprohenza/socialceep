package com.socialceep.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.socialceep.dto.AttachmentDto;
import com.socialceep.dto.PostDto;
import com.socialceep.entity.AttachmentEntity;
import com.socialceep.entity.PostCommentEntity;
import com.socialceep.entity.PostEntity;
import com.socialceep.entity.UserEntity;
import com.socialceep.form.PostModelForm;
import com.socialceep.session.PostToFeedUserSessionLoad;
import com.socialceep.session.SessionManager;
import com.socialceep.session.UserOwnPost;
import com.socialceep.session.UserOwnPostSession;
import com.socialceep.session.UserOwnPostSessionLoad;
import com.socialceep.session.UserSession;

@Controller
public class PostController {
	
	private UserSession uSession;

	@RequestMapping(value = "/post-feed/load", method = RequestMethod.GET)
	public ResponseEntity<Void> loadPost(ModelMap model, HttpServletRequest request) {
		
		this.uSession = (UserSession) request.getSession().getAttribute("uSession");
		
		// carga de post feed de la session
		Runnable postToFeedUserSessionLoad = new PostToFeedUserSessionLoad(this.uSession, 1, 10);
		new Thread(postToFeedUserSessionLoad).start();

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/post/insert", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> insertNewPost(MultipartHttpServletRequest request, HttpServletRequest req)
			throws IOException {
		
		this.uSession = (UserSession) request.getSession().getAttribute("uSession");
		
		System.out.println("Publicando new post.");
		String postBody = req.getParameter("postBody");

		List<MultipartFile> attachmentFile = request.getFiles("file");
		List<MultipartFile> attachmentImage = request.getFiles("attachment");

		System.out.println("Cantidad de files: " + attachmentFile.size());
		System.out.println("Cantidad de img: " + attachmentImage.size());

		System.out.println(postBody);
		System.out.println(attachmentFile);
		System.out.println(attachmentImage);

		// si hay post body o algun fichero o alguna foto
		if ((postBody != null && !postBody.equals("")) || attachmentFile.size() != 0 || attachmentImage.size() != 0) {

			System.out.println("ESTOY DENTRO PARA GUARDAR POST!!!");

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
			EntityManager entitymanager = emfactory.createEntityManager();

			AttachmentEntity att;

			File targetFile;

			Long postId = System.currentTimeMillis();

			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			PostEntity postEntity = new PostEntity();
			
			postEntity.setPostAuthor(uSession.getUserProfileEntity());
			postEntity.setPostBody(postBody);
			postEntity.setPostDate(DATE_FORMAT.format(new Date()));
			postEntity.setPostDateLastComment(DATE_FORMAT.format(new Date()));
			postEntity.setPostId(postId);
			if(attachmentFile.size() != 0 || attachmentImage.size() != 0)
				postEntity.setPostAttachment(true);
			else
				postEntity.setPostAttachment(false);

			// meter a la bd
			entitymanager.getTransaction().begin();

			entitymanager.persist(postEntity);

			// guradar imagen
			if (attachmentFile.size() != 0) {

				InputStream fileContent;
				Long attachmentFileCode = postId;

				// mover el file a su sitio
				for (MultipartFile f : attachmentFile) {

					System.out.println("Nombre: " + f.getOriginalFilename());
					System.out.println("Content-type: " + f.getContentType());
					System.out.println("Tamanio: " + f.getSize());

					fileContent = f.getInputStream();
					targetFile = new File(request.getContextPath() + "/files/" + attachmentFileCode);

					System.out.println("targetFile:" + targetFile);

					if (!targetFile.exists())
						targetFile.mkdirs();

					Files.copy(fileContent, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

					IOUtils.closeQuietly(fileContent);

					att = new AttachmentEntity();
					att.setAttachmentFileName(f.getOriginalFilename()); // nombre file
					att.setAttachmentFileCode(Long.toString(attachmentFileCode)); // cod file
					att.setAttachmentPost(Long.toString(postId)); // cod post
					att.setAttachmentContentType(f.getContentType()); // content type file
					att.setAttachmentFilePath("files"); // file path

					// meter a la bd

					entitymanager.persist(att);

					attachmentFileCode++;
				}

			}

			// guardar imagen
			if (attachmentImage.size() != 0) {

				InputStream fileContent;
				Long attachmentFileCode = postId;

				// mover el file a su sitio
				for (MultipartFile f : attachmentImage) {

					System.out.println("Nombre: " + f.getOriginalFilename());
					System.out.println("Content-type: " + f.getContentType());
					System.out.println("Tamanio: " + f.getSize());

					fileContent = f.getInputStream();
					targetFile = new File(request.getContextPath() + "/images/" + attachmentFileCode);

					System.out.println("targetFile:" + targetFile);

					if (!targetFile.exists())
						targetFile.mkdirs();

					Files.copy(fileContent, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

					IOUtils.closeQuietly(fileContent);

					att = new AttachmentEntity();
					att.setAttachmentFileName(f.getOriginalFilename()); // nombre file
					att.setAttachmentFileCode(Long.toString(attachmentFileCode)); // cod file
					att.setAttachmentPost(Long.toString(postId)); // cod post
					att.setAttachmentContentType(f.getContentType()); // content type file
					att.setAttachmentFilePath("images"); // file path

					// meter a la bd

					entitymanager.persist(att);

					attachmentFileCode++;
				}

			}

			entitymanager.getTransaction().commit();

			entitymanager.close();
			emfactory.close();
			
			
			UserOwnPost post = new UserOwnPost();
			//post.setCommentPostDto(commentsPost);
			post.setPostId(postId);
			post.setPostAuthorId(uSession.getUserProfileId());
			post.setPostAuthorName(uSession.getUserProfileName() + " " + uSession.getUserProfileLastName());
			post.setPostAuthorRole(uSession.getUserProfileRole());
			post.setPostBody(postBody);
			post.setPostDate(DATE_FORMAT.format(new Date()));
			// pm.setPostAuthorPhoto(SessionManager.getUserSession(request).getUserProfilePhoto());
			post.setPostAuthorPhoto(uSession.getUserProfilePhotoProfile());
			if(attachmentFile.size() != 0)
				post.setPostAttachment(new AttachmentDto(Long.toString(postId), attachmentFile.get(0).getOriginalFilename(), "files", attachmentFile.get(0).getContentType()));
			
			if(attachmentImage.size() != 0)
				post.setPostAttachment(new AttachmentDto(Long.toString(postId), attachmentImage.get(0).getOriginalFilename(), "images", attachmentImage.get(0).getContentType()));
			
			MyWebScket.sendNotificationToSessionFriends(post, "newPost", uSession.getUserProfileId(), uSession);

		}

		/*
		 * SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 * PostModel pm = new PostModel(); //
		 * pm.setPostAuthorName(SessionManager.getUserSession(request).
		 * getUserProfileName() // + " " +
		 * SessionManager.getUserSession(request).getUserProfileLastName());
		 * pm.setPostAuthorName(uSession.getUserProfileName() + " " +
		 * uSession.getUserProfileLastName()); //
		 * pm.setPostAuthorRole(SessionManager.getUserSession(request).
		 * getUserProfileRole()); pm.setPostAuthorRole(uSession.getUserProfileRole());
		 * pm.setPostBody(postBody); pm.setPostDate(DATE_FORMAT.format(new Date()));
		 * 
		 * postModel.add(0, pm);
		 */
		
		

		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/post/action/like", method = RequestMethod.POST)
	public ResponseEntity<Void> likeToPost(HttpServletRequest request) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		String postId = request.getParameter("postId");
		
		PostEntity postEntity = entitymanager.find(PostEntity.class, Long.parseLong(postId));
		
		
		entitymanager.getTransaction().begin();
		
		postEntity.setPostLike(postEntity.getPostLike() + 1);
		
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

}
