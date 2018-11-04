package com.app.mail.vo;

/**
 * Mail Template 을 관리하기 위한 Enum
 */
public enum MailTemplate {
	SAMPLE_MAIL_TEMPLATE ("mail/sample.mail"), // Sample Mail Template
	FIND_PASSWORD_TEMPLATE ("mail/findPassword.mail"), //Sample Template
	CAMPAIGN_SUBMITTED_TEMPLATE ("mail/campaignSubmitted.mail"); //Sample Template
	
	private String mailTemplatePath;
	
	private MailTemplate(String mailTemplatePath){
		this.mailTemplatePath = mailTemplatePath;
	}
	
	public String getMailTemplatePath(){
		return mailTemplatePath;
	}
}
