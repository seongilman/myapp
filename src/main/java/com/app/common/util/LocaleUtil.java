package com.app.common.util;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * LocaleUtil
 * @author seongilman
 * @create 2017. 05. 09
 */
public class LocaleUtil {

	/**
	 * getDefaultLocale
	 * 기본 Locale을 리턴
	 * @return Locale
	 */
    public static Locale getDefaultLocale() {
        return Locale.KOREAN;
    }

    /**
     * getLocale
     * HttpServletRequest에 저장되어 있는 Locale을 리턴한다.
     * 없는 경우는 기본 로케일 리턴
     * @param request
     * @return Locale
     */
    public static Locale getLocale(HttpServletRequest request) {
        Locale locale = null;
        HttpSession session = request.getSession(); 
        locale = (Locale)session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);

        if (locale == null ) {
            locale = getDefaultLocale();
        }
        return locale;
    }

}