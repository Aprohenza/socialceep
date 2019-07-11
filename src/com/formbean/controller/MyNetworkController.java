package com.formbean.controller;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.formbean.entity.FriendEntity;
import com.formbean.session.UserFriendsRequest;
import com.formbean.session.UserFriendsRequestSessionLoad;
import com.formbean.session.UserSession;

@Controller
public class MyNetworkController {

	
	private UserSession uSession;
	

	@RequestMapping(value = "/mynetwork/friendrequest", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> getFriendRequest(HttpServletRequest request) {
		
		this.uSession = (UserSession) request.getSession().getAttribute("uSession");

		// carga de solicitudes de amistad de la session
		Runnable userFriendsRequestSessionLoadInit = new UserFriendsRequestSessionLoad(this.uSession);
		new Thread(userFriendsRequestSessionLoadInit).start();

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@RequestMapping(value = "/mynetwork/actionFriendRequest", method = RequestMethod.POST)
	public ResponseEntity<List<UserFriendsRequest>> actionFriendRequest(
			@RequestParam("friendrequestid") int friendRequestId, @RequestParam("action") String action,
			HttpServletRequest request) {
		/*
		 * ACTIONS ACCEPT REJECT
		 */

		System.out.println("ACTION: " + action);
		System.out.println("FRIEND REQUEST ID: " + friendRequestId);
		System.out.println("friendRequestModel BEFORE: " + this.uSession.getUserFriendsRequest().size());

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();

		entitymanager.getTransaction().begin();
		FriendEntity friendUpdate = entitymanager.find(FriendEntity.class, friendRequestId);

		// before update
		System.out.println("FRIEND REQUEST STATUS BEFORE: " + friendUpdate.getFriendRequestStatus());
		if (action.equals("accept")) {
			friendUpdate.setFriendRequestStatus("ACCEPTED");
		} else {
			friendUpdate.setFriendRequestStatus("REJECTED");
		}

		entitymanager.getTransaction().commit();

		// after update
		System.out.println("FRIEND REQUEST STATUS AFTER: " + friendUpdate.getFriendRequestStatus());
		entitymanager.close();
		emfactory.close();

		List<UserFriendsRequest> friendRequestToRemove = new ArrayList<>();
		
		for (UserFriendsRequest toRemove : this.uSession.getUserFriendsRequest()) {
			if (toRemove.getFriendRequestId() == (friendRequestId)) {
				friendRequestToRemove.add(toRemove);
				System.out.println("FRIEND REQUEST ID: " + friendRequestId + " REMOVED.");
			}
		}

		this.uSession.getUserFriendsRequest().removeAll(friendRequestToRemove);
		System.out.println("FRIENDS REQUEST AFTER REMOVE: " + this.uSession.getUserFriendsRequest().size());

		return new ResponseEntity<List<UserFriendsRequest>>(this.uSession.getUserFriendsRequest(), HttpStatus.OK);
	}

}
