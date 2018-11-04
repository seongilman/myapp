package com.app.board.dao;

import java.util.List;

import com.app.board.vo.BoardVO;

/**
 * BoardDao
 * @author seongilman
 *
 */
public interface BoardDao {
	
	/**
	 * selectBoardListCount
	 * 게시물 총 카운트
	 * @param catagory
	 * @return int
	 * @throws Exception
	 */
	public int selectBoardListCount(String catagory) throws Exception;
	
	/**
	 * selectBoardList
	 * 게시판 리스트
	 * @return List<BoardVO>
	 * @throws Exception
	 */
	public List<BoardVO> selectBoardList(BoardVO boardVO) throws Exception;
	
	/**
	 * insertBoard
	 * 게시글 등록
	 * @return int
	 * @throws Exception
	 */
	public void insertBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * selectBoardContent
	 * 게시물 조회
	 * @param no
	 * @return BoardVO
	 * @throws Exception
	 */
	public BoardVO selectBoardContent(int no) throws Exception;
	
	/**
	 * updateBoard
	 * 게시물 수정
	 * @param boardVO
	 * @return
	 * @throws Exception
	 */
	public int updateBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * insertBoardReply
	 * 답글 등록
	 * @param boardVO
	 * @throws Exception
	 */
	public void insertBoardReply(BoardVO boardVO) throws Exception;
}
