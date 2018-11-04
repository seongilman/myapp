package com.app.board.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.board.dao.BoardDao;
import com.app.board.vo.BoardVO;

/**
 * BoardDaoImpl
 * @author Seong
 */
@Repository("BoardDao")
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * selectBoardListCount
	 * 게시물 총 카운트
	 * @param catagory
	 * @return int
	 * @throws Exception
	 */
	public int selectBoardListCount(String catagory) throws Exception {
		return sqlSession.selectOne("board.selectBoardListCount", catagory);
	}
	
	/**
	 * selectBoardList
	 * 게시판 리스트
	 * @return List<BoardVO>
	 * @throws Exception
	 */
	public List<BoardVO> selectBoardList(BoardVO boardVO) throws Exception {
		return sqlSession.selectList("board.selectBoardList", boardVO);
	}

	/**
	 * insertBoard
	 * 게시글 등록
	 * @return int
	 * @throws Exception
	 */
	public void insertBoard(BoardVO boardVO) throws Exception {
		sqlSession.insert("board.insertBoard", boardVO);
	}
	
	/**
	 * selectBoardContent
	 * 게시물 조회
	 * @param no
	 * @return BoardVO
	 * @throws Exception
	 */
	public BoardVO selectBoardContent(int no) throws Exception {
		return sqlSession.selectOne("board.selectBoardContent", no);
	}
	
	/**
	 * updateBoard
	 * 게시물 수정
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	public int updateBoard(BoardVO boardVO) throws Exception {
		return sqlSession.update("board.updateBoard", boardVO);
	}

	/**
	 * insertBoardReply
	 * 답글 등록
	 * @param boardVO
	 * @throws Exception
	 */
	public void insertBoardReply(BoardVO boardVO) throws Exception {
		sqlSession.insert("board.insertBoardReply", boardVO);
	}
}
