package com.app.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.account.dao.AccountDao;
import com.app.account.service.AccountService;
import com.app.user.vo.UserVO;

/**
 * AccountServiceImpl
 * @author seongilman
 *
 */
@Service("AccountService")
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	/**
	 * insertUserInfo
	 * 유저 등록
	 * @param userVO
	 * @throws Exception
	 */
	public void insertUserInfo(UserVO userVO) throws Exception {
		userVO.setPassword(bcryptPasswordEncoder.encode(userVO.getPassword()));
		accountDao.insertUserInfo(userVO);
		accountDao.insertUserRole(userVO.getUsername());
	}
	
	/**
	 * selectUserInfo
	 * 유저 정보 조회
	 * @param username
	 * @return UserVO
	 * @throws Exception
	 */
	@Override
	public UserVO selectUserInfo(String username) throws Exception {
		UserVO userVO = accountDao.selectUserInfo(username);
		return userVO;
	}
	
	/**
	 * selectIsExistUser
	 * 유저 존재 유무
	 * @param username
	 * @return boolean
	 * @throws Exception
	 */
	@Override
	public boolean selectIsExistUser(String username) throws Exception {
		return 1 > accountDao.selectIsExistUser(username);
	}
	
	/**
	 * updateUserEnabled
	 * 유저 활성화
	 * @param userVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateUserEnabled(UserVO userVO) throws Exception{
		return accountDao.updateUserEnabled(userVO);
	}
	
	/**
	 * updateUserAuthToken
	 * 유저 인증 토큰 갱신
	 * @param userVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateUserAuthToken(UserVO userVO) throws Exception{
		return accountDao.updateUserAuthToken(userVO);
	}

}
