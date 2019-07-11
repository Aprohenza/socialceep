package com.formbean.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LOGIN database table.
 * 
 */
@Entity
@Table(name="LOGIN")
@NamedQueries({
	@NamedQuery(name="LoginEntity.findAll", query="SELECT l FROM LoginEntity l"),
	@NamedQuery(name="LoginEntity.authenticateLogin", query="SELECT l FROM LoginEntity l WHERE l.loginEmail=:loginEmail AND l.loginPassword=:loginPassword")
}) 
public class LoginEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="login_id")
	private String loginId;

	@Column(name="login_email")
	private String loginEmail;

	@Column(name="login_last_date_connection")
	private String loginLastDateConnection;

	@Column(name="login_password")
	private String loginPassword;

	@Column(name="login_status")
	private int loginStatus;

	//bi-directional one-to-one association to UserEntity
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="login_id")
	private UserEntity user;

	public LoginEntity() {
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getLoginEmail() {
		return this.loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getLoginLastDateConnection() {
		return this.loginLastDateConnection;
	}

	public void setLoginLastDateConnection(String loginLastDateConnection) {
		this.loginLastDateConnection = loginLastDateConnection;
	}

	public String getLoginPassword() {
		return this.loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public int getLoginStatus() {
		return this.loginStatus;
	}

	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}

	public UserEntity getUser() {
		return this.user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}