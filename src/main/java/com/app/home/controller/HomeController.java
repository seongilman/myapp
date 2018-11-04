package com.app.home.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.app.board.service.BoardService;
import com.app.board.vo.BoardVO;
import com.app.mail.MailService;
import com.app.user.vo.UserVO;

/**
 * HomeController
 * @author Seong
 * @create 2016. 10. 23
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private MailService mailService;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	private BoardService boardService;
	
	@Value("#{code['code.response.error']}")
	private String NAME;
	
	/**
	 * home
	 * @param locale
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home(Model model, BoardVO boardVO) throws Exception {
		ModelAndView mv = new ModelAndView();
//		System.out.println(messageSource.getMessage("message.param", new String[]{"첫번째", "두번째"}, LocaleUtil.getLocale(request)));
		
		try {
			List<BoardVO> boardList = boardService.selectBoardList(boardVO);
			mv.addObject("boardList", boardList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("home");
		
		return mv;
	}
	
	/**
	 * Json Test
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 */
	@RequestMapping(value = "/jsonSample", method = RequestMethod.GET)
	@ResponseBody
	public UserVO getUserInfomation() throws Exception{
		UserVO userVO = new UserVO();
		userVO.setUsername("seongilman");
		userVO.setAge(30);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValueAsString(userVO);
		return userVO;
	}
	
}
