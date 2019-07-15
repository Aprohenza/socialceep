package com.socialceep.session;

import com.socialceep.dto.UserProfileDto;

public class UserFriendsRequestSession extends UserProfileDto{
	
	private Long friendRequestId;
	
	
	public UserFriendsRequestSession() {
		
	}

	public UserFriendsRequestSession(String userProfileId, String userProfileName, String userProfileLastName,
			String userProfileRole, String userProfilePhotoProfile, String userProfilePhotoCover,
			String userProfileNationality) {
		super(userProfileId, userProfileName, userProfileLastName, userProfileRole, userProfilePhotoProfile,
				userProfilePhotoCover, userProfileNationality, null, null, null);
		
	}

	public Long getFriendRequestId() {
		return friendRequestId;
	}

	public void setFriendRequestId(Long friendRequestId) {
		this.friendRequestId = friendRequestId;
	}

}
