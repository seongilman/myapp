package com.app.spring.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.app.account.service.AccountService;
import com.app.user.vo.UserRole;
import com.app.user.vo.UserVO;

/**
 * 로그인 처리를 위한 UserDetailService 구현
 * @author seongilman
 * @create 2017.05.09
 */
public class UserDetailServiceImple implements UserDetailsService{
	
	@Autowired
	private AccountService loginService;
	
	UserVO userVO = null;
	CustomUser customUser = null;
	List<GrantedAuthority> authorityList = null;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			// 아이디로 회원정보조회
			userVO = loginService.selectUserInfo(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(userVO == null){
			throw new UsernameNotFoundException("User Authentificaiton Failed!");
		}
		
		authorityList = new ArrayList<GrantedAuthority>();
//		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
//		if(userVO.getAdminFlagCode().equals("01020201")){ //관리자
//			authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//		}
//		if(userInfoVO.getAdminFlagCode().equals("01020203")){ //마스터 관리자
//			authorityList.add(new SimpleGrantedAuthority("ROLE_SUPERVISOR"));
//		}
		
		// 조회된 회원의 권한설정
		for(UserRole userRole : userVO.getRole()){
			authorityList.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		
		customUser = new CustomUser(userVO, authorityList);
		
		return customUser;
	}
	
}
