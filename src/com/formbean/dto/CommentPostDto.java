package com.formbean.dto;

import com.formbean.dao.UserDAO;
import com.formbean.entity.PostCommentEntity;
import com.formbean.entity.UserEntity;

public class CommentPostDto {
	
	private String commentBody;
	
	private int commentId;
	
	private Long commentDate;
	
	private Long commentPostId;
	
	private String commentAuthorId;
	
	private String commentAuthorName;
	
	private String commentAuthorLastName;
	
	private Long commentAuthorPhotoProfile;
	
	private String commentAuthorRole;

	public CommentPostDto(String commentBody, int commentId, Long commentDate, Long commentPostId,
			String commentAuthorId, String commentAuthorName, String commentAuthorLastName,
			Long commentAuthorPhotoProfile, String commentAuthorRole) {
		super();
		this.commentBody = commentBody;
		this.commentId = commentId;
		this.commentDate = commentDate;
		this.commentPostId = commentPostId;
		this.commentAuthorId = commentAuthorId;
		this.commentAuthorName = commentAuthorName;
		this.commentAuthorLastName = commentAuthorLastName;
		this.commentAuthorPhotoProfile = commentAuthorPhotoProfile;
		this.commentAuthorRole = commentAuthorRole;
	}
	
	// constructor construye comentario a partir de PostCommentEntity object
	public CommentPostDto(PostCommentEntity postCommentEntity) {
		
		this.commentBody = postCommentEntity.getPostcommentBody();
		this.commentId = postCommentEntity.getPostcommentId();
		this.commentDate = postCommentEntity.getPostcommentPostDate();
		this.commentPostId = postCommentEntity.getPostcommentPostId();
		this.commentAuthorId = postCommentEntity.getPostcommentPostAuthor();
		
		//seteo de las propiedades del author del comentario
		UserEntity commentAuthor = UserDAO.getUserById(this.commentAuthorId);
		this.commentAuthorName = commentAuthor.getUserName();
		this.commentAuthorLastName = commentAuthor.getUserLastname();
		this.commentAuthorPhotoProfile = commentAuthor.getUserPhotoProfile();
		this.commentAuthorRole = commentAuthor.getUserRole().getRole().getRoleName();
		
	}

	public String getCommentBody() {
		return commentBody;
	}

	public void setCommentBody(String commentBody) {
		this.commentBody = commentBody;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Long getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Long commentDate) {
		this.commentDate = commentDate;
	}

	public Long getCommentPostId() {
		return commentPostId;
	}

	public void setCommentPostId(Long commentPostId) {
		this.commentPostId = commentPostId;
	}

	public String getCommentAuthorId() {
		return commentAuthorId;
	}

	public void setCommentAuthorId(String commentAuthorId) {
		this.commentAuthorId = commentAuthorId;
	}

	public String getCommentAuthorName() {
		return commentAuthorName;
	}

	public void setCommentAuthorName(String commentAuthorName) {
		this.commentAuthorName = commentAuthorName;
	}

	public String getCommentAuthorLastName() {
		return commentAuthorLastName;
	}

	public void setCommentAuthorLastName(String commentAuthorLastName) {
		this.commentAuthorLastName = commentAuthorLastName;
	}

	public Long getCommentAuthorPhotoProfile() {
		return commentAuthorPhotoProfile;
	}

	public void setCommentAuthorPhotoProfile(Long commentAuthorPhotoProfile) {
		this.commentAuthorPhotoProfile = commentAuthorPhotoProfile;
	}

	public String getCommentAuthorRole() {
		return commentAuthorRole;
	}

	public void setCommentAuthorRole(String commentAuthorRole) {
		this.commentAuthorRole = commentAuthorRole;
	}
	
	

}
