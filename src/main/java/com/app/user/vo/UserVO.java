package com.app.user.vo;

import java.util.List;

/**
 * UserVO
 * 유저 정보 VO
 * @author seongilman
 * @create 2017. 05. 01
 */
public class UserVO {
	
	/** 이름 */
	String username;
	/** 비밀번호 */
	String password;
	/** 닉네임 */
	String nickname;
	/** 스프링 시큐리티에서 사용할 활성화 여부 */
	boolean enabled;
	/** 나이 */
	int age;
	/** 유저 권한 */
	List<UserRole> role;
	/** 유저 인증 시 토큰 */
	String authtoken;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<UserRole> getRole() {
		return role;
	}
	public void setRole(List<UserRole> role) {
		this.role = role;
	}
	public String getAuthtoken() {
		return authtoken;
	}
	public void setAuthtoken(String authtoken) {
		this.authtoken = authtoken;
	}
	
	@Override
	public String toString() {
		return "UserVO [username=" + username + ", password=" + password
				+ ", nickname=" + nickname + ", enabled=" + enabled + ", age="
				+ age + ", role=" + role + ", authtoken=" + authtoken + "]";
	}
}
