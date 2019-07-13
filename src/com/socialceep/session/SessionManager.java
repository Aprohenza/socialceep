package com.socialceep.session;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;



import com.socialceep.encoder.PasswordEncoderGenerator;
import com.socialceep.entity.LoginEntity;
import com.socialceep.form.UserLoginForm;


public class SessionManager {

	
	
	

	public UserSession openSession(HttpServletRequest request, UserLoginForm user) throws NoResultException {
		
		UserSession uSession = null;
		
		String email = user.getEmailUser();
		String passwordEncoded = PasswordEncoderGenerator.encode(user.getPasswordUser());

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("socialceep");
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<LoginEntity> loginEntity = entitymanager.createNamedQuery("LoginEntity.authenticateLogin",
				LoginEntity.class);

		loginEntity.setParameter("loginEmail", email);
		loginEntity.setParameter("loginPassword", passwordEncoded);

		LoginEntity le = loginEntity.getSingleResult();
		
		if(le.getLoginStatus() != 0) {
			//usuario activado
			uSession = new UserSession();

			uSession.setUserProfileId(le.getUser().getUserId()); // user id
			uSession.setUserProfileName(le.getUser().getUserName()); // name
			uSession.setUserProfileLastName(le.getUser().getUserLastname()); // last name
			uSession.setUserProfileRole(le.getUser().getUserRole().getRole().getRoleName()); // role
			uSession.setUserProfileNationality(le.getUser().getUserNationality()); // nationality
			uSession.setUserProfilePhotoProfile(Long.toString(le.getUser().getUserPhotoProfile())); // photo profile
			uSession.setUserProfilePhotoCover(Long.toString(le.getUser().getUserPhotoCover())); // photo cover
			//uSession.setUserProfileCycle(le.getUser().getCycleCurseSessionEntity().getCycle().getCycleName()); // ciclo
			uSession.setUserProfilePhone(le.getUser().getUserPhone()); // phone
			
			
			// carga de amigos de la session
			Runnable userFriendSessionLoadInit = new UserFriendSessionLoad(uSession);
			new Thread(userFriendSessionLoadInit).start();
			
			request.getSession().setAttribute("uSession", uSession);

			System.out.println("uSession CREADO EXITOSAMENTE!!!");
			
		}
		
		return uSession;

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
