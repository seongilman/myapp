package com.app.file.service;

import com.app.file.vo.FileUploadVO;

/**
 * FileService
 * @author seongilman
 * @create 2017. 05. 11
 */
public interface FileService {

	/**
	 * editorPhotoFileWrite
	 * 파일 업로드 파일 생성
	 * @param username
	 * @param file
	 * @return File Path
	 * @throws Exception
	 */
	public FileUploadVO editorPhotoFileWrite(FileUploadVO fileUploadVO) throws Exception;
}
