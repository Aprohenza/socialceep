package com.socialceep.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.socialceep.dao.MessageDao;
import com.socialceep.dao.UserDao;
import com.socialceep.dto.FriendRequestModel;
import com.socialceep.entity.FriendEntity;
import com.socialceep.entity.UserEntity;
import com.socialceep.form.NewMessageFromMessaging;
import com.socialceep.session.SessionManager;
import com.socialceep.session.UserFriendSessionLoad;
import com.socialceep.session.UserFriendsRequestSession;
import com.socialceep.session.UserFriendsRequestSessionLoad;
import com.socialceep.session.UserFriendsSession;
import com.socialceep.session.UserOwnPostSessionLoad;
import com.socialceep.session.UserSession;

@Controller
public class MyNetworkController {

	private UserSession uSession;

	@RequestMapping(value = "/mynetwork/connections", method = RequestMethod.GET)
	public String networkConnections(ModelMap model, HttpServletRequest request) {

		this.uSession = (UserSession) request.getSession().getAttribute("uSession");

		List<UserFriendsSession> myFriends = uSession.getUserFriendsSession();

		if (myFriends.size() != 0) {
			System.out.println("TIENE AMIGOS: " + myFriends.size());
			model.addAttribute("friendSession", myFriends);
			return "conectionsSession";

		}

		System.out.println("NO TIENE AMIGOS.");
		return "noConnectionsSession";
	}

	// reload user friends session
	@RequestMapping(value = "/mynetwork/connections/reload", method = RequestMethod.POST)
	public ResponseEntity<Void> networkConnectionsReload(ModelMap model, HttpServletRequest request) {

		this.uSession = (UserSession) request.getSession().getAttribute("uSession");

		// carga de amigos de la session
		Runnable userFriendSessionLoadInit = new UserFriendSessionLoad(uSession);
		new Thread(userFriendSessionLoadInit).start();

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@RequestMapping(value = "/mynetwork/friendrequest/all", method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> getFriendRequest(HttpServletRequest request) {

		this.uSession = (UserSession) request.getSession().getAttribute("uSession");

		// carga de solicitudes de amistad de la session
		Runnable userFriendsRequestSessionLoadInit = new UserFriendsRequestSessionLoad(this.uSession);
		new Thread(userFriendsRequestSessionLoadInit).start();

		return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	}

	@RequestMapping(value = "/mynetwork/actionFriendRequest", method = RequestMethod.POST)
	public ResponseEntity<List<UserFriendsRequestSession>> actionFriendRequest(
			@RequestParam("friendrequestid") Long friendRequestId, @RequestParam("action") String action,
			HttpServletRequest request) {
		/*
		 * ACTIONS ACCEPT REJECT
		 */

		this.uSession = (UserSession) request.getSession().getAttribute("uSession");

		System.out.println("ACTION: " + action);
		System.out.println("FRIEND REQUEST ID: " + friendRequestId);
		System.out.println("SESSION 000: " + this.uSession);
		System.out.println("friendRequestModel BEFORE: " + this.uSession.getUserFriendsRequest().size());

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();

		entitymanager.getTransaction().begin();
		FriendEntity friendUpdate = entitymanager.find(FriendEntity.class, friendRequestId);
		
		String userToNotify = "";

		// before update
		System.out.println("FRIEND REQUEST STATUS BEFORE: " + friendUpdate.getFriendRequestStatus());
		if (action.equals("accept")) {
			friendUpdate.setFriendRequestStatus("ACCEPTED");
			// actualizar lista dde amigos de la session

			FriendEntity friendRequest = entitymanager.find(FriendEntity.class, friendRequestId);
			UserEntity userFR = entitymanager.find(UserEntity.class, friendRequest.getFriendUserRequest()); // user
																											// friend
																											// request
			
			userToNotify = userFR.getUserId();

			this.uSession.getUserFriendsSession()
					.add(new UserFriendsSession(userFR.getUserId(), userFR.getUserName(), userFR.getUserLastname(),
							userFR.getUserRole().getRole().getRoleName(), Long.toString(userFR.getUserPhotoProfile()),
							Long.toString(userFR.getUserPhotoCover()), userFR.getUserNationality()));
			
			
		} else {
			friendUpdate.setFriendRequestStatus("REJECTED");
		}

		entitymanager.getTransaction().commit();

		// after update
		System.out.println("FRIEND REQUEST STATUS AFTER: " + friendUpdate.getFriendRequestStatus());
		entitymanager.close();
		emfactory.close();

		List<UserFriendsRequestSession> friendRequestToRemove = new ArrayList<>();
		
		System.out.println("SESSION 666: " + this.uSession);
		System.out.println("VALOR DE FRIENDS REQUEST AKI: " + this.uSession.getUserFriendsRequest());
		
		for (UserFriendsRequestSession toRemove : this.uSession.getUserFriendsRequest()) {
			System.out.println("CHECK: " + toRemove.getFriendRequestId() + " = " + friendRequestId);
			if (toRemove.getFriendRequestId().equals(friendRequestId)) {
				friendRequestToRemove.add(toRemove);
				System.out.println("FRIEND REQUEST ID: " + friendRequestId + " REMOVED.");
			}
		}

		this.uSession.getUserFriendsRequest().removeAll(friendRequestToRemove);
		List<UserFriendsRequestSession> newFriendsRequest = this.uSession.getUserFriendsRequest(); 
		System.out.println("FRIENDS REQUEST AFTER REMOVE: " + this.uSession.getUserFriendsRequest().size());
		
		if(action.equals("accept")) {
			//NOTIFICAR AL QUE INVITA DE ACTION SOBRE ACTION EN SU INVITACION SI ES ACCEPTED
			MyWebScket.notifyToUser(userToNotify, "acceptedFriendRequest");
		}
		 
		

		return new ResponseEntity<List<UserFriendsRequestSession>>(newFriendsRequest,
				HttpStatus.OK);
	}

	@RequestMapping(value = "/mynetwork/sendNewFriendRequest", method = RequestMethod.POST)
	public ResponseEntity<Void> sendNewFriendRequest(@RequestParam("userid") String userInvitedId,
			HttpServletRequest request) {
		/*
		 * ENVIAR SOLICITUD DE USUARIO CON ID "userInvitedId"
		 */

		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Long sysDate = System.currentTimeMillis();

		this.uSession = (UserSession) request.getSession().getAttribute("uSession");

		System.out.println("USUARIO UNVITADO A CONECTAR: " + userInvitedId);

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();

		entitymanager.getTransaction().begin();

		FriendEntity fE = new FriendEntity();

		fE.setFriendId(sysDate);
		fE.setFriendRequestStatus("PENDING");
		fE.setFriendUserRequest(this.uSession.getUserProfileId());
		fE.setFriendUserInvited(userInvitedId);
		fE.setFriendRequestDate(new Date());

		entitymanager.persist(fE);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();

		// PREPARANDO OBJETO FRIEND REQUEST A ENVIARAL CLIENTE
		UserEntity uE = UserDao.getUserById(userInvitedId);

		UserFriendsRequestSession fR = new UserFriendsRequestSession();
		fR.setFriendRequestId(sysDate);
		fR.setUserProfileId(userInvitedId);
		fR.setUserProfileName(uE.getUserName() + " " + uE.getUserLastname());
		fR.setUserProfilePhotoProfile(Long.toString(uE.getUserPhotoProfile()));

		MyWebScket.sendNotificationToParticularUserConnected(fR, "newFriendRequest", userInvitedId);

		System.out.println("INVITACION CREADA CON EXITO.");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
