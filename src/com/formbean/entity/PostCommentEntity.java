package com.formbean.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the POSTCOMMENT database table.
 * 
 */
@Entity
@Table(name="POSTCOMMENT")
@NamedQueries({
	@NamedQuery(name="PostCommentEntity.findAll", query="SELECT p FROM PostCommentEntity p"),
	@NamedQuery(name="PostCommentEntity.findCommentByPost", query="SELECT p FROM PostCommentEntity p WHERE p.postcommentPostId=:postcommentPostId")
}) 
public class PostCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="postcomment_id")
	private int postcommentId;

	@Column(name="postcomment_body")
	private String postcommentBody;

	@Column(name="postcomment_post_author")
	private String postcommentPostAuthor;

	
	@Column(name="postcomment_post_date")
	private Long postcommentPostDate;

	@Column(name = "postcomment_post_id")
	private Long postcommentPostId;
	
	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @Column(name = "post_id") private PostEntity post;
	 */

	/**
	 * @return the postcommentPostId
	 */
	public Long getPostcommentPostId() {
		return postcommentPostId;
	}

	/**
	 * @param postcommentPostId the postcommentPostId to set
	 */
	public void setPostcommentPostId(Long postcommentPostId) {
		this.postcommentPostId = postcommentPostId;
	}

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "postcomment_post_id") private PostEntity post;
	 */

	public PostCommentEntity() {
	}

	public int getPostcommentId() {
		return this.postcommentId;
	}

	public void setPostcommentId(int postcommentId) {
		this.postcommentId = postcommentId;
	}

	public String getPostcommentBody() {
		return this.postcommentBody;
	}

	public void setPostcommentBody(String postcommentBody) {
		this.postcommentBody = postcommentBody;
	}

	public String getPostcommentPostAuthor() {
		return this.postcommentPostAuthor;
	}

	public void setPostcommentPostAuthor(String postcommentPostAuthor) {
		this.postcommentPostAuthor = postcommentPostAuthor;
	}

	public Long getPostcommentPostDate() {
		return this.postcommentPostDate;
	}

	public void setPostcommentPostDate(Long postcommentPostDate) {
		this.postcommentPostDate = postcommentPostDate;
	}

	/*
	 * public PostEntity getPostcommentPostId() { return this.post; }
	 * 
	 * public void setPostcommentPostId(PostEntity post) { this.post = post; }
	 */

}