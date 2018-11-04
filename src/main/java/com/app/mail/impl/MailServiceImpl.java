package com.app.mail.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.app.common.util.EncryptionUtil;
import com.app.mail.MailService;
import com.app.mail.helper.MailHelper;
import com.app.mail.vo.MailTemplate;
import com.app.mail.vo.MailVO;
import com.app.user.vo.UserVO;

/**
 * MailServiceImpl
 * @author Seong
 * @create 2017. 02. 05
 */
@Service("MailService")
public class MailServiceImpl implements MailService {
	
	@Autowired
	private JavaMailSender mailSender; // Spring에서 제공하는 JavaMailSender
	
	@Autowired
	private MailHelper mailHelper;
	
	/**
	 * sendAuthMail
	 * 인증메일 발송
	 * @param UserVO
	 */
	@Async
	public void sendAuthMail(UserVO userVO) throws Exception {
		String encodeUsername = EncryptionUtil.encode(userVO.getUsername());
		
		MailVO mailVO = new MailVO();
		mailVO.setFrom("seongilman@gmail.com");
		mailVO.setTo(userVO.getUsername());
		mailVO.setSubject("[인증번호] 이메일 인증");
		mailVO.setMailTemplateURL(MailTemplate.SAMPLE_MAIL_TEMPLATE);

		Map<String, String> paramMap = new HashMap<String, String>();
		String authURL = "http://seongilman.cafe24.com/myapp/account/authentication/" + encodeUsername + "/" + userVO.getAuthtoken();
		paramMap.put("authURL", authURL);
		paramMap.put("imgPath", "http://seongilman.cafe24.com/myapp/resources/common/image/");
		mailVO.setParamMap(paramMap);
		
		mailHelper.sendMail(mailVO);
	}
}
