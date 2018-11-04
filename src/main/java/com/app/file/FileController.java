package com.app.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.file.service.FileService;
import com.app.file.vo.FileUploadVO;

/**
 * FileController
 * 파일 컨트롤러
 * @author seongilman
 * @create 2017. 05. 11
 */
@RequestMapping("/file")
@Controller
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileService fileService;
	
	/**
	 * ckeditorImageUploads
	 * CKEDITOR 이미지 업로드 (ModelAndView 리턴방식)
	 * @param request
	 * @param upload
	 * @param response
	 * @return ModelAndView
	 * @throws Exception 
	 */
	@RequestMapping(value="/upload/ckeditor", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView ckeditorImageUploads(HttpServletRequest request, HttpServletResponse response
			, @ModelAttribute FileUploadVO fileUploadVO) throws Exception{
		// 파라미터는 @RequestParam MultipartFile upload로 바로 받아와도 되지만, CKEditorFuncNum등 한번에 받아오기 위해 FileUploadVO로 감쌈.
		ModelAndView model = new ModelAndView();
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset-utf-8");
		
		logger.debug("{}", fileUploadVO.toString());
		
		fileUploadVO = fileService.editorPhotoFileWrite(fileUploadVO);
		
		logger.debug("File URL {}", fileUploadVO.getImageUrl());
		
		model.addObject("CKEditorFuncNum", fileUploadVO.getCKEditorFuncNum());
		model.addObject("fileUrl", fileUploadVO.getImageUrl());
		model.addObject("message", fileUploadVO.getMessage());
		
		model.setViewName("/file/ckeditorImageUpload");
		
		return model;
	}
	
	/**
	 * ckeditorImageUploads
	 * CKEDITOR 이미지 업로드	(javascript 리턴)
	 * ckeditorImageUpload.jsp가 필요없이 즉시 페이지에서 실행.
	 * @param request
	 * @param upload
	 * @param response
	 */
	@RequestMapping(value="/upload/ckeditorjs", method = {RequestMethod.GET, RequestMethod.POST})
	public void ckeditorImageUploadsJs(HttpServletRequest request, HttpServletResponse response
			, @RequestParam MultipartFile upload){
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
 
		try{
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
//            String uploadPath = "저장경로/" + fileName;//저장경로
			String uploadPath = "D:/develop/workspace/MyApps/src/main/webapp/resources/images/boast.jpg";
 
			out = new FileOutputStream(new File(uploadPath));
			out.write(bytes);
			String callback = request.getParameter("CKEditorFuncNum");
 
			printWriter = response.getWriter();
//            String fileUrl = "저장한 URL경로/" + fileName;//url경로
			String fileUrl = "http://localhost:8080/myapp/resources/images/boast.jpg";
 
			printWriter.println("<script type='text/javascript'>window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" + fileUrl + "','이미지를 업로드 하였습니다.'"+ ")</script>");
			printWriter.flush();
		}catch(IOException e){
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
        return;
	}

	/**
	 * ckeditorImageUploadList
	 * CKEDITOR 이미지 업로드 탭의 서버보기에서 내가 올린 파일들을 불러온다.
	 * @return ModelAndView
	 */
	@RequestMapping(value="/upload/list", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView ckeditorImageUploadList(){
		ModelAndView model = new ModelAndView();
		
		List<String> uploadList = new ArrayList<String>();
		
		// TODO : 내가 올린 사진 리스트 로직 추가
		
		uploadList.add("http://localhost:8080/myapp/resources/images/boast.jpg");
		uploadList.add("http://localhost:8080/myapp/resources/images/boast.jpg");
		
		model.addObject("uploadList", uploadList);
		model.setViewName("/file/ckeditorUploadList");
		
		return model;
	}
}
