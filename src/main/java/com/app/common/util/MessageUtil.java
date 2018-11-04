package com.app.common.util;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * MessageUtil
 * @author seongilman
 * @create 2017. 05. 09
 */
public class MessageUtil implements MessageSourceAware {
    
    private static MessageSource msg;

    public void setMessageSource (MessageSource msg) {
    	MessageUtil.msg = msg;
    }
    
    /**
     * 시스템 속성을 얻기위한 함수
     * 호출 내용은 기존 메시지와 동일하나 기능적으로 구분하기 위해서 추가한 메소드 이다. 
     */
    public static String prop(String key) {
    	return MessageUtil.msg(key);
    }
    
    public static String prop(String key, Locale locale) {
    return MessageUtil.msg(key, locale);
    }
    
    public static String propFormat(String key, Object...objects) {
         return MessageFormat.format(MessageUtil.msg(key),objects);
    }
    
    public static String propFormat(String key, Locale locale, Object...objects) {
        return MessageFormat.format(MessageUtil.msg(key, locale),objects);
    }
    
    /**
     * msg
     * code에 해당하는 메시지를 가져온다.
     * @param key
     * @return String
     */
    public static String msg(String code){
        return msg.getMessage(code, null, Locale.getDefault());
    }
    
    /**
     * code에 해당하는 메시지를 가져온다.
     * @param code
     * @param locale
     * @return String
     */
    public static String msg(String code, Locale locale){
        return msg.getMessage(code, null, locale);
    }
}