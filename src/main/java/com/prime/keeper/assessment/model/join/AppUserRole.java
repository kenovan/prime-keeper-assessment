package com.prime.keeper.assessment.model.join;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.prime.keeper.assessment.model.role.AppRole;

@Entity
@Table(name = "app_user_role")
public class AppUserRole {

	@EmbeddedId
	@Column(name = "user_id")
	private int userId;
	
	@EmbeddedId
	@Column(name = "role_id")
	private int roleId;
	
	@OneToOne
	@JoinColumn(name = "role_id", referencedColumnName="id", insertable = false, updatable = false)
	private AppRole appRole;
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public AppRole getAppRole() {
		return appRole;
	}

	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}
	
}
