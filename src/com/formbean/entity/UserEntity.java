package com.formbean.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name = "USERS")
@NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_course")
	private String userCourse;

	@Column(name = "user_gender")
	private String userGender;

	@Column(name = "user_grade")
	private String userGrade;

	@Column(name = "user_lastname")
	private String userLastname;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_nationality")
	private String userNationality;

	@Column(name = "user_phone")
	private String userPhone;

	@Column(name = "user_presentation")
	private String userPresentation;

	@Column(name = "user_session")
	private String userSession;

	@Column(name = "user_signed_date")
	private String userSignedDate;
	
	@Column(name = "user_photo_profile")
	private Long userPhotoProfile;
	
	@Column(name = "user_photo_cover")
	private Long userPhotoCover;
	
	/**
	 * @return the userPhotoCover
	 */
	public Long getUserPhotoCover() {
		return userPhotoCover;
	}

	/**
	 * @param userPhotoCover the userPhotoCover to set
	 */
	public void setUserPhotoCover(Long userPhotoCover) {
		this.userPhotoCover = userPhotoCover;
	}

	/**
	 * @return the userPhotoProfile
	 */
	public Long getUserPhotoProfile() {
		return userPhotoProfile;
	}

	/**
	 * @param userPhotoProfile the userPhotoProfile to set
	 */
	public void setUserPhotoProfile(Long userPhotoProfile) {
		this.userPhotoProfile = userPhotoProfile;
	}

	

	/*
	 * // bi-directional one-to-one association to LoginEntity
	 * 
	 * @OneToOne(mappedBy = "user", fetch=FetchType.LAZY) private LoginEntity login;
	 */

	// bi-directional many-to-one association to post
	@OneToMany(mappedBy = "postAuthor", fetch=FetchType.LAZY)
	private List<PostEntity> postEntities;
	
	// bi-directional many-to-one association to messages
	@OneToMany(mappedBy = "messageAuthor", fetch=FetchType.LAZY)
	private List<MessageEntity> messagesEntities;




	/**
	 * @return the postEntities
	 */
	public List<PostEntity> getPostEntities() {
		return postEntities;
	}

	/**
	 * @param postEntities the postEntities to set
	 */
	public void setPostEntities(List<PostEntity> postEntities) {
		this.postEntities = postEntities;
	}

	// bi-directional many-to-many association to ConversationEntity
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "COVERSATIONS_USER", joinColumns = { @JoinColumn(name = "conversation_user_userid") }, inverseJoinColumns = { @JoinColumn(name = "conversation_user_thread") })
	private List<ConversationEntity> conversations;

	// bi-directional one-to-one association to UserRoleEntity
	@OneToOne
	@JoinColumn(name = "user_id")
	private UserRoleEntity userRole;

	public UserEntity() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserCourse() {
		return this.userCourse;
	}

	public void setUserCourse(String userCourse) {
		this.userCourse = userCourse;
	}

	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserGrade() {
		return this.userGrade;
	}

	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}

	public String getUserLastname() {
		return this.userLastname;
	}

	public void setUserLastname(String userLastname) {
		this.userLastname = userLastname;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNationality() {
		return this.userNationality;
	}

	public void setUserNationality(String userNationality) {
		this.userNationality = userNationality;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPresentation() {
		return this.userPresentation;
	}

	public void setUserPresentation(String userPresentation) {
		this.userPresentation = userPresentation;
	}

	public String getUserSession() {
		return this.userSession;
	}

	public void setUserSession(String userSession) {
		this.userSession = userSession;
	}

	public String getUserSignedDate() {
		return this.userSignedDate;
	}

	public void setUserSignedDate(String userSignedDate) {
		this.userSignedDate = userSignedDate;
	}

	/*
	 * public LoginEntity getLogin() { return this.login; }
	 * 
	 * public void setLogin(LoginEntity login) { this.login = login; }
	 */

	public List<ConversationEntity> getConversations() {
		return this.conversations;
	}

	public void setConversations(List<ConversationEntity> conversations) {
		this.conversations = conversations;
	}

	public UserRoleEntity getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRoleEntity userRole) {
		this.userRole = userRole;
	}

}