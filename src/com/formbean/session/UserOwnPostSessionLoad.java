package com.formbean.session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


import org.springframework.stereotype.Component;

import com.formbean.controller.MyWebScket;
import com.formbean.dao.UserDao;
import com.formbean.dto.AttachmentDto;
import com.formbean.dto.CommentPostDto;
import com.formbean.entity.AttachmentEntity;
import com.formbean.entity.PostCommentEntity;
import com.formbean.entity.PostEntity;
import com.formbean.entity.UserEntity;


public class UserOwnPostSessionLoad implements Runnable {
	
	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
	EntityManager entitymanager = emfactory.createEntityManager();
	
	List<PostEntity> postEntityList = new ArrayList<>();
	
	private UserSession uSession;

	
	public UserOwnPostSessionLoad() {}
	

	public UserOwnPostSessionLoad(UserSession uSession) {
		this.uSession = uSession;
	}

	@Override
	public void run() {
		List<UserOwnPostSession> userOwnPostSession = new ArrayList<>();
		
		System.out.println("uSession en post load: " + uSession);
		
		//TypedQuery<PostEntity> queryPostEntity = entitymanager.createNamedQuery("PostEntity.findOwnPostSession", PostEntity.class);
		
		//queryPostEntity.setParameter("postAuthor", entitymanager.find(UserEntity.class, uSession.getUserProfileId()));
		
		TypedQuery<PostEntity> queryPostEntity = entitymanager.createNamedQuery("PostEntity.findFeedPostSession", PostEntity.class);
		queryPostEntity.setParameter("uSession", entitymanager.find(UserEntity.class, uSession.getUserProfileId()));
		
		List<UserEntity> listUserEntityFriendsSession = new ArrayList<UserEntity>();
		
		for(UserFriendsSession uFs: uSession.getUserFriendsSession()) {
			UserEntity uE = UserDao.getUserById(uFs.getUserProfileId());
			listUserEntityFriendsSession.add(uE);
		}
		
		queryPostEntity.setParameter("friendsSession", listUserEntityFriendsSession);
		
		postEntityList = queryPostEntity.getResultList();
		
		

		//userEntity = entitymanager.find(UserEntity.class, uSession.getUserProfileId());

		//postEntity = userEntity.getPostEntities();

		for (PostEntity pE : postEntityList) {
			
			//comentarios
			TypedQuery<PostCommentEntity> query = entitymanager.createNamedQuery("PostCommentEntity.findCommentByPost", PostCommentEntity.class);
			query.setParameter("postcommentPostId", pE.getPostId());

			List<PostCommentEntity> postComments = query.getResultList();

			//System.out.println("POST ID PIDIENDO COMMENTS: " + pE.getPostId());
			//System.out.println("COMMENTS: " + postComments.toString());
			
			List<CommentPostDto> commentsPost = new ArrayList<CommentPostDto>();
			
			for(PostCommentEntity cp: postComments) {
				CommentPostDto cpdto = new CommentPostDto(cp);
				commentsPost.add(cpdto);				
			}

			UserOwnPostSession post = new UserOwnPostSession();
			
			post.setCommentstPostDto(commentsPost);
			post.setPostId(pE.getPostId());
			post.setPostAuthorId(pE.getPostAuthor().getUserId());
			post.setPostAuthorName(pE.getPostAuthor().getUserName() + " " + pE.getPostAuthor().getUserLastname());
			post.setPostAuthorRole(pE.getPostAuthor().getUserRole().getRole().getRoleName());
			post.setPostBody(pE.getPostBody());
			post.setPostDate(DATE_FORMAT.format(new Date()));
			post.setPostAuthorPhoto(Long.toString(pE.getPostAuthor().getUserPhotoProfile()));
			post.setPostLike(pE.getPostLike()); // likes del post

			System.out.println("TIENE ATTACHMENT: " + pE.isPostAttachment());
			// tiene attachment el post ??
			if (pE.isPostAttachment()) {

				TypedQuery<AttachmentEntity> tq = entitymanager
						.createNamedQuery("AttachmentEntity.findAttachmentCodeByPostId", AttachmentEntity.class);
				tq.setParameter("attachmentPost", Long.toString(pE.getPostId()));

				try {
					List<AttachmentEntity> att = tq.getResultList();
					if (att.size() == 1) {
						AttachmentDto attDto = new AttachmentDto();
						attDto.setAttachmentCode(att.get(0).getAttachmentFileCode());
						attDto.setAttachmentContentType(att.get(0).getAttachmentContentType());
						attDto.setAttachmentPath(att.get(0).getAttachmentFilePath());
						attDto.setAttachmentName(att.get(0).getAttachmentFileName());

						post.setPostAttachment(attDto);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				

			}

			userOwnPostSession.add(post);
		}
		
		uSession.setUserOwnPosts(userOwnPostSession);
		
		Collections.reverse(uSession.getUserOwnPosts());
		System.out.println("INICIALIZADO BEAN DE POST CON:" + uSession.getUserOwnPosts() + " DE POSTS");
		MyWebScket.sendMessageToClient(uSession.getUserOwnPosts(), "posts", uSession.getUserProfileId());

	}

}
