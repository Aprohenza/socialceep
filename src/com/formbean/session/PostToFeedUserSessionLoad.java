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

import com.formbean.controller.MyWebScket;
import com.formbean.dao.UserDao;
import com.formbean.dto.AttachmentDto;
import com.formbean.dto.CommentPostDto;
import com.formbean.entity.AttachmentEntity;
import com.formbean.entity.PostCommentEntity;
import com.formbean.entity.PostEntity;
import com.formbean.entity.UserEntity;

public class PostToFeedUserSessionLoad implements Runnable {

	private UserSession uSession;
	private int pageNumber;
	private int pageSize;
	
	
	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public PostToFeedUserSessionLoad(UserSession uSession, int pageNumber, int pageSize) {
		this.uSession = uSession;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	private List<PostToFeedUserSession> getPostToFeedUserSession() {
		
		List<PostToFeedUserSession> postToFeedUserSession = new ArrayList<PostToFeedUserSession>();

		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<PostEntity> queryPostEntity = entitymanager.createNamedQuery("PostEntity.findFeedPostSession",
				PostEntity.class);

		List<UserEntity> listUserEntityFriendsSession = new ArrayList<UserEntity>();

		for (UserFriendsSession uFs : uSession.getUserFriendsSession()) {
			UserEntity uE = UserDao.getUserById(uFs.getUserProfileId());
			listUserEntityFriendsSession.add(uE);
		}

		queryPostEntity.setParameter("uSession", entitymanager.find(UserEntity.class, uSession.getUserProfileId()));
		queryPostEntity.setParameter("friendsSession", listUserEntityFriendsSession);
		queryPostEntity.setFirstResult((this.pageNumber - 1) * this.pageSize);
		queryPostEntity.setMaxResults(this.pageSize);

		List<PostEntity> postEntityList = queryPostEntity.getResultList();

		for (PostEntity pE : postEntityList) {
			
			PostToFeedUserSession post = new PostToFeedUserSession();

			// comentarios del post pE
			TypedQuery<PostCommentEntity> query = entitymanager.createNamedQuery("PostCommentEntity.findCommentByPost", PostCommentEntity.class);
			query.setParameter("postcommentPostId", pE.getPostId());

			List<PostCommentEntity> postComments = query.getResultList();


			List<CommentPostDto> commentsPost = new ArrayList<CommentPostDto>();

			for (PostCommentEntity cp : postComments) {
				CommentPostDto cpdto = new CommentPostDto(cp);
				commentsPost.add(cpdto);
			}

			

			post.setCommentPostDto(commentsPost);
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

				}

			}

			postToFeedUserSession.add(post);
		}

		return postToFeedUserSession;

	}

	@Override
	public void run() {
		
		List<PostToFeedUserSession> postToSendToClient = this.getPostToFeedUserSession();
		
		//Collections.reverse(postToSendToClient);
		System.out.println("INICIALIZADO BEAN DE POST FEED CON:" + postToSendToClient + " DE POSTS");
		MyWebScket.sendMessageToClient(postToSendToClient, "posts", uSession.getUserProfileId());

	}

}
