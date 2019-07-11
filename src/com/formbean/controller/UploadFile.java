package com.formbean.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.jsp.PageContext;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import com.formbean.entity.AttachmentEntity;
import com.formbean.entity.PostEntity;
import com.formbean.session.SessionManager;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
@MultipartConfig
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadFile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		AttachmentEntity att = null;

		ServletContext app = request.getServletContext();
		System.out.println(request.getServletContext().getRealPath("/images/"));

		Part filePartImage = request.getPart("attachment");
		Part filePartFile = request.getPart("file");
		String postBody = request.getParameter("postBody");

		Long attachmentPost = System.currentTimeMillis();

		String attachmentFileCode = Long.toString(System.currentTimeMillis());

		System.out.println("FILE PART IMAGE: " + filePartImage);
		System.out.println("FILE PART FILE: " + filePartFile);
		System.out.println("POST BODY: " + postBody);

		System.out.println(filePartImage.getSubmittedFileName());
		System.out.println(filePartFile.getSubmittedFileName());

		if (filePartImage.getSubmittedFileName() != null || filePartFile.getSubmittedFileName() != null) {

			String fileName = null;
			Part filePart = null;
			String attachmentFileContentType = null;
			File targetFile = null;

			if (filePartImage.getSubmittedFileName() != null) {
				
				filePart = filePartImage;

				fileName = Paths.get(filePartImage.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
				
				attachmentFileContentType = filePartFile.getContentType();
				
				targetFile = new File(request.getContextPath() + "/" + attachmentFileCode);

				System.out.println("content type: " + filePartImage.getContentType());
				System.out.println(fileName);

			}

			if (filePartFile.getSubmittedFileName() != null) {
				
				filePart = filePartFile;

				fileName = Paths.get(filePartFile.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
				
				attachmentFileContentType = filePartFile.getContentType();
				
				targetFile = new File(request.getContextPath() + "/files/" + attachmentFileCode);

				System.out.println("content type: " + filePartFile.getContentType());
				System.out.println(fileName);

			}
			
			InputStream fileContent = filePart.getInputStream();
			
			

			att = new AttachmentEntity();
			att.setAttachmentFileName(fileName);
			att.setAttachmentFileCode(attachmentFileCode);
			att.setAttachmentPost(Long.toString(attachmentPost));
			att.setAttachmentContentType(attachmentFileContentType);

			

			System.out.println(targetFile);

			if (!targetFile.exists())
				targetFile.mkdirs();

			Files.copy(fileContent, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

			IOUtils.closeQuietly(fileContent);

		}
		
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		PostEntity postEntity = new PostEntity();
		postEntity.setPostAuthor(SessionManager.getUserSession(request).getUserProfileEntity());
		postEntity.setPostBody(postBody);
		postEntity.setPostDate(DATE_FORMAT.format(new Date()));
		postEntity.setPostDateLastComment(DATE_FORMAT.format(new Date()));
		postEntity.setPostId(attachmentPost);

		entitymanager.getTransaction().begin();
		
		if(att != null) {
			entitymanager.persist(att);
			entitymanager.persist(postEntity);
		}else {
			entitymanager.persist(postEntity);
		}

		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();

		

		
	}

}
