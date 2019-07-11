package com.formbean.session;


import java.util.List;

import org.springframework.stereotype.Component;

import com.formbean.dto.CommentPostDto;
import com.formbean.dto.PostDto;



public class UserOwnPostSession extends PostDto {

	
	private List<CommentPostDto> commentstPostDto;
	private int postLike;
	
	

	

	public UserOwnPostSession() {
	}

	/**
	 * @param uOwnPosts
	 */
	public UserOwnPostSession(List<UserOwnPost> uOwnPosts) {
		
	}
	
	
	public List<CommentPostDto> getCommentstPostDto() {
		return commentstPostDto;
	}

	public void setCommentstPostDto(List<CommentPostDto> commentstPostDto) {
		this.commentstPostDto = commentstPostDto;
	}

	
	public int getPostLike() {
		return postLike;
	}

	public void setPostLike(int postLike) {
		this.postLike = postLike;
	}
	

}
