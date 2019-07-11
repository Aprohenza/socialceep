package com.formbean.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.formbean.dto.UserProfileDto;
import com.formbean.entity.LoginEntity;
import com.formbean.entity.UserEntity;
import com.formbean.form.PostModelForm;
import com.formbean.form.UserLoginForm;
import com.formbean.form.model.UserRegistrationRequestNonReferrer;
import com.formbean.session.PostToFeedUserSessionLoad;
import com.formbean.session.SessionManager;
import com.formbean.session.UserFriendSessionLoad;
import com.formbean.session.UserFriendsSession;
import com.formbean.session.UserOwnPost;
import com.formbean.session.UserSession;
import com.formbean.validator.LoginValidator;

@Controller
public class MainController {

	@Autowired
	private LoginValidator loginValidator;

	private UserSession uSession;

	/*
	 * @InitBinder protected void initBinder(WebDataBinder binder) {
	 * binder.addValidators(loginValidator); }
	 */

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String showLogingUserForm(Model model) {
		model.addAttribute("user", new UserLoginForm());
		model.addAttribute("userRegistration", new UserRegistrationRequestNonReferrer());

		model.addAttribute("bornDay", UserRegistrationRequestNonReferrer.DAY);
		model.addAttribute("bornMonth", UserRegistrationRequestNonReferrer.MONTH);
		model.addAttribute("bornYear", UserRegistrationRequestNonReferrer.YEAR);

		return "loginHome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginCheck(@ModelAttribute("user") @Validated UserLoginForm user, BindingResult result,
			ModelMap model, HttpServletRequest request) {

		if (result.hasErrors())
			return "redirect:login";

		try {
			SessionManager sManager = new SessionManager();
			sManager.openSession(request, user);
		} catch (NoResultException ex) {
			System.out.println("Login incorrecto para usuario: " + user.getEmailUser());
			result.rejectValue("emailUser", "user.login.invalid");
			return "redirect:login";
		}

		return "redirect:feed";
	}

	@RequestMapping(value = "/feed", method = RequestMethod.GET)
	public String myAnotherFunc(ModelMap model, HttpServletRequest request) {

		uSession = (UserSession) request.getSession().getAttribute("uSession");

		model.addAttribute("newPost", new PostModelForm());

		model.addAttribute("uSession", uSession);

		return "feed";
	}

	@RequestMapping(value = "/profile/{userid}", method = RequestMethod.GET)
	public String profile(ModelMap model, @PathVariable("userid") String userid, HttpServletRequest request) {

		uSession = (UserSession) request.getSession().getAttribute("uSession");

		boolean isMe = false;

		if (!uSession.getUserProfileId().equals(userid)) {

			boolean isMyFriend = false;

			EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
			EntityManager entitymanager = emfactory.createEntityManager();

			UserEntity userEntity = entitymanager.find(UserEntity.class, userid);

			if (userEntity == null) {
				return "profileUnavailable";
			}

			UserProfileDto userProfile = new UserProfileDto(userEntity.getUserId(), userEntity.getUserName(),
					userEntity.getUserLastname(), userEntity.getUserRole().getRole().getRoleName(),
					Long.toString(userEntity.getUserPhotoProfile()), Long.toString(userEntity.getUserPhotoCover()),
					userEntity.getUserNationality(), userEntity.getUserEmail());

			// is my friend
			for (UserFriendsSession uFS : uSession.getUserFriendsSession()) {
				if (uFS.getUserProfileId().equals(userid)) {
					isMyFriend = true;
				}
			}

			model.addAttribute("userProfile", userProfile);
			model.addAttribute("isMyFriend", isMyFriend);
		} else {
			model.addAttribute("userProfile", uSession);
			isMe = true;
		}

		model.addAttribute("isMe", isMe);

		return "userProfile";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		SessionManager sManager = new SessionManager();
		sManager.closeUserSession(request);
		return "redirect:/";
	}

	@RequestMapping(value = "/mynetwork", method = RequestMethod.GET)
	public String mynetwork(HttpServletRequest request) {

		return "mynetwork";
	}

	@RequestMapping(value = "/socket", method = RequestMethod.GET)
	public String mySocket(HttpServletRequest request) {

		return "socket";
	}

	@RequestMapping(value = "/friends-session/load", method = RequestMethod.GET)
	public ResponseEntity<Void> loadFriendsSession(ModelMap model, HttpServletRequest request) {

		uSession = (UserSession) request.getSession().getAttribute("uSession");

		// carga de amigos de la session
		Runnable userFriendSessionLoadInit = new UserFriendSessionLoad(uSession);
		new Thread(userFriendSessionLoadInit).start();

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
