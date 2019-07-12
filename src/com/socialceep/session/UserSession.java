package com.socialceep.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.socialceep.dto.UserProfileDto;
import com.socialceep.entity.ConversationEntity;
import com.socialceep.entity.UserEntity;

@Component
public class UserSession extends UserProfileDto{
	
	
	
	private List<UserFriendsRequestSession> userFriendsRequest;
	
	private List<UserFriendsSession> userFriendsSession;
	
	private List<UserOwnPostSession> userOwnPostSession; 

	private List<UserConversation> userConversations;
	
	
	
	
	


	public UserSession() {
	}
	
	
	
	

	public UserSession(String userProfileId, String userProfileName, String userProfileLastName, String userProfileRole,
			String userProfilePhotoProfile, String userProfilePhotoCover, String userProfileNationality) {
		super(userProfileId, userProfileName, userProfileLastName, userProfileRole, userProfilePhotoProfile,
				userProfilePhotoCover, userProfileNationality, null, null);
		
	}





	public UserSession(List<UserFriendsRequestSession> userFriendsRequest, List<UserFriendsSession> userFriendsSession,
			List<UserOwnPostSession> userOwnPostSession, List<UserConversation> userConversations) {
		
		this.userFriendsRequest = userFriendsRequest;
		this.userFriendsSession = userFriendsSession;
		this.userOwnPostSession = userOwnPostSession;
		this.userConversations = userConversations;
	}




	/**
	 * @return the userProfileEntity
	 */
	public UserEntity getUserProfileEntity() {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		UserEntity userEntity = entitymanager.find(UserEntity.class, super.getUserProfileId());
		return userEntity;
	}
	
	public List<UserFriendsRequestSession> getUserFriendsRequest() {
		return userFriendsRequest;
	}


	public void setUserFriendsRequest(List<UserFriendsRequestSession> userFriendsRequest) {
		this.userFriendsRequest = userFriendsRequest;
	}
	
	public List<UserFriendsSession> getUserFriendsSession() {
		return userFriendsSession;
	}

	public void setUserFriendsSession(List<UserFriendsSession> userFriendsSession) {
		this.userFriendsSession = userFriendsSession;
	}
	
	public List<UserConversation> getUserConversations() {
		return userConversations;
	}

	public void setUserConversations(List<UserConversation> userConversations) {
		this.userConversations = userConversations;
	}

	
	public List<UserOwnPostSession> getUserOwnPosts() {
		return userOwnPostSession;
	}


	public void setUserOwnPosts(List<UserOwnPostSession> userOwnPosts) {
		this.userOwnPostSession = userOwnPosts;
	}
	
	public List<UserOwnPostSession> getUserOwnPostSession() {
		return userOwnPostSession;
	}


	public void setUserOwnPostSession(List<UserOwnPostSession> userOwnPostSession) {
		this.userOwnPostSession = userOwnPostSession;
	}
	

}
