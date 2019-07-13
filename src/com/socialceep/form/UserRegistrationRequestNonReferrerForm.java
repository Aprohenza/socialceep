package com.socialceep.form;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserRegistrationRequestNonReferrerForm {
	
	private String userNameRegistration;
	private String userLastNameRegistration;
	private String userEmailRegistration;
	private String userPasswordRegistration;
	private String userBornDayRegistration;
	private String userBornMonthRegistration;
	private String userBornYearRegistration;
	private String userGenderRegistration;
	
	public static final List<Integer> DAY = new ArrayList<>();
	public static final String[] MONTH = {"ene", "feb", "mar", "abr", "may", "jun", "jul", "ago", "sep", "oct", "nov", "dic"};
	public static final List<Integer> YEAR = new ArrayList<>();
	
	private void fillDays() {
		this.DAY.clear();
		for(int i = 1; i <= 31; i++){
			this.DAY.add(i);
		}
	}
	
	 private void fillYear() {
		 Calendar cal= Calendar.getInstance();
		 int year = cal.get(Calendar.YEAR);
		 this.YEAR.clear();
		 for(int i = year - 18; i >= 1900; i--) {
			 this.YEAR.add(i);
		 }
	}
	 
	
	public UserRegistrationRequestNonReferrerForm() {
		fillDays();
		fillYear();
	}

	public UserRegistrationRequestNonReferrerForm(String userNameRegistration, String userLastNameRegistration,
			String userEmailRegistration, String userPasswordRegistration, String userBornDayRegistration,
			String userBornMonthRegistration, String userBornYearRegistration, String userGenderRegistration) {
		fillDays();
		fillYear();
		this.userNameRegistration = userNameRegistration;
		this.userLastNameRegistration = userLastNameRegistration;
		this.userEmailRegistration = userEmailRegistration;
		this.userPasswordRegistration = userPasswordRegistration;
		this.userBornDayRegistration = userBornDayRegistration;
		this.userBornMonthRegistration = userBornMonthRegistration;
		this.userBornYearRegistration = userBornYearRegistration;
		this.userGenderRegistration = userGenderRegistration;
	}

	public String getUserNameRegistration() {
		return userNameRegistration;
	}

	public void setUserNameRegistration(String userNameRegistration) {
		this.userNameRegistration = userNameRegistration;
	}

	public String getUserLastNameRegistration() {
		return userLastNameRegistration;
	}

	public void setUserLastNameRegistration(String userLastNameRegistration) {
		this.userLastNameRegistration = userLastNameRegistration;
	}

	public String getUserEmailRegistration() {
		return userEmailRegistration;
	}

	public void setUserEmailRegistration(String userEmailRegistration) {
		this.userEmailRegistration = userEmailRegistration;
	}

	public String getUserPasswordRegistration() {
		return userPasswordRegistration;
	}

	public void setUserPasswordRegistration(String userPasswordRegistration) {
		this.userPasswordRegistration = userPasswordRegistration;
	}

	public String getUserBornDayRegistration() {
		return userBornDayRegistration;
	}

	public void setUserBornDayRegistration(String userBornDayRegistration) {
		this.userBornDayRegistration = userBornDayRegistration;
	}

	public String getUserBornMonthRegistration() {
		return userBornMonthRegistration;
	}

	public void setUserBornMonthRegistration(String userBornMonthRegistration) {
		this.userBornMonthRegistration = userBornMonthRegistration;
	}

	public String getUserBornYearRegistration() {
		return userBornYearRegistration;
	}

	public void setUserBornYearRegistration(String userYearRegistration) {
		this.userBornYearRegistration = userYearRegistration;
	}

	public String getUserGenderRegistration() {
		return userGenderRegistration;
	}

	public void setUserGenderRegistration(String userGenderRegistration) {
		this.userGenderRegistration = userGenderRegistration;
	}

	
	
	
	
	

}
