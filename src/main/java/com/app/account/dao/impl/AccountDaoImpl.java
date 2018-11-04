package com.app.account.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.account.dao.AccountDao;
import com.app.user.vo.UserRole;
import com.app.user.vo.UserVO;

/**
 * AccountDaoImpl
 * @author seongilman
 *
 */
@Repository("AccountDao")
public class AccountDaoImpl implements AccountDao {
	
	@Autowired
	private SqlSession sqlSession;

	/**
	 * 유저 등록
	 * @param userVO
	 * @throws Exception
	 */
	@Override
	public void insertUserInfo(UserVO userVO) throws Exception {
		sqlSession.insert("account.insertUserInfo", userVO);
	}
	
	/**
	 * 유저 권한 등록
	 * @param userVO
	 * @throws Exception
	 */
	@Override
	public void insertUserRole(String username) throws Exception {
		sqlSession.insert("account.insertUserRole", username);
	}
	
	/**
	 * 유저 존재 유무
	 * @param username
	 * @return count
	 * @throws Exception
	 */
	@Override
	public int selectIsExistUser(String username) throws Exception {
		return sqlSession.selectOne("account.selectIsExistUser", username);
	}
	
	/**
	 * 유저 정보 조회
	 * @param username
	 * @return UserVO
	 * @throws Exception
	 */
	@Override
	public UserVO selectUserInfo(String username) throws Exception {
		UserVO userVO = sqlSession.selectOne("account.selectUserInfo", username);
		if(userVO != null){
			List<UserRole> userRole = sqlSession.selectList("account.selectUserRole", username);
			userVO.setRole(userRole);
		}
		return userVO;
	}
	
	/**
	 * 유저 활성화
	 * @param userVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateUserEnabled(UserVO userVO) throws Exception{
		return sqlSession.update("account.updateUserEnabled", userVO);
	}
	
	/**
	 * 유저 인증 토큰 갱신
	 * @param userVO
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int updateUserAuthToken(UserVO userVO) throws Exception{
		return sqlSession.update("account.updateUserAuthToken", userVO);
	}

}
