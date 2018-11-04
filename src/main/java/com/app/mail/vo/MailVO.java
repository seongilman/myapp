package com.app.mail.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * MailVO
 * @author Seong
 * @create 2017. 02. 05
 */
public class MailVO {
	
	/** 보낸 사람 */
	private String from;
	/** 받는 사람 */
	private String to;
	/** 제목 */
	private String subject;
	/** 맵핑 파라미터 map */
	private Map<String, String> paramMap = new HashMap<String, String>();
	/** 메일 템플릿 URL(/resource/mail/sample.mail) */
	private MailTemplate mailTemplateURL;
	/** 첨부파일 경로 */
	private String attachFilePath;
	/** 첨부파일명 */
	private String attachFileName = "text.txt";
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Map<String, String> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String, String> paramMap) {
		this.paramMap = paramMap;
	}
	public MailTemplate getMailTemplateURL() {
		return mailTemplateURL;
	}
	public void setMailTemplateURL(MailTemplate mailTemplateURL) {
		this.mailTemplateURL = mailTemplateURL;
	}
	public String getAttachFilePath() {
		return attachFilePath;
	}
	public void setAttachFilePath(String attachFilePath) {
		this.attachFilePath = attachFilePath;
	}
	public String getAttachFileName() {
		return attachFileName;
	}
	public void setAttachFileName(String attachFileName) {
		this.attachFileName = attachFileName;
	}
}
