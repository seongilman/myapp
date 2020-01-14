package com.app.file.service.impl;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.common.util.DateUtil;
import com.app.file.service.FileService;
import com.app.file.vo.FileUploadVO;
import com.app.spring.security.SecurityUtils;

/**
 * FileServiceImpl
 * @author seongilman
 * @create 2017. 05. 11
 */
@Service("FileService")
public class FileServiceImpl implements FileService {

	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	/** 이미지 업로드 디렉토리 */
	@Value("#{code['image.upload.path']}")
	private String IMAGE_UPLOAD_PATH;

	/** 게시판 디렉토리  */
	@Value("#{code['image.upload.dir.board']}")
	private String IMAGE_UPLOAD_DIR_BOARD;

	/** 외부에서 접근할 업로드 URL */
	@Value("#{code['image.upload.url']}")
	private String UPLOAD_FILE_URL;
	
	@Value("#{code['code.dot']}")
	private String DOT_STRING;
	
	@Value("#{code['code.path.separator']}")
	private String PATH_SEPARATOR;

	/**
	 * editorPhotoFileWrite
	 * 파일 업로드 파일 생성
	 * server.xml에 <Context docBase="D:|data|images|upload" path="/myapp/images/upload" reloadable="true"/> 설정 필요
	 * @param userId
	 * @param file
	 * @return File Path
	 * @throws Exception
	 */
	@Override
	public FileUploadVO editorPhotoFileWrite(FileUploadVO fileUploadVO) throws Exception {
		try {
			String usernameDir = SecurityUtils.getCustomUser().getUsername().split("@")[0];	
			// 업로드 경로/유저아이디/게시판 디렉토리
			String saveDir = IMAGE_UPLOAD_PATH + PATH_SEPARATOR + usernameDir + PATH_SEPARATOR + IMAGE_UPLOAD_DIR_BOARD;
			
			logger.info("UPLOAD PATH [{}]", saveDir);
			
			File uploadDir = new File(saveDir);
				
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			
			String extension = fileUploadVO.getUpload().getOriginalFilename().substring(fileUploadVO.getUpload().getOriginalFilename().indexOf(DOT_STRING) + 1, fileUploadVO.getUpload().getOriginalFilename().length()).toLowerCase();
			String fileNm = DateUtil.formatDate(null, DateUtil.FM_PATTEN_YYYYMMDDHHMISSSSS) + DOT_STRING + extension;				
			File uploadFile = new File(saveDir + PATH_SEPARATOR + fileNm);
			fileUploadVO.getUpload().transferTo(uploadFile);

//			PhotoCommonInfo photoCommonInfo = new PhotoCommonInfo();
//			photoCommonInfo.setPhotoUrl(web_editor_file_url_prefix + userId + CCI.PATH_SEPARATOR_STRING + fileNm);
//			
//			this.thumbnailCreation(uploadFile, photoCommonInfo);
			
			String uploadFileUrl = UPLOAD_FILE_URL + PATH_SEPARATOR + usernameDir + IMAGE_UPLOAD_DIR_BOARD + PATH_SEPARATOR + fileNm;
			fileUploadVO.setImageUrl(uploadFileUrl);
			fileUploadVO.setMessage("success image upload");
			
		} catch (IOException e) {
			e.printStackTrace();
			fileUploadVO.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			fileUploadVO.setMessage(e.getMessage());
		}
		return fileUploadVO;
	}

}
