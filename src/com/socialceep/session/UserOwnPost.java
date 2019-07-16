package com.socialceep.session;

import java.util.List;

import com.socialceep.dto.CommentPostDto;
import com.socialceep.dto.PostDto;

public class UserOwnPost extends PostDto {
	
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
	public UserOwnPost(Long postId, String postAuthorId, String postAuthorName, String postAuthorRole, String postAuthorCycle,
			String postDate, String postBody, String postAuthorPhoto) {
		super(postId, postAuthorId, postAuthorName, postAuthorRole, postAuthorCycle, postDate, postBody, postAuthorPhoto);
	}

}
