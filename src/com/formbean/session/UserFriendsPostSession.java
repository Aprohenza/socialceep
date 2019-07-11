package com.formbean.session;

import com.formbean.dto.PostModel;

public class UserFriendsPostSession extends PostModel {

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
	public UserFriendsPostSession(Long postId, String postAuthorId, String postAuthorName, String postAuthorRole,
			String postDate, String postBody, String postAuthorPhoto) {
		super(postId, postAuthorId, postAuthorName, postAuthorRole, postDate, postBody, postAuthorPhoto);
	}
	
	
	
}
