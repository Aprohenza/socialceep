package com.formbean.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.formbean.dto.UserProfileDto;
import com.formbean.entity.ConversationEntity;
import com.formbean.entity.CycleCurseSessionEntity;
import com.formbean.entity.UserConversationEntity;
import com.formbean.entity.UserEntity;

public class UserDao implements Runnable {

	private static EntityManagerFactory emfactory;
	private static EntityManager entitymanager;

	UserEntity userEntity;
	private UserProfileDto userProfileDataChange;

	public UserDao(UserProfileDto userProfileDataChange) {

		emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		entitymanager = emfactory.createEntityManager();

		this.userEntity = entitymanager.find(UserEntity.class, userProfileDataChange.getUserProfileId());

		this.userProfileDataChange = userProfileDataChange;
	}

	public static List<UserEntity> getUsersMatch(String match) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<UserEntity> query = entitymanager.createNamedQuery("UserEntity.findUsersWithMatch",
				UserEntity.class);
		query.setParameter("userMatch", "%" + match + "%");
		// System.out.println("PARAMETRO ENVIADO: " + "%"+match+"%");
		List<UserEntity> usersMatch = query.getResultList();

		entitymanager.close();
		emfactory.close();

		return usersMatch;

	}

	// update user information
	private void updateUserInformation(UserProfileDto userProfileDataChange) {
		System.out.println("INIT UPDATE USER INFORMATION.");

		entitymanager.getTransaction().begin();

		if (userProfileDataChange.getUserProfilePhotoProfile() != null
				&& !userProfileDataChange.getUserProfilePhotoProfile().equals(""))
			userEntity.setUserPhotoProfile(Long.parseLong(userProfileDataChange.getUserProfilePhotoProfile()));

		if (userProfileDataChange.getUserProfilePhotoCover() != null
				&& !userProfileDataChange.getUserProfilePhotoCover().equals(""))
			userEntity.setUserPhotoCover(Long.parseLong(userProfileDataChange.getUserProfilePhotoCover()));

		if (userProfileDataChange.getUserProfileNationality() != null
				&& !userProfileDataChange.getUserProfileNationality().equals(""))
			userEntity.setUserNationality(userProfileDataChange.getUserProfileNationality());

		if (userProfileDataChange.getUserProfileEmail() != null
				&& !userProfileDataChange.getUserProfileEmail().equals(""))
			userEntity.setUserEmail(userProfileDataChange.getUserProfileEmail());

		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();

		System.out.println("END UPDATE USER INFORMATION.");
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

public static void createNewUser(String userId, String userName, String userLastname, String userGender, String userNationality, String userEmail, String userPhone, String userPresentation, Integer userCycle, String userYearGraduate, String userSignedDate, String userPhotoProfile, String userPhotoCover) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		UserEntity uE = new UserEntity();
		
		uE.setUserId(userId);
		uE.setUserName(userName);
		uE.setUserLastname(userLastname);
		uE.setUserGender(userGender);
		uE.setUserNationality(userNationality);
		uE.setUserEmail(userEmail);
		uE.setUserPhone(userPhone);
		uE.setUserPresentation(userPresentation);
		
		System.out.println("CYCLE LLEGADO A USER DAO: " + userCycle);
		if(userCycle != null) {
			System.out.println("DENTRO DEL IF");
			uE.setUserCycle(userCycle);
		}
			
		
		uE.setUserYearGraduate(userYearGraduate);
		
		uE.setUserSignedDate(userSignedDate);
		
		if(userPhotoProfile != null && !userPhotoProfile.equals(""))
			uE.setUserPhotoProfile(Long.parseLong(userPhotoProfile));
		else
			uE.setUserPhotoProfile(Long.parseLong("1111111111111"));
		
		if(userPhotoCover != null && !userPhotoCover.equals(""))
			uE.setUserPhotoCover(Long.parseLong(userPhotoCover));
		else
			uE.setUserPhotoCover(Long.parseLong("1111111111111"));
		
		
		entitymanager.getTransaction().begin();

		entitymanager.persist(uE);
		
		entitymanager.getTransaction().commit();
		
		
		entitymanager.close();
		emfactory.close();
	}

	@Override
	public void run() {
		this.updateUserInformation(this.userProfileDataChange);

	}

}
