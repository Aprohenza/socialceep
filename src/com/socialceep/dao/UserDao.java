package com.socialceep.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.socialceep.dto.UserProfileDto;
import com.socialceep.entity.ConversationEntity;
import com.socialceep.entity.UserEntity;
import com.socialceep.session.UserFriendsSuggestion;
import com.socialceep.session.UserSession;

public class UserDao implements Runnable {

	private static EntityManagerFactory emfactory;
	private static EntityManager entitymanager;

	UserEntity userEntity;
	private UserProfileDto userProfileDataChange;

	public UserDao(UserProfileDto userProfileDataChange) {

		emfactory = Persistence.createEntityManagerFactory("socialceep");
		entitymanager = emfactory.createEntityManager();

		this.userEntity = entitymanager.find(UserEntity.class, userProfileDataChange.getUserProfileId());

		this.userProfileDataChange = userProfileDataChange;
	}

	public static List<UserEntity> getUsersMatch(String match) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
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
	
	public static List<UserFriendsSuggestion> getFriendsSuggestion(UserSession uSession, Integer userCycle) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		//la gente de mi ciclo
		TypedQuery<UserEntity> query = entitymanager.createNamedQuery("UserEntity.findFriendsSuggestion", UserEntity.class);
		query.setParameter("userCycle", userCycle);
		
		List<UserFriendsSuggestion> usersFriendsSuggestion =  new ArrayList<UserFriendsSuggestion>();
		
		for(UserEntity uE: query.getResultList()) {
			if(!uSession.getUserProfileId().equals(uE.getUserId())) {
				UserFriendsSuggestion uFriendSuggestion = new UserFriendsSuggestion();
				uFriendSuggestion.setUserProfileId(uE.getUserId());
				uFriendSuggestion.setUserProfileName(uE.getUserName());
				uFriendSuggestion.setUserProfileLastName(uE.getUserLastname());
				uFriendSuggestion.setUserProfilePhotoProfile(Long.toString(uE.getUserPhotoProfile()));
				uFriendSuggestion.setUserProfileRole(uE.getUserRole().getRole().getRoleName());
				uFriendSuggestion.setUserProfileCycle(uE.getCycleCurseSessionEntity().getCycle().getCycleName());
				usersFriendsSuggestion.add(uFriendSuggestion);
			}
			
		}
		
		//other people
		TypedQuery<UserEntity> q = entitymanager.createNamedQuery("UserEntity.findAll", UserEntity.class);
		q.setMaxResults(10);
		
		for(UserEntity uE: q.getResultList()) {
			if(!uSession.getUserProfileId().equals(uE.getUserId())) {
				UserFriendsSuggestion uFriendSuggestion = new UserFriendsSuggestion();
				uFriendSuggestion.setUserProfileId(uE.getUserId());
				uFriendSuggestion.setUserProfileName(uE.getUserName());
				uFriendSuggestion.setUserProfileLastName(uE.getUserLastname());
				uFriendSuggestion.setUserProfilePhotoProfile(Long.toString(uE.getUserPhotoProfile()));
				uFriendSuggestion.setUserProfileRole(uE.getUserRole().getRole().getRoleName());
				
				if(uE.getCycleCurseSessionEntity()!=null) {
					uFriendSuggestion.setUserProfileCycle(uE.getCycleCurseSessionEntity().getCycle().getCycleName());
				}
				
				usersFriendsSuggestion.add(uFriendSuggestion);
			}
		}
		
		
		

		entitymanager.close();
		emfactory.close();

		return usersFriendsSuggestion;

	}

	// update user information
	private void updateUserInformation(UserProfileDto userProfileDataChange) {
		System.out.println("INIT UPDATE USER INFORMATION.");
		System.out.println("PHONE: " + userProfileDataChange.getUserProfilePhone());

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
		
		if(userProfileDataChange.getUserProfilePhone() != null && !userProfileDataChange.getUserProfilePhone().equals(""))
			userEntity.setUserPhone(userProfileDataChange.getUserProfilePhone());

		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();

		System.out.println("END UPDATE USER INFORMATION.");
	}

	public static UserEntity getUserById(String userId) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();

		UserEntity uE = entitymanager.find(UserEntity.class, userId);
		entitymanager.close();
		emfactory.close();
		return uE;
	}

	public static List<ConversationEntity> getConversationsOfUser(String userId) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();

		UserEntity uE = entitymanager.find(UserEntity.class, userId);
		List<ConversationEntity> conversations = uE.getConversations();

		entitymanager.close();
		emfactory.close();

		return conversations;
	}

public static void createNewUser(String userId, String userName, String userLastname, String userGender, String userNationality, String userEmail, String userPhone, String userPresentation, Integer userCycle, String userYearGraduate, String userSignedDate, String userPhotoProfile, String userPhotoCover) {
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
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
			uE.setUserPhotoCover(Long.parseLong("2222222222222"));
		
		
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
