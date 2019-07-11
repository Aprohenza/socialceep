package com.formbean.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.formbean.dto.UserProfileDTO;
import com.formbean.entity.ConversationEntity;
import com.formbean.entity.UserEntity;

public class UserDAO implements Runnable {

	private static EntityManagerFactory emfactory;
	private static EntityManager entitymanager;
	
	UserEntity userEntity;
	private UserProfileDTO userProfileDataChange;

	public UserDAO(UserProfileDTO userProfileDataChange) {	
		
		emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		entitymanager = emfactory.createEntityManager();
		
		this.userEntity = entitymanager.find(UserEntity.class, userProfileDataChange.getUserProfileId());
			
		this.userProfileDataChange = userProfileDataChange;
	}

	//update user information
	private void updateUserInformation(UserProfileDTO userProfileDataChange) {
		System.out.println("Ejecutando update user information.");
		
		entitymanager.getTransaction().begin();
		
		if(userProfileDataChange.getUserProfilePhotoProfile() != null && !userProfileDataChange.getUserProfilePhotoProfile().equals(""))					
			userEntity.setUserPhotoProfile(Long.parseLong(userProfileDataChange.getUserProfilePhotoProfile()));
		
		if(userProfileDataChange.getUserProfilePhotoCover() != null && !userProfileDataChange.getUserProfilePhotoCover().equals(""))
			userEntity.setUserPhotoCover(Long.parseLong(userProfileDataChange.getUserProfilePhotoCover()));
		
		if(userProfileDataChange.getUserProfileNationality() != null && !userProfileDataChange.getUserProfileNationality().equals(""))
			userEntity.setUserNationality(userProfileDataChange.getUserProfileNationality());
		
		entitymanager.getTransaction().commit();
		
		entitymanager.close();
		emfactory.close();
		
		System.out.println("Update user information terminado.");
	}
	
	public static UserEntity getUserById(String userId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		UserEntity uE = entitymanager.find(UserEntity.class, userId);
		entitymanager.close();
		emfactory.close();
		return uE;
	}

	public static List<ConversationEntity> getConversationsOfUser(String userId) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		UserEntity uE = entitymanager.find(UserEntity.class, userId);
		List<ConversationEntity> conversations = uE.getConversations();
		
		entitymanager.close();
		emfactory.close();

		return conversations;
	}

	@Override
	public void run() {
		this.updateUserInformation(this.userProfileDataChange);
		
	}

}
