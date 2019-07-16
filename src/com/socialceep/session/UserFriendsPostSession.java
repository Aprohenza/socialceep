package com.socialceep.session;

import com.socialceep.dto.PostDto;

public class UserFriendsPostSession extends PostDto {

	/**
	 * 
	 */
	public UserFriendsPostSession() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param postId
	 * @param postAuthorId
	 * @param postAuthorName
	 * @param postAuthorRole
	 * @param postDate
	 * @param postBody
	 * @param postAuthorPhoto
	 */
	public UserFriendsPostSession(Long postId, String postAuthorId, String postAuthorName, String postAuthorRole, String postAuthorCycle,
			String postDate, String postBody, String postAuthorPhoto) {
		super(postId, postAuthorId, postAuthorName, postAuthorRole, postAuthorCycle, postDate, postBody, postAuthorPhoto);
	}
	
	
	
}
