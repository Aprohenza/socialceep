package com.socialceep.session;

import com.socialceep.dto.UserProfileDto;

public class UserFriendsSession extends UserProfileDto {

	public UserFriendsSession() {		
		// TODO Auto-generated constructor stub
	}

	public UserFriendsSession(String userProfileId, String userProfileName, String userProfileLastName,
			String userProfileRole, String userProfilePhotoProfile, String userProfilePhotoCover,
			String userProfileNationality) {
		super(userProfileId, userProfileName, userProfileLastName, userProfileRole, userProfilePhotoProfile,
				userProfilePhotoCover, userProfileNationality, null, null);
		// TODO Auto-generated constructor stub
	}

}
