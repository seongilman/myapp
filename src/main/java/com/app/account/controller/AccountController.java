package com.app.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.account.service.AccountService;
import com.app.common.util.EncryptionUtil;
import com.app.common.util.TokenUtil;
import com.app.mail.MailService;
import com.app.spring.security.SecurityUtils;
import com.app.user.vo.UserVO;

/**
 * AccountController
 * @author Seong
 * @create 2016. 10. 23
 */
@Controller
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * 웰컴
	 * @return
	 */
	@RequestMapping(value = {"/welcome" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

	  ModelAndView model = new ModelAndView();
	  
	  logger.info("Login Users", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
	
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This is default page!");
	  model.setViewName("hello");
	  return model;

	}

	/**
	 * 어드민
	 * @return
	 */
	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

	  ModelAndView model = new ModelAndView();
	  model.addObject("title", "Spring Security Login Form - Database Authentication");
	  model.addObject("message", "This page is for ROLE_ADMIN only!");
	  model.setViewName("admin");
	  
	  return model;

	}

	/**
	 * login
	 * 로그인
	 * @param request
	 * @param error
	 * @param logout
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/account/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout
			, @RequestParam(value = "username", required = false) String username) {
		ModelAndView model = new ModelAndView();
		
		if(username != null){
			model.addObject("username", username);
			model.addObject("certified", "NOT_CERTIFIED");
			model.addObject("message", "이메일 인증이 되지 않았습니다. 이메일 인증을 해주세요.");
			model.setViewName("/account/authentication");
			return model;
		}
		
		if (error != null) {
			model.addObject("error", error);
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}

		String referer = request.getHeader("Referer");
		String returnURL = "/";
		if(referer != null && !referer.contains("login") && !referer.contains("logout")
				&& referer != null && !referer.contains("join")){
			returnURL = referer;
		}
	  
		request.getSession().setAttribute("RETURN_URL", returnURL);
		model.setViewName("/account/login");

		return model;
	}

	/**
	 * For 403 Access Denied Page
	 * 권한없을 경우 403 페이지
	 * @return
	 */
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
		ModelAndView model = new ModelAndView();

		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("403");
		
		return model;
	}
	
	/**
	 * 회원가입 페이지
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/account/join", method = RequestMethod.GET)
	public ModelAndView memberJoinPage() throws Exception {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("/account/join");
		return model;
	}
	
	/**
	 * memberJoin
	 * 회원가입
	 * @param request
	 * @param userVO
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/account/join", method = RequestMethod.POST)
	public ModelAndView memberJoin(HttpServletRequest request, @ModelAttribute UserVO userVO) {
		ModelAndView model = new ModelAndView();
		
		try {
			String token = TokenUtil.generateToken();
			userVO.setAuthtoken(token);
			
			accountService.insertUserInfo(userVO);
			mailService.sendAuthMail(userVO);
			
			// 아이디 암호화
			String encodeUsername = EncryptionUtil.encode(userVO.getUsername());
			
			model.addObject("username", encodeUsername);
			model.addObject("certified", "JOIN_SUCCESS");
			model.addObject("message", "회원가입이 완료되었습니다. 이메일 인증이 필요합니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.setViewName("/account/authentication");
		
		return model;
	}
	
	/**
	 * memberIsExist
	 * 유저 아이디 중복 조회
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/account/exist", method = RequestMethod.POST)
	@ResponseBody
	public Boolean memberIsExist(@RequestParam("username") String username) throws Exception {
		logger.debug("username : {}", username);
		return accountService.selectIsExistUser(username);
	}
	
	/**
	 * authentication
	 * 회원가입 시 발송한 인증메일을 통해 들어온 유저아이디, 토큰으로
	 * 해당 유저 활성화 USER Table Enabled = 1
	 * @param request
	 * @param username
	 * @param token
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/account/authentication/{username}/{token}", method = RequestMethod.GET)
	public ModelAndView authentication(HttpServletRequest request
			, @PathVariable String username
			, @PathVariable String token) throws Exception{
		ModelAndView model = new ModelAndView();
		
		String decodeUsername = EncryptionUtil.decode(username);
		
		model.setViewName("/account/authentication");
		
		UserVO userVO = new UserVO();
		userVO.setUsername(decodeUsername);
		userVO.setAuthtoken(token);
		
		int successCount = accountService.updateUserEnabled(userVO);
		
		logger.debug("authentication info {}", userVO.toString());
		logger.debug("authentication update {}", successCount);
		
		if(0 < successCount){
			userVO = accountService.selectUserInfo(decodeUsername);
			// 활성화 후 자동 로그인 처리
			SecurityUtils.signInUser(userVO);
			
			model.addObject("certified", "SUCCESS");
			model.addObject("message", "인증이 완료되었습니다.");
		}else{
			model.addObject("certified", "ERROR");
			model.addObject("username", username);
			model.addObject("error", "인증이 실패했습니다.");
		}
		
		return model;
	}
	
}
