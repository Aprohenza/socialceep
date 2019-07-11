package com.formbean.session;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserFriendsRequestSession {
	
	private List<UserFriendsRequest> userFriendsRequest;

	public List<UserFriendsRequest> getUserFriendsRequest() {
		return userFriendsRequest;
	}

	public void setUserFriendsRequest(List<UserFriendsRequest> userFriendsRequest) {
		this.userFriendsRequest = userFriendsRequest;
	}
	
	

}
