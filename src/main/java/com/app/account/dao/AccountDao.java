package com.app.account.dao;

import com.app.user.vo.UserVO;

/**
 * MemberDao
 * @author Seong
 */
public interface AccountDao {
	
	/**
	 * insertUserInfo
	 * 유저 등록
	 * @param userVO
	 * @throws Exception
	 */
	public void insertUserInfo(UserVO userVO) throws Exception;
	
	/**
	 * insertUserRole
	 * 유저 권한 등록
	 * @param userVO
	 * @throws Exception
	 */
	public void insertUserRole(String username) throws Exception;
	
	/**
	 * selectIsExistUser
	 * 유저 존재 유무
	 * @param username
	 * @return count
	 * @throws Exception
	 */
	public int selectIsExistUser(String username) throws Exception;
	
	/**
	 * selectUserInfo
	 * 유저 정보 조회
	 * @param username
	 * @return UserVO
	 * @throws Exception
	 */
	public UserVO selectUserInfo(String username) throws Exception;
	
	/**
	 * 유저 활성화
	 * updateUserEnabled
	 * @param userVO
	 * @return int
	 * @throws Exception
	 */
	public int updateUserEnabled(UserVO userVO) throws Exception;
	
	/**
	 * updateUserAuthToken
	 * 유저 인증 토큰 갱신
	 * @param userVO
	 * @return int
	 * @throws Exception
	 */
	public int updateUserAuthToken(UserVO userVO) throws Exception;
}
