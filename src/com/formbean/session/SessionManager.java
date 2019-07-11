package com.formbean.session;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.formbean.dao.MessageDAO;
import com.formbean.dao.UserDAO;
import com.formbean.encoder.PasswordEncoderGenerator;
import com.formbean.entity.FriendEntity;
import com.formbean.entity.LoginEntity;
import com.formbean.entity.UserEntity;
import com.formbean.form.UserLoginForm;
import com.formbean.updates.CheckNewPost;

@Component
public class SessionManager {

	
	private static UserSession uSession;
	
	private UserOwnPostSessionLoad userOwnPostSessionLoad;
	

	public static void openSession(HttpServletRequest request, UserLoginForm user) throws NoResultException {
		String email = user.getEmailUser();
		String passwordEncoded = PasswordEncoderGenerator.encode(user.getPasswordUser());

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<LoginEntity> loginEntity = entitymanager.createNamedQuery("LoginEntity.authenticateLogin",
				LoginEntity.class);

		loginEntity.setParameter("loginEmail", email);
		loginEntity.setParameter("loginPassword", passwordEncoded);

		LoginEntity le = loginEntity.getSingleResult();
		
		uSession = new UserSession();

		uSession.setUserProfileId(le.getUser().getUserId()); // user id
		uSession.setUserProfileName(le.getUser().getUserName()); // name
		uSession.setUserProfileLastName(le.getUser().getUserLastname()); // last name
		uSession.setUserProfileRole(le.getUser().getUserRole().getRole().getRoleName()); // role
		uSession.setUserProfileNationality(le.getUser().getUserNationality()); // nationality
		uSession.setUserProfilePhoto(Long.toString(le.getUser().getUserPhotoProfile())); // photo profile
		uSession.setUserProfilePhotoCover(Long.toString(le.getUser().getUserPhotoCover())); // photo cover
		
		
		// carga de amigos de la session
		Runnable userFriendSessionLoadInit = new UserFriendSessionLoad(uSession);
		new Thread(userFriendSessionLoadInit).start();
		
		request.getSession().setAttribute("uSession", uSession);

		System.out.println("uSession CREADO EXITOSAMENTE!!!");

	}

	/*
	 * public void loadSessionComponents() { // carga de componentes de la session
	 * 
	 * // carga de post donde la session es author Runnable
	 * userOwnPostSessionLoadInit = this.userOwnPostSessionLoad; new
	 * Thread(userOwnPostSessionLoadInit).start();
	 * 
	 * // carga de amigos de la session Runnable userFriendSessionLoadInit =
	 * this.userFriendSessionLoad; new Thread(userFriendSessionLoadInit).start();
	 * 
	 * // carga de solicitudes de amistad de la session Runnable
	 * userFriendsRequestSessionLoadInit = this.userFriendsRequestSessionLoad; new
	 * Thread(userFriendsRequestSessionLoadInit).start();
	 * 
	 * }
	 */
	

	public UserSession getUserSession(HttpServletRequest request) {
		return (UserSession) request.getSession().getAttribute("uSession");
	}

	public void closeUserSession(HttpServletRequest request) {
		//this.uSession = null;
		request.getSession(false).invalidate();
	}

}
