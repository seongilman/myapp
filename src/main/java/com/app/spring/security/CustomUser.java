package com.app.spring.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.app.user.vo.UserVO;

/**
 * 로그인 처리후 Session에 관리할 객체 정의
 * @author seongilman
 * @create 2017.05.09
 */
public class CustomUser extends User{
	/** serialVersionUID */
	private static final long serialVersionUID = 5397405779634679770L;
	
	private String username;
	private String password;
	private String nickname;
	private int age;

	public CustomUser(UserVO userVO, Collection<? extends GrantedAuthority> AuthList){
//		super(userVO.getUsername(), userVO.getPassword(), true, true, true, true, AuthList);
		
		// enabled이 true면 인증, false면 미인증 유저.
		super(userVO.getUsername(), userVO.getPassword(), userVO.getEnabled(), true, true, true, AuthList);
		this.username = userVO.getUsername();
		this.password = userVO.getPassword();
		this.nickname = userVO.getNickname();
		this.age = userVO.getAge();
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "CustomUser [username=" + username + ", password=" + password
				+ ", nickname=" + nickname + ", age=" + age + "]";
	}
	
}
