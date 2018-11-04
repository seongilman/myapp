package com.app.mail;

import com.app.user.vo.UserVO;

public interface MailService {
	
	/**
	 * 인증 메일 전송
	 * @param mailVO
	 */
	public void sendAuthMail(UserVO userVO) throws Exception;
}
