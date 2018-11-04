package com.app.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.app.board.dao.BoardDao;
import com.app.board.service.BoardService;
import com.app.board.vo.BoardVO;
import com.app.spring.security.SecurityUtils;

/**
 * BoardSerivceImpl
 * @author seongilman
 *
 */
@Service("BoardService")
public class BoardSerivceImpl implements BoardService {

	@Autowired
	BoardDao boardDao;

	/**
	 * 게시물 총 카운트
	 * @param catagory
	 * @return int
	 * @throws Exception
	 */
	@Override
	public int selectBoardListCount(String catagory) throws Exception {
		return boardDao.selectBoardListCount(catagory);
	}
	
	/**
	 * selectBoardList
	 * 게시판 리스트
	 * @param boardVO
	 * @return List<BoardVO>
	 * @throws Exception
	 */
	public List<BoardVO> selectBoardList(BoardVO boardVO) throws Exception {
		return boardDao.selectBoardList(boardVO);
	}

	/**
	 * insertBoard
	 * 게시글 등록
	 * @param boardVO
	 * @return int
	 * @throws Exception
	 */
	public int insertBoard(BoardVO boardVO) throws Exception {
		String writer = SecurityUtils.getCustomUser().getUsername();
		boardVO.setWriter(writer);
		boardDao.insertBoard(boardVO);
		return boardVO.getNo();
	}
	
	/**
	 * selectBoardContent
	 * 게시물 조회
	 * @param no
	 * @return BoardVO
	 * @throws Exception
	 */
	public BoardVO selectBoardContent(int no) throws Exception {
		return boardDao.selectBoardContent(no);
	}
	
	/**
	 * selectBoardContent2Modify
	 * 수정할 게시물 조회
	 * @param no
	 * @return BoardVO
	 * @throws Exception
	 */
	public BoardVO selectBoardContent2Modify(int no) throws Exception {
		return boardDao.selectBoardContent(no);
	}
	
	/**
	 * updateBoard
	 * 게시물 수정
	 * @param boardVO
	 * @return int
	 * @throws Exception
	 */
	public int updateBoard(BoardVO boardVO) throws Exception {
		return boardDao.updateBoard(boardVO);
	}
	
	/**
	 * insertBoardReply
	 * 답글 등록
	 * @param boardVO
	 * @throws Exception
	 */
	public int insertBoardReply(BoardVO boardVO) throws Exception {
		String writer = SecurityUtils.getCustomUser().getUsername();
		boardVO.setWriter(writer);
		boardDao.insertBoardReply(boardVO);
		return boardVO.getNo();
	}
}
