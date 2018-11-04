package com.app.user.vo;

/**
 * UserRole
 * 유저 권한 VO
 * @author seongilman
 * @create 2017. 05. 01
 */
public class UserRole {
	String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserRole [role=" + role + "]";
	}
}
