package com.socialceep.session;

import com.socialceep.dto.PostDto;

public class PostToFeedUserSession extends PostDto {

	public PostToFeedUserSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostToFeedUserSession(Long postId, String postAuthorId, String postAuthorName, String postAuthorRole, String postAuthorCycle,
			String postDate, String postBody, String postAuthorPhoto) {
		super(postId, postAuthorId, postAuthorName, postAuthorRole, postAuthorCycle, postDate, postBody, postAuthorPhoto);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
