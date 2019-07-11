package com.formbean.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * The persistent class for the POST database table.
 * 
 */
@Entity
@Table(name = "POST")
@NamedQueries({
	@NamedQuery(name = "PostEntity.findAll", query = "SELECT p FROM PostEntity p"),
	@NamedQuery(name = "PostEntity.findLastPostOnFeedUserSession", query = "SELECT p FROM PostEntity p  WHERE p.postAuthor=:postAuthor ORDER BY p.postId DESC"),
	@NamedQuery(name = "PostEntity.findOwnPostSession", query = "SELECT p FROM PostEntity p WHERE p.postAuthor=:postAuthor"),
	@NamedQuery(name = "PostEntity.findFeedPostSession", query = "SELECT p FROM PostEntity p WHERE p.postAuthor=:uSession OR p.postAuthor IN :friendsSession ORDER BY p.postId DESC")
})

public class PostEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "post_id")
	private Long postId;
	
	@Column(name = "post_attachment")
	private boolean postAttachment;
	
	@Column(name = "post_like_count")
	private int postLike;
	
	//@Column(name = "post_author")
	//private String postAuthor;

	

	/**
	 * @return the postAttachment
	 */
	public boolean isPostAttachment() {
		return postAttachment;
	}

	/**
	 * @param postAttachment the postAttachment to set
	 */
	public void setPostAttachment(boolean postAttachment) {
		this.postAttachment = postAttachment;
	}

	// bi-directional one-to-one association to UserEntity
	
	 @ManyToOne(fetch = FetchType.LAZY)	 
	 @JoinColumn(name = "post_author")
	 private UserEntity postAuthor;
	 

	/*
	 * @OneToMany(fetch = FetchType.LAZY, mappedBy = "post") private
	 * List<PostCommentEntity> postComments;
	 */

	/**
	 * @return the postComments
	 */
	/*
	 * public List<PostCommentEntity> getPostComments() { return postComments; }
	 * 
	 *//**
		 * @param postComments the postComments to set
		 *//*
			 * public void setPostComments(List<PostCommentEntity> postComments) {
			 * this.postComments = postComments; }
			 */

	/**
	 * @return the postAuthor
	 */
	public UserEntity getPostAuthor() {
		return postAuthor;
	}

	/**
	 * @param postAuthor the postAuthor to set
	 */
	public void setPostAuthor(UserEntity user) {
		this.postAuthor = user;
	}

	@Column(name = "post_body")
	private String postBody;

	@Column(name = "post_date")
	private String postDate;

	@Column(name = "post_date_last_comment")
	private String postDateLastComment;

	public PostEntity() {
	}

	public Long getPostId() {
		return this.postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getPostBody() {
		return this.postBody;
	}

	public void setPostBody(String postBody) {
		this.postBody = postBody;
	}

	public String getPostDate() {
		return this.postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getPostDateLastComment() {
		return this.postDateLastComment;
	}

	public void setPostDateLastComment(String postDateLastComment) {
		this.postDateLastComment = postDateLastComment;
	}
	
	public int getPostLike() {
		return postLike;
	}

	public void setPostLike(int postLike) {
		this.postLike = postLike;
	}

}