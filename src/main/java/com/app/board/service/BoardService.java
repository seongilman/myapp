package com.app.board.service;

import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import com.app.board.vo.BoardVO;

/**
 * BoardService
 * @author seongilman
 *
 */
public interface BoardService {
	
	/**
	 * 게시물 총 카운트
	 * @param catagory
	 * @return int
	 * @throws Exception
	 */
	public int selectBoardListCount(String catagory) throws Exception;
	
	/**
	 * selectBoardList
	 * 게시판 리스트
	 * @param boardVO
	 * @return List<BoardVO>
	 * @throws Exception
	 */
	public List<BoardVO> selectBoardList(BoardVO boardVO) throws Exception;
	
	/**
	 * insertBoard
	 * 게시글 등록
	 * @param boardVO
	 * @return int
	 * @throws Exception
	 */
	public int insertBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * selectBoardContent
	 * 게시물 조회
	 * @param no
	 * @return BoardVO
	 * @throws Exception
	 */
	public BoardVO selectBoardContent(int no) throws Exception;
	
	/**
	 * selectBoardContent2Modify
	 * 수정할 게시물 조회
	 * @PostAuthorize - 리턴되는 값 작성자(BoardVO.writer)와 로그인 된 유저(principal.Username)가 일치할 경우 및 관리자만 인증.
	 * 인증 실패시 403(security-context.xml의 access denied page 페이지)
	 * 
	 * Controller에서 try catch문 작성 시 따로 처리해주어야함.
	 * Exception으로 던지면 시큐리티가 알아서 access denied page으로 던짐
	 * @param no
	 * @return BoardVO
	 * @throws Exception
	 */
	@PostAuthorize("(returnObject.writer == principal.Username) or hasRole('ROLE_ADMIN')")
	public BoardVO selectBoardContent2Modify(int no) throws Exception;
	
	/**
	 * updateBoard
	 * 게시물 수정
	 * @param boardVO
	 * @return int
	 * @throws Exception
	 */
	@PreAuthorize("(#boardVO.writer == principal.Username) or hasRole('ROLE_ADMIN')")
	public int updateBoard(BoardVO boardVO) throws Exception;
	
	/**
	 * insertBoardReply
	 * 답글 등록
	 * @param boardVO
	 * @throws Exception
	 */
	public int insertBoardReply(BoardVO boardVO) throws Exception;
}
