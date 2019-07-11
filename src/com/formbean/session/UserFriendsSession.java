package com.formbean.session;

public class UserFriendsSession {
	
	private String userId;
	private String userName;
	private String userLastName;
	private String userGender;
	private String userNationality;
	private Long userPhotoProfile;
	private Long userPhotoCover;
	private String roleName;
	
	

	public UserFriendsSession(String userId, String userName, String userLastName, String userGender, String userNationality,
			Long userPhotoProfile, Long userPhotoCover, String roleName) {		
		this.userId = userId;
		this.userName = userName;
		this.userLastName = userLastName;
		this.userGender = userGender;
		this.userNationality = userNationality;
		this.userPhotoProfile = userPhotoProfile;
		this.userPhotoCover = userPhotoCover;
		this.roleName = roleName;
	}

	public UserFriendsSession() {
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;		
	}

	public void setUserName(String userName) {
		this.userName = userName;		
	}
	
	public String getUserLastName() {
		return userLastName;		
	}
	
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;		
	}
	
	public String getUserGender() {
		return userGender;		
	}
	
	public void setUserGender(String userGender) {
		this.userGender =userGender;		
	}
	
	public String getUserNationality() {
		return userNationality;		
	}
	
	public void setUserNationality(String userNationality) {
		this.userNationality = userNationality;		
	}
	
	public Long getUserPhotoProfile() {
		return userPhotoProfile;		
	}
	
	public void setUserPhotoProfile(Long userPhotoProfile) {
		this.userPhotoProfile = userPhotoProfile;		
	}
	
	public Long getUserPhotoCover() {
		return userPhotoCover;		
	}
	
	public void setUserPhotoCover(Long userPhotoCover) {
		this.userPhotoCover = userPhotoCover;		
	}
	
	public String getUserRole() {
		return roleName;		
	}
	
	public void setUserRole(String roleName) {
		this.roleName = roleName;		
	}

	

}
