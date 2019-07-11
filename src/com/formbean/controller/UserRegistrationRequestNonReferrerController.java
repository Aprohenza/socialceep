package com.formbean.controller;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.formbean.form.model.UserRegistrationRequestNonReferrer;

import test.mail.JwtGenerator;
import test.mail.MailManager;

@Controller
public class UserRegistrationRequestNonReferrerController {

	@RequestMapping(value = "/register/{status}", method = RequestMethod.GET)
	public ModelAndView registerGet(ModelMap model, @PathVariable("status") String status) {
		if (!status.equals("success"))
			return new ModelAndView("registerFailed");

		return new ModelAndView("registerSuccess");

	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(
			@ModelAttribute("userRegistration") UserRegistrationRequestNonReferrer userRegistration,
			BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {

		if (result.hasErrors())
			return new ModelAndView("loginHome");

		System.out.println("nombre: " + userRegistration.getUserNameRegistration());
		System.out.println("apellidos: " + userRegistration.getUserLastNameRegistration());
		System.out.println("email: " + userRegistration.getUserEmailRegistration());
		System.out.println("password: " + userRegistration.getUserPasswordRegistration());
		System.out.println("gender: " + userRegistration.getUserGenderRegistration());

		// prepare jwt
		String token = JwtGenerator.createJwtToUserRegistration(userRegistration.getUserNameRegistration(),
				userRegistration.getUserLastNameRegistration(), userRegistration.getUserEmailRegistration(),
				userRegistration.getUserPasswordRegistration(), userRegistration.getUserGenderRegistration());

		try {
			MailManager.enviarConGMail("aprohenza@gmail.com", "Confirmacion de registro", "<h2>Hola "
					+ userRegistration.getUserNameRegistration()
					+ ", gracias por registrarte en Social Ceep.</h2><h3>Para confirmar tu cuenta haz clic en el siguiente enlace:</h3><a href=\""
					+ token + "\">Confirmar mi cuenta</a><h3>esperamos verte pronto.</h3></h5>The Social Ceep Team</h5>");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:register/failed");
		}

		model.addAttribute("userRegistration", userRegistration);

		redirectAttributes.addFlashAttribute("userRegistration", userRegistration);

		return new ModelAndView("redirect:register/success");
	}

}
