package com.app.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.board.service.BoardService;
import com.app.board.vo.BoardVO;
import com.app.common.util.PagingVO;

/**
 * BoardController
 * @author Seong
 * @create 2016. 12. 25
 */
@RequestMapping("/boards")
@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	@Value("#{code['code.response.error']}")
	private String NAME;
	
	/**
	 * 게시판 리스트
	 * @param model
	 * @param category
	 * @return
	 */
	@RequestMapping(value = "/{category}", method = RequestMethod.GET)
	public ModelAndView boardList(Model model, 
			@PathVariable("category") String category, 
			@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo, 
			BoardVO boardVO) {
		ModelAndView mv = new ModelAndView();
		
		logger.info("pageNo {}", pageNo);
		
		try {
			boardVO.setPageNo(pageNo);
			int totalCount = boardService.selectBoardListCount(category);
			PagingVO pagingVO = new PagingVO(boardVO.getPageNo(), totalCount, boardVO.getPageSize(), boardVO.getPageSize());
			List<BoardVO> boardList = boardService.selectBoardList(boardVO);
			
			mv.addObject("category", category);
			mv.addObject("boardList", boardList);
			mv.addObject("pagingVO", pagingVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("/boards/boardList");
		
		return mv;
	}
	
	/**
	 * boardView
	 * 게시물 보기
	 * @param request
	 * @param response
	 * @param category
	 * @param boardNo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/{category}/{boardNo}", method = RequestMethod.GET)
	public ModelAndView boardView(HttpServletRequest request, 
			HttpServletResponse response, 
			@PathVariable("category") String category, @PathVariable("boardNo") int boardNo) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		logger.info("category {}", category);
		logger.info("boardNo {}", boardNo);
		
		BoardVO boardVO = boardService.selectBoardContent(boardNo);
		
		mv.addObject("boardVO", boardVO);
		mv.addObject("category", category);
		
		mv.setViewName("/boards/boardContent");
		
		return mv;
	}
	
	/**
	 * boardWrite
	 * 게시물 등록 페이지
	 * @param model
	 * @param category
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public ModelAndView boardWrite(Model model, 
			@RequestParam(value = "category", required = false, defaultValue = "standard") String category) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("category", category);
		mv.addObject("type", "WRITE");
		mv.setViewName("/boards/boardWrite");

		return mv;
	}
	
	/**
	 * boardWrite
	 * 게시물 등록
	 * @param request
	 * @param response
	 * @param boardVO
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String boardWrite(HttpServletRequest request, HttpServletResponse response, 
			@ModelAttribute BoardVO boardVO) {
		
		logger.debug("BoardVO {}", boardVO.toString());
		
		int boardNo = 0;
		try {
			boardNo = boardService.insertBoard(boardVO);
			logger.debug("insert boardNo {}", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("insertBoard error {}", e.getMessage());
		}
		
		return "redirect:/boards/" + boardVO.getCategory() + "/" + boardNo;
	}
	
	/**
	 * boardModify
	 * 게시물 수정 페이지
	 * @param model
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify/{boardNo}", method = RequestMethod.GET)
	public ModelAndView boardModify(Model model, @PathVariable int boardNo) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		// 해당 메서드는 Service Interface에 스프링 시큐리티 @PostAuthorize설정으로 본인이거나 관리자가 아닐시 security-context.xml에 명시된 access-denied-handler error-page로 이동 
		BoardVO boardVO = boardService.selectBoardContent2Modify(boardNo);
		mv.addObject("boardVO", boardVO);
		mv.addObject("type", "MODIFY");
		mv.setViewName("/boards/boardWrite");

		return mv;
	}
	
	/**
	 * boardModify
	 * 게시물 수정
	 * @param model
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modify/{boardNo}", method = RequestMethod.POST)
	public String boardModify(Model model, @ModelAttribute BoardVO boardVO) throws Exception {
		
		logger.debug("BoardVO {}", boardVO.toString());
		
		int updateCount = boardService.updateBoard(boardVO);
		logger.debug("update Count {}", updateCount);
		return "redirect:/boards/" + boardVO.getCategory() + "/" + boardVO.getNo();
	}
	
	/**
	 * boardModify
	 * 답글 등록 페이지
	 * @param model
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reply/{boardNo}", method = RequestMethod.GET)
	public ModelAndView boardReply(Model model, @PathVariable int boardNo) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		// 해당 메서드는 Service Interface에 스프링 시큐리티 @PostAuthorize설정으로 본인이거나 관리자가 아닐시 security-context.xml에 명시된 access-denied-handler error-page로 이동 
		BoardVO boardVO = boardService.selectBoardContent(boardNo);
		mv.addObject("boardVO", boardVO);
		mv.addObject("type", "REPLY");
		mv.setViewName("/boards/boardWrite");

		return mv;
	}
	
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String boardReply(Model model, @ModelAttribute BoardVO boardVO) throws Exception {
		
		int boardNo = boardService.insertBoardReply(boardVO);
		
		return "redirect:/boards/" + boardVO.getCategory() + "/" + boardNo;
	}
	
}
