package com.formbean.session;

import java.util.List;

import com.formbean.dto.CommentPostDto;
import com.formbean.dto.PostModel;

public class UserOwnPost extends PostModel {
	
	private List<CommentPostDto> commentstPostDto;
	
	public List<CommentPostDto> getCommentPostDto() {
		return commentstPostDto;
	}

	public void setCommentPostDto(List<CommentPostDto> commentstPostDto) {
		this.commentstPostDto = commentstPostDto;
	}

	/**
	 * 
	 */
	public UserOwnPost() {
		super();	}

	/**
	 * @param postId
	 * @param postAuthorId
	 * @param postAuthorName
	 * @param postAuthorRole
	 * @param postDate
	 * @param postBody
	 * @param postAuthorPhoto
	 */
	public UserOwnPost(Long postId, String postAuthorId, String postAuthorName, String postAuthorRole,
			String postDate, String postBody, String postAuthorPhoto) {
		super(postId, postAuthorId, postAuthorName, postAuthorRole, postDate, postBody, postAuthorPhoto);
	}

}
