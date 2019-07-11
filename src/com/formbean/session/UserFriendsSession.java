package com.formbean.session;

import com.formbean.dto.UserProfileDto;

public class UserFriendsSession extends UserProfileDto {

	public UserFriendsSession() {		
		// TODO Auto-generated constructor stub
	}

	public UserFriendsSession(String userProfileId, String userProfileName, String userProfileLastName,
			String userProfileRole, String userProfilePhotoProfile, String userProfilePhotoCover,
			String userProfileNationality) {
		super(userProfileId, userProfileName, userProfileLastName, userProfileRole, userProfilePhotoProfile,
				userProfilePhotoCover, userProfileNationality, null);
		// TODO Auto-generated constructor stub
	}

}
