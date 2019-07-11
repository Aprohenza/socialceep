package com.formbean.dto;

public class UserProfileDto {

	private String userProfileId;

	private String userProfileName;

	private String userProfileLastName;

	private String userProfileRole;

	private String userProfileNationality;

	private String userProfilePhotoProfile;

	private String userProfilePhotoCover;
	
	private String userProfileEmail;
	
	private String userProfilePhone;	

	private String userProfileCycle;
	

	public UserProfileDto() {

	}

	/**
	 * @param userProfileId
	 * @param userProfileName
	 * @param userProfileLastName
	 * @param userProfileRole
	 * @param userProfilePhotoProfile
	 * @param userProfilePhotoCover
	 */
	public UserProfileDto(String userProfileId, String userProfileName, String userProfileLastName,
			String userProfileRole, String userProfilePhotoProfile, String userProfilePhotoCover,
			String userProfileNationality, String userProfileEmail) {
		this.userProfileId = userProfileId;
		this.userProfileName = userProfileName;
		this.userProfileLastName = userProfileLastName;
		this.userProfileRole = userProfileRole;
		this.userProfileNationality = userProfileNationality;
		this.userProfilePhotoProfile = userProfilePhotoProfile;
		this.userProfilePhotoCover = userProfilePhotoCover;
		this.userProfileEmail = userProfileEmail;
	}

	/**
	 * @return the userProfileId
	 */
	public String getUserProfileId() {
		return userProfileId;
	}

	/**
	 * @param userProfileId the userProfileId to set
	 */
	public void setUserProfileId(String userProfileId) {
		this.userProfileId = userProfileId;
	}

	/**
	 * @return the userProfileName
	 */
	public String getUserProfileName() {
		return userProfileName;
	}

	/**
	 * @param userProfileName the userProfileName to set
	 */
	public void setUserProfileName(String userProfileName) {
		this.userProfileName = userProfileName;
	}

	/**
	 * @return the userProfileLastName
	 */
	public String getUserProfileLastName() {
		return userProfileLastName;
	}

	/**
	 * @param userProfileLastName the userProfileLastName to set
	 */
	public void setUserProfileLastName(String userProfileLastName) {
		this.userProfileLastName = userProfileLastName;
	}

	/**
	 * @return the userProfileRole
	 */
	public String getUserProfileRole() {
		return userProfileRole;
	}

	/**
	 * @param userProfileRole the userProfileRole to set
	 */
	public void setUserProfileRole(String userProfileRole) {
		this.userProfileRole = userProfileRole;
	}

	/**
	 * @return the userProfilePhotoProfile
	 */
	public String getUserProfilePhotoProfile() {
		return userProfilePhotoProfile;
	}

	/**
	 * @param userProfilePhotoProfile the userProfilePhotoProfile to set
	 */
	public void setUserProfilePhotoProfile(String userProfilePhotoProfile) {
		this.userProfilePhotoProfile = userProfilePhotoProfile;
	}

	/**
	 * @return the userProfilePhotoCover
	 */
	public String getUserProfilePhotoCover() {
		return userProfilePhotoCover;
	}

	/**
	 * @param userProfilePhotoCover the userProfilePhotoCover to set
	 */
	public void setUserProfilePhotoCover(String userProfilePhotoCover) {
		this.userProfilePhotoCover = userProfilePhotoCover;
	}
	
	/**
	 * @return the userProfileNationality
	 */
	public String getUserProfileNationality() {
		return userProfileNationality;
	}

	/**
	 * @param userProfileNationality the userProfileNationality to set
	 */
	public void setUserProfileNationality(String userProfileNationality) {
		this.userProfileNationality = userProfileNationality;
	}
	
	public String getUserProfileEmail() {
		return userProfileEmail;
	}

	public void setUserProfileEmail(String userProfileEmail) {
		this.userProfileEmail = userProfileEmail;
	}
	
	public String getUserProfilePhone() {
		return userProfilePhone;
	}

	public void setUserProfilePhone(String userProfilePhone) {
		this.userProfilePhone = userProfilePhone;
	}
	
	public String getUserProfileCycle() {
		return userProfileCycle;
	}

	public void setUserProfileCycle(String userProfileCycle) {
		this.userProfileCycle = userProfileCycle;
	}

}
