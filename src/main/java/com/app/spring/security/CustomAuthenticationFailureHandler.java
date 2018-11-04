package com.app.spring.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.security.auth.login.CredentialExpiredException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.app.common.util.EncryptionUtil;
import com.app.common.util.LocaleUtil;
import com.app.common.util.MessageUtil;

/**
 * CustomAuthenticationFailureHandler
 * Spring Security 로그인 실패 시
 * @author Seong
 * @create 2017. 05. 01
 */
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		
		String error = "";
		if(exception.getClass().equals(BadCredentialsException.class)){	// 비밀번호 오류
			error = MessageUtil.msg("error.badCredentials", LocaleUtil.getLocale(request));
		}else if(exception.getClass().equals(DisabledException.class)){	// 계정비활성화
			error = MessageUtil.msg("error.disabled", LocaleUtil.getLocale(request));
			String username = request.getParameter("username");
			
			try{
				
				String encodeUsername = EncryptionUtil.encode(username);
				response.sendRedirect(request.getContextPath() + "/account/login?error="+URLEncoder.encode(error, "UTF-8") + "&username=" + encodeUsername);
				return;
			}catch(Exception e){
				
			}
			return;
		}else if(exception.getClass().equals(AccountExpiredException.class)){	// 계정만료
			error = MessageUtil.msg("error.accountExpired", LocaleUtil.getLocale(request));
		}else if(exception.getClass().equals(CredentialExpiredException.class)){	// 계정권한만료
			error = MessageUtil.msg("error.credentialExpired", LocaleUtil.getLocale(request));
		}else if(exception.getClass().equals(LockedException.class)){	// 계정 잠김
			error = MessageUtil.msg("error.locked", LocaleUtil.getLocale(request));
		}
		response.sendRedirect(request.getContextPath() + "/account/login?error="+URLEncoder.encode(error, "UTF-8"));
	}

}
