package com.app.profile.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.mail.MailService;

/**
 * ProfileController
 * @author Seong
 * @create 2017. 05. 02
 */
@Controller
public class MeProfileController {

	private static final Logger logger = LoggerFactory.getLogger(MeProfileController.class);

	@Autowired
	private MailService mailService;
	
	/**
	 * profile
	 * 프로필로 이동
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/me/profile", method = RequestMethod.GET)
	public ModelAndView profile() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/me/profile");
		
		return mv;
	}
	
}
