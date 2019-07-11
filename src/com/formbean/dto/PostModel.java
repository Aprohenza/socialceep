package com.formbean.dto;

import java.util.List;

import com.formbean.entity.PostCommentEntity;

public class PostModel {
	
	private Long postId;
	
	private String postAuthorId;
	
	private String postAuthorName;
	
	private String postAuthorRole;
	
	private String postAuthorPhoto;	

	private String postDate;
	
	private String postBody;
	
	private AttachmentDto postAttachment;
	
	/**
	 * @return the postAuthorPhoto
	 */
	public String getPostAuthorPhoto() {
		return postAuthorPhoto;
	}

	/**
	 * @param postAuthorPhoto the postAuthorPhoto to set
	 */
	public void setPostAuthorPhoto(String postAuthorPhoto) {
		this.postAuthorPhoto = postAuthorPhoto;
	}
	
	/**
	 * @return the postAttachment
	 */
	public AttachmentDto getPostAttachment() {
		return postAttachment;
	}

	/**
	 * @param postAttachment the postAttachment to set
	 */
	public void setPostAttachment(AttachmentDto postAttachment) {
		this.postAttachment = postAttachment;
	}

	private List<PostCommentEntity> postComments;

	/**
	 * @return the postComments
	 */
	public List<PostCommentEntity> getPostComments() {
		return postComments;
	}

	/**
	 * @param postComments the postComments to set
	 */
	public void setPostComments(List<PostCommentEntity> postComments) {
		this.postComments = postComments;
	}

	/**
	 * 
	 */
	public PostModel() {
	}

	/**
	 * @param postAuthorName
	 * @param postAuthorRole
	 * @param postDate
	 * @param postBody
	 */
	public PostModel(Long postId, String postAuthorId, String postAuthorName, String postAuthorRole, String postDate, String postBody, String postAuthorPhoto) {
		this.postId = postId;
		this.postAuthorId = postAuthorId;
		this.postAuthorName = postAuthorName;
		this.postAuthorRole = postAuthorRole;
		this.postDate = postDate;
		this.postBody = postBody;
		this.postAuthorPhoto = postAuthorPhoto;
	}

	/**
	 * @return the postAuthorName
	 */
	public String getPostAuthorName() {
		return postAuthorName;
	}

	/**
	 * @param postAuthorName the postAuthorName to set
	 */
	public void setPostAuthorName(String postAuthorName) {
		this.postAuthorName = postAuthorName;
	}

	/**
	 * @return the postAuthorRole
	 */
	public String getPostAuthorRole() {
		return postAuthorRole;
	}

	/**
	 * @param postAuthorRole the postAuthorRole to set
	 */
	public void setPostAuthorRole(String postAuthorRole) {
		this.postAuthorRole = postAuthorRole;
	}

	/**
	 * @return the postDate
	 */
	public String getPostDate() {
		return postDate;
	}

	/**
	 * @param postDate the postDate to set
	 */
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	/**
	 * @return the postBody
	 */
	public String getPostBody() {
		return postBody;
	}

	/**
	 * @param postBody the postBody to set
	 */
	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

	/**
	 * @return the postAuthorId
	 */
	public String getPostAuthorId() {
		return postAuthorId;
	}

	/**
	 * @param postAuthorId the postAuthorId to set
	 */
	public void setPostAuthorId(String postAuthorId) {
		this.postAuthorId = postAuthorId;
	}

	/**
	 * @return the postId
	 */
	public Long getPostId() {
		return postId;
	}

	/**
	 * @param postId the postId to set
	 */
	public void setPostId(Long postId) {
		this.postId = postId;
	}

	
	
	
	
}
