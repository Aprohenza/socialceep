package com.formbean.session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.formbean.controller.MyWebScket;
import com.formbean.dto.AttachmentDto;
import com.formbean.entity.AttachmentEntity;
import com.formbean.entity.FriendEntity;
import com.formbean.entity.PostEntity;
import com.formbean.entity.UserEntity;

@Component
public class UserFriendSessionLoad implements Runnable {

	private UserSession uSession;

	public UserFriendSessionLoad(UserSession uSession) {
		this.uSession = uSession;
	}

	private List<UserFriendsSession> getUserFriendsSession() {

		List<UserFriendsSession> userFriends = new ArrayList<UserFriendsSession>();

		List<PostFromFriendsUserSession> postListFromFriends = new ArrayList<PostFromFriendsUserSession>();

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<FriendEntity> query = entitymanager.createNamedQuery("FriendEntity.findAllFriends",
				FriendEntity.class);
		query.setParameter("uSessionId", uSession.getUserProfileId());
		List<FriendEntity> friends = query.getResultList();

		if (!friends.isEmpty()) {
			UserEntity uEntity;

			for (FriendEntity fe : friends) {
				
				//CARGA DE AMIGOS DE LA SESSION
				UserFriendsSession userFriend = new UserFriendsSession();

				if (!fe.getFriendUserRequest().equals(uSession.getUserProfileId())) {
					uEntity = entitymanager.find(UserEntity.class, fe.getFriendUserRequest());
				} else {
					uEntity = entitymanager.find(UserEntity.class, fe.getFriendUserInvited());
				}

				userFriend.setUserProfileId(uEntity.getUserId());
				userFriend.setUserProfileName(uEntity.getUserName());
				userFriend.setUserProfileLastName(uEntity.getUserLastname());
				// userFriend.setUserGender(uEntity.getUserGender());
				userFriend.setUserProfileNationality(uEntity.getUserNationality());
				userFriend.setUserProfilePhotoProfile(Long.toString(uEntity.getUserPhotoProfile()));
				userFriend.setUserProfilePhotoCover(Long.toString(uEntity.getUserPhotoCover()));
				userFriend.setUserProfileRole(uEntity.getUserRole().getRole().getRoleName());

				userFriends.add(userFriend);

			}
			
		

		}

		return userFriends;
	}

	@Override
	public void run() {

		this.uSession.setUserFriendsSession(this.getUserFriendsSession());

		MyWebScket.sendMessageToClient(this.uSession.getUserFriendsSession(), "sessionFriends",
				uSession.getUserProfileId());

	}

}
