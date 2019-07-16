package com.socialceep.session;

import com.socialceep.dto.PostDto;

public class PostFromFriendsUserSession extends PostDto {
	
	public PostFromFriendsUserSession() {
		
	}

	public PostFromFriendsUserSession(Long postId, String postAuthorId, String postAuthorName, String postAuthorRole, String postAuthorCycle,
			String postDate, String postBody, String postAuthorPhoto) {
		super(postId, postAuthorId, postAuthorName, postAuthorRole, postAuthorCycle, postDate, postBody, postAuthorPhoto);
		// TODO Auto-generated constructor stub
	}
	
	

}
