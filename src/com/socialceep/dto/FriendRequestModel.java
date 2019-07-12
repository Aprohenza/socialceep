package com.socialceep.dto;

public class FriendRequestModel {
	
	private int friendRequestId;
	
	private String friendUserRequestId;
	
	private String friendUserRequestName;
	
	private String friendUserRequestRole;
	
	private String friendUserRequestImageProfile;
	
	private String friendUserRequestCicle;

	/**
	 * 
	 */
	public FriendRequestModel() {
	}

	/**
	 * @param friendUserRequestId
	 * @param friendUserRequestName
	 * @param friendUserRequestRole
	 * @param friendUserRequestImageProfile
	 * @param friendUserRequestCicle
	 */
	public FriendRequestModel(int friendRequestId, String friendUserRequestId, String friendUserRequestName, String friendUserRequestRole,
			String friendUserRequestImageProfile, String friendUserRequestCicle) {
		this.friendRequestId = friendRequestId;
		this.friendUserRequestId = friendUserRequestId;
		this.friendUserRequestName = friendUserRequestName;
		this.friendUserRequestRole = friendUserRequestRole;
		this.friendUserRequestImageProfile = friendUserRequestImageProfile;
		this.friendUserRequestCicle = friendUserRequestCicle;
	}

	/**
	 * @return the friendUserRequestId
	 */
	public String getFriendUserRequestId() {
		return friendUserRequestId;
	}

	/**
	 * @param friendUserRequestId the friendUserRequestId to set
	 */
	public void setFriendUserRequestId(String friendUserRequestId) {
		this.friendUserRequestId = friendUserRequestId;
	}

	/**
	 * @return the friendUserRequestName
	 */
	public String getFriendUserRequestName() {
		return friendUserRequestName;
	}

	/**
	 * @param friendUserRequestName the friendUserRequestName to set
	 */
	public void setFriendUserRequestName(String friendUserRequestName) {
		this.friendUserRequestName = friendUserRequestName;
	}

	/**
	 * @return the friendUserRequestRole
	 */
	public String getFriendUserRequestRole() {
		return friendUserRequestRole;
	}

	/**
	 * @param friendUserRequestRole the friendUserRequestRole to set
	 */
	public void setFriendUserRequestRole(String friendUserRequestRole) {
		this.friendUserRequestRole = friendUserRequestRole;
	}

	/**
	 * @return the friendUserRequestImageProfile
	 */
	public String getFriendUserRequestImageProfile() {
		return friendUserRequestImageProfile;
	}

	/**
	 * @param friendUserRequestImageProfile the friendUserRequestImageProfile to set
	 */
	public void setFriendUserRequestImageProfile(String friendUserRequestImageProfile) {
		this.friendUserRequestImageProfile = friendUserRequestImageProfile;
	}

	/**
	 * @return the friendUserRequestCicle
	 */
	public String getFriendUserRequestCicle() {
		return friendUserRequestCicle;
	}

	/**
	 * @param friendUserRequestCicle the friendUserRequestCicle to set
	 */
	public void setFriendUserRequestCicle(String friendUserRequestCicle) {
		this.friendUserRequestCicle = friendUserRequestCicle;
	}

	/**
	 * @return the friendRequestId
	 */
	public int getFriendRequestId() {
		return friendRequestId;
	}

	/**
	 * @param friendRequestId the friendRequestId to set
	 */
	public void setFriendRequestId(int friendRequestId) {
		this.friendRequestId = friendRequestId;
	}
	
	

}
