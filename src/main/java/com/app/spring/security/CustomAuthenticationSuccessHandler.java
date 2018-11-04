package com.app.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * CustomAuthenticationSuccessHandler
 * Spring Security 로그인 실패 시
 * @author Seong
 * @create 2017. 05. 01
 */
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

		String returnURL = request.getSession().getAttribute("RETURN_URL").toString();
		
		logger.debug("returnURL : " + returnURL);
		
		if(returnURL != null){
			getRedirectStrategy().sendRedirect(request, response, returnURL);
		}else{
			super.onAuthenticationSuccess(request, response, authentication);
		}
		
	}

}
