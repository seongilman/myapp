package com.app.file.vo;

import org.springframework.web.multipart.MultipartFile;

/**
 * FileUploadVO
 * @author seongilman
 *
 */
public class FileUploadVO {
	/** CKEditor가 파일을 전송할 때 자동으로 'CKEditorFuncNum'라는 GET 타입의 데이터를 전송(callback function) */
	private String CKEditorFuncNum;
	private MultipartFile upload;
	private String newFilename;
	private String imageUrl;
	private String message;
	
	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}
	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}
	public MultipartFile getUpload() {
		return upload;
	}
	public void setUpload(MultipartFile upload) {
		this.upload = upload;
	}
	public String getNewFilename() {
		return newFilename;
	}
	public void setNewFilename(String newFilename) {
		this.newFilename = newFilename;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "FileUploadVO [CKEditorFuncNum=" + CKEditorFuncNum + ", upload="
				+ upload + ", newFilename=" + newFilename + ", imageUrl="
				+ imageUrl + ", message=" + message + "]";
	}
}
