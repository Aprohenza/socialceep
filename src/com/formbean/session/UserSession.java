package com.formbean.session;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.formbean.entity.ConversationEntity;
import com.formbean.entity.UserEntity;

@Component
public class UserSession{
	
	private String userProfileId;
	
	private String userProfileName;
	
	private String userProfileLastName;
	
	private String userProfileRole;
	
	private String userProfileNationality;
	
	private String userProfilePhoto;
	
	private String userProfilePhotoCover;
	
	private List<UserFriendsRequest> userFriendsRequest;
	
	private List<UserFriendsSession> userFriendsSession;
	
	private List<UserOwnPostSession> userOwnPostSession; 

	private List<UserConversation> userConversations;
	
	
	
	
	


	public UserSession() {
	}
	

	/**
	 * @return the userProfileEntity
	 */
	public UserEntity getUserProfileEntity() {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		UserEntity userEntity = entitymanager.find(UserEntity.class, userProfileId);
		return userEntity;
	}
	
	public List<UserFriendsRequest> getUserFriendsRequest() {
		return userFriendsRequest;
	}


	public void setUserFriendsRequest(List<UserFriendsRequest> userFriendsRequest) {
		this.userFriendsRequest = userFriendsRequest;
	}

	


	/**
	 * @param userProfileId
	 * @param userProfileName
	 * @param userProfileLastName
	 * @param userProfileRole
	 * @param userProfileNationality
	 * @param userProfilePhoto
	 * @param userProfilePhotoCover
	 */
	public UserSession(String userProfileId, String userProfileName, String userProfileLastName,
			String userProfileRole, String userProfileNationality, String userProfilePhoto,
			String userProfilePhotoCover) {
		this.userProfileId = userProfileId;
		this.userProfileName = userProfileName;
		this.userProfileLastName = userProfileLastName;
		this.userProfileRole = userProfileRole;
		this.userProfileNationality = userProfileNationality;
		this.userProfilePhoto = userProfilePhoto;
		this.userProfilePhotoCover = userProfilePhotoCover;
	}

	




	/**
	 * @return the userProfileId
	 */
	public String getUserProfileId() {
		return userProfileId;
	}

	/**
	 * @param userProfileId the userProfileId to set
	 */
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	/**
	 * @return the userProfileName
	 */
	public String getUserProfileName() {
		return userProfileName;
	}

	/**
	 * @param userProfileName the userProfileName to set
	 */
	public void setUserProfileName(String userProfileName) {
		this.userProfileName = userProfileName;
	}

	/**
	 * @return the userProfileLastName
	 */
	public String getUserProfileLastName() {
		return userProfileLastName;
	}

	/**
	 * @param userProfileLastName the userProfileLastName to set
	 */
	public void setUserProfileLastName(String userProfileLastName) {
		this.userProfileLastName = userProfileLastName;
	}

	/**
	 * @return the userProfileRole
	 */
	public String getUserProfileRole() {
		return userProfileRole;
	}

	/**
	 * @param userProfileRole the userProfileRole to set
	 */
	public void setUserProfileRole(String userProfileRole) {
		this.userProfileRole = userProfileRole;
	}

	/**
	 * @return the userProfileNationality
	 */
	public String getUserProfileNationality() {
		return userProfileNationality;
	}

	/**
	 * @param userProfileNationality the userProfileNationality to set
	 */
	public void setUserProfileNationality(String userProfileNationality) {
		this.userProfileNationality = userProfileNationality;
	}

	/**
	 * @return the userProfilePhoto
	 */
	public String getUserProfilePhoto() {
		return userProfilePhoto;
	}

	/**
	 * @param userProfilePhoto the userProfilePhoto to set
	 */
	public void setUserProfilePhoto(String userProfilePhoto) {
		this.userProfilePhoto = userProfilePhoto;
	}

	/**
	 * @return the userProfilePhotoCover
	 */
	public String getUserProfilePhotoCover() {
		return userProfilePhotoCover;
	}

	/**
	 * @param userProfilePhotoCover the userProfilePhotoCover to set
	 */
	public void setUserProfilePhotoCover(String userProfilePhotoCover) {
		this.userProfilePhotoCover = userProfilePhotoCover;
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
