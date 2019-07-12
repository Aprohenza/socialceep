package com.socialceep.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.socialceep.form.UserLoginForm;

@Component
public class LoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserLoginForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {

		ValidationUtils.rejectIfEmpty(err, "emailUser", "user.email.empty");
		ValidationUtils.rejectIfEmpty(err, "passwordUser", "user.password.empty");
	

		UserLoginForm user = (UserLoginForm) obj;

		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if (!(pattern.matcher(user.getEmailUser()).matches())) {
			err.rejectValue("emailUser", "user.email.invalid");
		}

	}

}
