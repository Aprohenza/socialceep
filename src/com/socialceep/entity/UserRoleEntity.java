package com.socialceep.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER_ROLE database table.
 * 
 */
@Entity
@Table(name="USER_ROLE")
@NamedQuery(name="UserRoleEntity.findAll", query="SELECT u FROM UserRoleEntity u")
public class UserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_role_userid")
	private String userRoleUserid;

	//bi-directional one-to-one association to UserEntity
	@OneToOne(mappedBy="userRole")
	private UserEntity user;

	//bi-directional one-to-one association to RoleEntity
	@OneToOne
	@JoinColumn(name="user_role_roleid")
	private RoleEntity role;

	public UserRoleEntity() {
	}

	public String getUserRoleUserid() {
		return this.userRoleUserid;
	}

	public void setUserRoleUserid(String userRoleUserid) {
		this.userRoleUserid = userRoleUserid;
	}

	public UserEntity getUser() {
		return this.user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public RoleEntity getRole() {
		return this.role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

}