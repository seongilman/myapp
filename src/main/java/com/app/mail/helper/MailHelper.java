package com.app.mail.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.app.mail.vo.MailVO;

/**
 * MailHelper
 * @author seongilman
 *
 */
@Component
public class MailHelper {
	
	@Autowired
	private JavaMailSender mailSender; // Spring에서 제공하는 JavaMailSender

	/**
	 * sendMail
	 * 메일 발송
	 * 	1. simpleMailMessage : 오로지 String으로 메일을 보낼 때 사용한다. (이 폼으로 html을 보내면 다 깨진다! 조심)
	 * 2. MimeMessage : html, 첨부파일등 다양한 폼의 메일을 보낼 때 사용한다.
	 * @param mailVO
	 * @throws Exception
	 */
	public void sendMail(MailVO mailVO) throws Exception{
		try {
			/**
			 * velocity를 이용하면 html을 읽어들여 작업가능.
			 * 해당 메서드는  별도의 플러그인 없이 HTML 템플릿의 #변수#를 mailVO 파라미터 변수 값으로 replace한다.
			 */
			// 메일 템플릿 경로(resource/mail/mailTest.mail)
			URL htmlUrl = getTemplateUrl(mailVO.getMailTemplateURL().getMailTemplatePath()); // 메일 template 경로 획득
			String htmlText = readFile(htmlUrl.getFile());
			String html = getMessageMaping(mailVO.getParamMap(), htmlText); // 메일 내용 맵핑
			
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	 
			messageHelper.setFrom(mailVO.getFrom());  // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(mailVO.getTo());     // 받는사람 이메일
			messageHelper.setSubject(mailVO.getSubject()); // 메일제목은 생략이 가능하다
			messageHelper.setText(html, true);  // 메일 내용
	      
	      if(mailVO.getAttachFilePath() != null){	// 파일첨부
	    	  FileSystemResource fsr = new FileSystemResource(mailVO.getAttachFilePath());
	    	  messageHelper.addAttachment(mailVO.getAttachFileName(), fsr);
	      }
//	      Transport.send(msg);
	      mailSender.send(message);
	    } catch(Exception e){
	    	e.printStackTrace();
	      System.out.println(e);
	    }
	}
	
	private String readFile(String path) throws IOException {
		BufferedReader reader = null;
		StringBuilder builder = new StringBuilder();
		try {
//			reader = new BufferedReader(new FileReader(new File(path)));
			reader = new BufferedReader(new InputStreamReader((new FileInputStream(new File(path))), "UTF-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException ex) {}
			}
		}
		return builder.toString();
	};
	
	/**
	 * <pre>
	 * getTemplateUrl
	 * 템플릿 경로 가져오기
	 * <pre>
	 * @param templatePath
	 * @return
	 */
	private URL getTemplateUrl(String templatePath) {
		URL url = this.getClass().getClassLoader().getResource(templatePath);
		return url;
	};
	
	/**
	 * <pre>
	 * getMessageMaping
	 * 문자열 맵핑 후 html 반환
	 * html 템플릿의 #변수# 를 파라미터의 변수값으로 치환
	 * ex) "#name# -> "seongilman"
	 * <pre>
	 * @param paramMap
	 * @param htmlText
	 * @return
	 */
	private String getMessageMaping(Map<String, String> paramMap, String htmlText) {
		if (paramMap == null) {
			return htmlText;
		}
		
		Iterator<String> iterator = paramMap.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			String value = paramMap.get(key);
			htmlText = htmlText.replace("#" + key + "#", value);
		}

		return htmlText;
	}
}
