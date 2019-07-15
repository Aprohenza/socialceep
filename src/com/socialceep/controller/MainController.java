package com.socialceep.controller;


import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.socialceep.dao.CycleCurseSessionDao;
import com.socialceep.dao.UserDao;
import com.socialceep.dto.UserProfileDto;
import com.socialceep.entity.UserEntity;
import com.socialceep.form.PostModelForm;
import com.socialceep.form.UserLoginForm;
import com.socialceep.form.UserRegistrationRequestNonReferrerForm;
import com.socialceep.session.SessionManager;
import com.socialceep.session.UserFriendSessionLoad;
import com.socialceep.session.UserFriendsSession;
import com.socialceep.session.UserSession;
import com.socialceep.validator.LoginValidator;

@Controller
public class MainController {

	@Autowired
	private LoginValidator loginValidator;
	
	

	private UserSession uSession;

	
	 @InitBinder("user")
	 protected void initUserLoginFormBinder(WebDataBinder binder) {
		 binder.addValidators(loginValidator);		
	}
	 
	 
	

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public String showLogingUserForm(Model model) {
		
		model.addAttribute("user", new UserLoginForm());		
		model.addAttribute("userRegistration", new UserRegistrationRequestNonReferrerForm());

		model.addAttribute("bornDay", UserRegistrationRequestNonReferrerForm.DAY);
		model.addAttribute("bornMonth", UserRegistrationRequestNonReferrerForm.MONTH);
		model.addAttribute("bornYear", UserRegistrationRequestNonReferrerForm.YEAR);

		return "loginHome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginCheck(@ModelAttribute("user") @Validated UserLoginForm user, BindingResult result,
			ModelMap model, HttpServletRequest request) {

		if (result.hasErrors())
			return "redirect:login";

		try {
			SessionManager sManager = new SessionManager();
			UserSession uSession = sManager.openSession(request, user);
			
			if(uSession ==null) 
				return "waiting-validation";
				
			
			
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
		
		UserEntity userEntity = UserDao.getUserById(userid);
		
		if (userEntity == null) {
			return "profileUnavailable";
		}
		
		Integer userProfileCycle = null;
		String userProfileCycleName = null;
		
		if(userEntity.getCycleCurseSessionEntity() != null) {
			userProfileCycle = userEntity.getUserCycle();
			userProfileCycleName = CycleCurseSessionDao.getCycleCurseSessionNameCycle(userProfileCycle);
			 
			
		}
			
		
		UserProfileDto userProfile = new UserProfileDto(userEntity.getUserId(), userEntity.getUserName(),
				userEntity.getUserLastname(), userEntity.getUserRole().getRole().getRoleName(),
				Long.toString(userEntity.getUserPhotoProfile()), Long.toString(userEntity.getUserPhotoCover()),
				userEntity.getUserNationality(), userEntity.getUserEmail(), userEntity.getUserPhone(), userProfileCycleName);

		if (!uSession.getUserProfileId().equals(userid)) {

			boolean isMyFriend = false;

			// is my friend
			for (UserFriendsSession uFS : uSession.getUserFriendsSession()) {
				if (uFS.getUserProfileId().equals(userid)) {
					isMyFriend = true;
				}
			}

			model.addAttribute("userProfile", userProfile);
			model.addAttribute("isMyFriend", isMyFriend);
		} else {
			model.addAttribute("userProfile", userProfile);
			isMe = true;
		}

		model.addAttribute("isMe", isMe);

		return "userProfile";
	}
	
	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public String notifications(HttpServletRequest request) {
		//SessionManager sManager = new SessionManager();
		//sManager.closeUserSession(request);
		return "notifications";
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
