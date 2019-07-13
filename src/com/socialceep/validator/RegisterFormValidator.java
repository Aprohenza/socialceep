package com.socialceep.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.socialceep.form.UserRegistrationRequestNonReferrerForm;

@Component
public class RegisterFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserRegistrationRequestNonReferrerForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors err) {
		
		ValidationUtils.rejectIfEmpty(err, "userNameRegistration", "user.register.name.empty");
		ValidationUtils.rejectIfEmpty(err, "userLastNameRegistration", "user.register.lastname.empty");
		ValidationUtils.rejectIfEmpty(err, "userEmailRegistration", "user.register.email.empty");
		ValidationUtils.rejectIfEmpty(err, "userPasswordRegistration", "user.register.password.empty");
		ValidationUtils.rejectIfEmpty(err, "userBornDayRegistration", "user.register.bornday.empty");
		ValidationUtils.rejectIfEmpty(err, "userBornMonthRegistration", "user.register.bornmonth.empty");
		ValidationUtils.rejectIfEmpty(err, "userBornYearRegistration", "user.register.bornyear.empty");
		ValidationUtils.rejectIfEmpty(err, "userGenderRegistration", "user.register.gender.empty");
		
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		
		UserRegistrationRequestNonReferrerForm userReg = (UserRegistrationRequestNonReferrerForm) obj;
		
		if (!(pattern.matcher(userReg.getUserEmailRegistration()).matches())) {
			err.rejectValue("userEmailRegistration", "user.register.email.empty");
		}
		
	}

}
