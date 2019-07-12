package com.socialceep.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ROLES database table.
 * 
 */
@Entity
@Table(name="ROLES")
@NamedQuery(name="RoleEntity.findAll", query="SELECT r FROM RoleEntity r")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_id")
	private int roleId;

	@Column(name="role_name")
	private String roleName;

	//bi-directional one-to-one association to UserRoleEntity
	@OneToOne(mappedBy="role")
	private UserRoleEntity userRole;

	public RoleEntity() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserRoleEntity getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRoleEntity userRole) {
		this.userRole = userRole;
	}

}