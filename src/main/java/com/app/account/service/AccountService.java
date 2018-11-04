package com.app.account.service;

import com.app.user.vo.UserVO;

/**
 * AccountService
 * @author Seong
 */
public interface AccountService {
	
	/**
	 * 유저 등록
	 * @param userVO
	 * @throws Exception
	 */
	public void insertUserInfo(UserVO userVO) throws Exception;
	
	/**
	 * 유저 정보 조회
	 * @param username
	 * @return UserVO
	 * @throws Exception
	 */
	public UserVO selectUserInfo(String username) throws Exception;
	
	/**
	 * 유저 존재 유무
	 * @param username
	 * @return boolean
	 * @throws Exception
	 */
	public boolean selectIsExistUser(String username) throws Exception;
	
	/**
	 * 유저 활성화
	 * @param userVO
	 * @return int
	 * @throws Exception
	 */
	public int updateUserEnabled(UserVO userVO) throws Exception;
	
	/**
	 * 유저 인증 토큰 갱신
	 * @param userVO
	 * @return int
	 * @throws Exception
	 */
	public int updateUserAuthToken(UserVO userVO) throws Exception;
}
