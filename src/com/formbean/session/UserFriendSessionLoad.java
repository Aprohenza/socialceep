package com.formbean.session;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.formbean.controller.MyWebScket;
import com.formbean.entity.FriendEntity;
import com.formbean.entity.UserEntity;

@Component
public class UserFriendSessionLoad implements Runnable {
	
	
	private UserSession uSession;
	
	
	
	
	public UserFriendSessionLoad(UserSession uSession) {
		this.uSession = uSession;
	}

	private List<UserFriendsSession> getUserFriendsSession() {
		
		List<UserFriendsSession> userFriends = new ArrayList<UserFriendsSession>();
		
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();
		
		TypedQuery<FriendEntity> query = entitymanager.createNamedQuery("FriendEntity.findAllFriends",	FriendEntity.class);
		query.setParameter("friendUserInvited", uSession.getUserProfileId());
		List<FriendEntity> friends = query.getResultList();
		
		if(!friends.isEmpty()) {
			for(FriendEntity fe: friends) {
				UserFriendsSession userFriend = new UserFriendsSession();
				UserEntity uEntity = entitymanager.find(UserEntity.class, fe.getFriendUserRequest());
				userFriend.setUserId(uEntity.getUserId());
				userFriend.setUserName(uEntity.getUserName());
				userFriend.setUserLastName(uEntity.getUserLastname());
				userFriend.setUserGender(uEntity.getUserGender());
				userFriend.setUserNationality(uEntity.getUserNationality());
				userFriend.setUserPhotoProfile(uEntity.getUserPhotoProfile());
				userFriend.setUserPhotoCover(uEntity.getUserPhotoCover());
				userFriend.setUserRole(uEntity.getUserRole().getRole().getRoleName());
				
				userFriends.add(userFriend);
				
			}
		}
				
		return userFriends;		
	}
	
	@Override
	public void run() {
		
		this.uSession.setUserFriendsSession(this.getUserFriendsSession());
		
		MyWebScket.sendMessageToClient(this.uSession.getUserFriendsSession(), "sessionFriends", uSession.getUserProfileId());

	}

}
