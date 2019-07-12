package com.socialceep.session;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.socialceep.controller.MyWebScket;
import com.socialceep.entity.FriendEntity;
import com.socialceep.entity.UserEntity;

@Component
public class UserFriendsRequestSessionLoad implements Runnable {
	
	
	private UserSession uSession;
	

	public UserFriendsRequestSessionLoad(UserSession uSession) {
		this.uSession = uSession;
	}

	private List<UserFriendsRequestSession> getUserFriendsRequestSession() {

		List<UserFriendsRequestSession> userFriendsRequest = new ArrayList<UserFriendsRequestSession>();

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<FriendEntity> query = entitymanager.createNamedQuery("FriendEntity.findAllFriendsRequest",
				FriendEntity.class);
		query.setParameter("friendUserInvited", uSession.getUserProfileId());
		List<FriendEntity> friendsRequest = query.getResultList();

		if (!friendsRequest.isEmpty()) {
			for (FriendEntity fe : friendsRequest) {
				UserFriendsRequestSession userFriendRequest = new UserFriendsRequestSession();
				UserEntity uEntity = entitymanager.find(UserEntity.class, fe.getFriendUserRequest());
				userFriendRequest.setUserProfileId(uEntity.getUserId());
				userFriendRequest.setUserProfileName(uEntity.getUserName());
				userFriendRequest.setUserProfileLastName(uEntity.getUserLastname());
				//userFriendRequest.setUserGender(uEntity.getUserGender());
				userFriendRequest.setUserProfileNationality(uEntity.getUserNationality());
				userFriendRequest.setUserProfilePhotoProfile(Long.toString(uEntity.getUserPhotoProfile()));
				userFriendRequest.setUserProfilePhotoCover(Long.toString(uEntity.getUserPhotoCover()));
				userFriendRequest.setUserProfileRole(uEntity.getUserRole().getRole().getRoleName());
				userFriendRequest.setFriendRequestId(fe.getFriendId());

				userFriendsRequest.add(userFriendRequest);

			}
		}

		return userFriendsRequest;
	}

	@Override
	public void run() {
		this.uSession.setUserFriendsRequest(this.getUserFriendsRequestSession());
		
		
		Object object;
		if(this.uSession.getUserFriendsRequest().size() != 0)
			object = this.uSession.getUserFriendsRequest();
			
		else
			object = 0;
		
		MyWebScket.sendMessageToClient(object, "sessionFriendsRequest", uSession.getUserProfileId());
	
		

	}

}
