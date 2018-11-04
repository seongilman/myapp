package com.app.mail.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.account.service.AccountService;
import com.app.common.util.EncryptionUtil;
import com.app.common.util.TokenUtil;
import com.app.mail.MailService;
import com.app.user.vo.UserVO;

/**
 * MailController
 * @author Seong
 * @create 2017. 05. 12
 */
@RequestMapping("mail")
@Controller
public class MailController {

	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Autowired
	private AccountService memberService;
	
	@Autowired
	private MailService mailService;
	
	/**
	 * sendAuthentication
	 * 인증메일 발송
	 * @param request
	 * @param userVO
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	public ModelAndView sendAuthentication(HttpServletRequest request, @ModelAttribute UserVO userVO) {
		ModelAndView model = new ModelAndView();
		
		logger.debug("sendAuthentication userVO {}" + userVO.toString());
		
		try {
			String token = TokenUtil.generateToken();
			String decodeUsername = EncryptionUtil.decode(userVO.getUsername());
			userVO.setUsername(decodeUsername);
			userVO.setAuthtoken(token);
			
			memberService.updateUserAuthToken(userVO);
			mailService.sendAuthMail(userVO);
			
			String encodeUsername = userVO.getUsername();
			model.addObject("username", encodeUsername);
			model.addObject("certified", "SEND_AUTHENTICATION_CODE");
			model.addObject("message", "인증메일을 전송했습니다. 메일을 확인해주세요.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("/account/authentication");
		return model;
	}
	
}
