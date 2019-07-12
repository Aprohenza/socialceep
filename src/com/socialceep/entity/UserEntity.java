package com.socialceep.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the USERS database table.
 * 
 */
@Entity
@Table(name = "USERS")
@NamedQueries({ @NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u"),
		@NamedQuery(name = "UserEntity.findUsersWithMatch", query = "SELECT u FROM UserEntity u WHERE u.userName LIKE :userMatch OR u.userLastname LIKE :userMatch") })

public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "user_lastname")
	private String userLastname;

	@Column(name = "user_gender")
	private String userGender;

	@Column(name = "user_nationality")
	private String userNationality;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_phone")
	private String userPhone;

	@Column(name = "user_presentation")
	private String userPresentation;
	
	@Column(name = "user_cycle")
	private Integer userCycle;	

	@Column(name = "user_year_graduate")
	private String userYearGraduate;

	@Column(name = "user_signed_date")
	private String userSignedDate;

	@Column(name = "user_photo_profile")
	private Long userPhotoProfile;

	@Column(name = "user_photo_cover")
	private Long userPhotoCover;

	// ASSOCIALTIONS

	// bi-directional one-to-one association to UserRoleEntity
	@OneToOne
	@JoinColumn(name = "user_id")
	private UserRoleEntity userRole;

	// bi-directional many-to-one association to post
	@OneToMany(mappedBy = "postAuthor", fetch = FetchType.LAZY)
	private List<PostEntity> postEntities;
	
	@ManyToOne
	@JoinColumn(name = "user_cycle", insertable = false, updatable = false)
	private CycleCurseSessionEntity cycleCurseSessionEntity;

	// bi-directional many-to-one association to messages
	@OneToMany(mappedBy = "messageAuthor", fetch = FetchType.LAZY)
	private List<MessageEntity> messagesEntities;

	// bi-directional many-to-many association to ConversationEntity
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
	private List<ConversationEntity> conversations;

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

	public UserEntity() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserGender() {
		return this.userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
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
	
	public Integer getUserCycle() {
		return userCycle;
	}

	public void setUserCycle(Integer userCycle) {
		this.userCycle = userCycle;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public CycleCurseSessionEntity getCycleCurseSessionEntity() {
		return cycleCurseSessionEntity;
	}

	public void setCycleCurseSessionEntity(CycleCurseSessionEntity cycleCurseSessionEntity) {
		this.cycleCurseSessionEntity = cycleCurseSessionEntity;
	}

	public String getUserYearGraduate() {
		return userYearGraduate;
	}

	public void setUserYearGraduate(String userYearGraduate) {
		this.userYearGraduate = userYearGraduate;
	}

}