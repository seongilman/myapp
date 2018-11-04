package com.app.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.user.vo.UserVO;

/**
 * SecurityUtils
 * @author seongilman
 * @create 2017. 05. 01
 */
public class SecurityUtils {

    protected SecurityUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * getAuthenticatedUser
     * 인증된 유저 정보 취득
     * @return Object
     */
    public static Object getAuthenticatedUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication == null || authentication.getAuthorities() == null || authentication.getAuthorities().size() == 0) {
            return null;
        }

        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails details = (UserDetails) authentication.getPrincipal();
            return details;
        }
        return authentication.getPrincipal();
    }

    /**
     * getCustomUser
     * Login한 Session 정보에서 CustomUser 정보 취득
     * @return CustomUser
     */
    public static CustomUser getCustomUser(){
    	SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication == null || authentication.getAuthorities() == null || authentication.getAuthorities().size() == 0) {
            return null;
        }
        if (authentication.getPrincipal() instanceof CustomUser) {
        	CustomUser details = (CustomUser) authentication.getPrincipal();
            return details;
        }
        return null;
    }

    /**
     * getAuthorities
     * 권한 리스트 취득
     * @return List<String>
     */
    public static List<String> getAuthorities() {
        List<String> listAuth = new ArrayList<String>();

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if (authentication == null || authentication.getAuthorities() == null || authentication.getAuthorities().size() == 0) {
            return null;
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        
        Iterator<? extends GrantedAuthority> authoritiesIterator = authorities.iterator();
        
        while(authoritiesIterator.hasNext()){
        	listAuth.add(authoritiesIterator.next().getAuthority());
        }
        return listAuth;
    }

    /**
     * signInUser
     * 로그인 처리
     * @param userVO
     * @return Authentication
     */
    public static Authentication signInUser(UserVO userVO) {
//        List<GrantedAuthority> roles = getRoles(user);
//        UserDetailsImpl springSecurityUser = new Userdetails(user, roles);
    	
    	List<GrantedAuthority> authorityList = getRoles();
    	CustomUser customUser = new CustomUser(userVO, authorityList);
    	Authentication authentication = new UsernamePasswordAuthenticationToken(customUser, null, authorityList);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(customUser.getUsername(), customUser.getPassword(), authorityList);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public static List<GrantedAuthority> getRoles() {
    	List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
    	roles.add(new GrantedAuthorityImpl("ROLE_USER"));
    	return roles;
    }
    
    public static List<GrantedAuthority> getRoles(User user) {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new GrantedAuthorityImpl("ROLE_USER"));
        return roles;
    }
    
}
