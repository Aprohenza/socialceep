package com.formbean.session;

import com.formbean.dto.PostDto;

public class PostFromFriendsUserSession extends PostDto {
	
	public PostFromFriendsUserSession() {
		
	}

	public PostFromFriendsUserSession(Long postId, String postAuthorId, String postAuthorName, String postAuthorRole,
			String postDate, String postBody, String postAuthorPhoto) {
		super(postId, postAuthorId, postAuthorName, postAuthorRole, postDate, postBody, postAuthorPhoto);
		// TODO Auto-generated constructor stub
	}
	
	

}
