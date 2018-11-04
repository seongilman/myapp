package com.app.util.domain;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 다국어(i18n) 설정 시 REST API를 사용하기 위한 Filter
 * @author Seong
 *
 */
public class LocaleUrlFilter implements Filter{

	private static final Pattern localePattern = Pattern.compile("^/([a-z]{2})(?:/([a-z]{2}))?(/.*)?");
	public static final String COUNTRY_CODE_ATTRIBUTE_NAME = LocaleUrlFilter.class.getName() + ".country";
	public static final String LANGUAGE_CODE_ATTRIBUTE_NAME = LocaleUrlFilter.class.getName() + ".language";

	@Override
	public void init(FilterConfig arg0) throws ServletException {}  

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		String url = request.getRequestURI().substring(request.getContextPath().length());
		Matcher matcher = localePattern.matcher(url);
		
		if (matcher.matches()) {
			// Set the language attributes that we will use in LocaleResolver and strip the language from the url
			request.setAttribute(COUNTRY_CODE_ATTRIBUTE_NAME, matcher.group(1));
			request.setAttribute(LANGUAGE_CODE_ATTRIBUTE_NAME, matcher.group(2));
			request.getRequestDispatcher(matcher.group(3) == null ? "/" : matcher.group(3)).forward(servletRequest, servletResponse);
		}else{
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {}
}