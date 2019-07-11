package com.formbean.session;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.formbean.controller.MyWebScket;
import com.formbean.entity.FriendEntity;
import com.formbean.entity.UserEntity;

@Component
public class UserFriendsRequestSessionLoad implements Runnable {
	
	
	private UserSession uSession;
	

	public UserFriendsRequestSessionLoad(UserSession uSession) {
		this.uSession = uSession;
	}

	private List<UserFriendsRequest> getUserFriendsRequestSession() {

		List<UserFriendsRequest> userFriendsRequest = new ArrayList<UserFriendsRequest>();

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<FriendEntity> query = entitymanager.createNamedQuery("FriendEntity.findAllFriendsRequest",
				FriendEntity.class);
		query.setParameter("friendUserInvited", uSession.getUserProfileId());
		List<FriendEntity> friendsRequest = query.getResultList();

		if (!friendsRequest.isEmpty()) {
			for (FriendEntity fe : friendsRequest) {
				UserFriendsRequest userFriendRequest = new UserFriendsRequest();
				UserEntity uEntity = entitymanager.find(UserEntity.class, fe.getFriendUserRequest());
				userFriendRequest.setUserId(uEntity.getUserId());
				userFriendRequest.setUserName(uEntity.getUserName());
				userFriendRequest.setUserLastName(uEntity.getUserLastname());
				userFriendRequest.setUserGender(uEntity.getUserGender());
				userFriendRequest.setUserNationality(uEntity.getUserNationality());
				userFriendRequest.setUserPhotoProfile(uEntity.getUserPhotoProfile());
				userFriendRequest.setUserPhotoCover(uEntity.getUserPhotoCover());
				userFriendRequest.setUserRole(uEntity.getUserRole().getRole().getRoleName());
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
