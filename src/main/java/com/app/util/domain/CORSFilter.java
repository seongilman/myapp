package com.app.util.domain;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * SimpleCORSFilter
 * 크로스 도메인 필터 설정
 * 크로스 도메인은 실제 서버에서 해결해주는 것이 표준화된 방법이고, 100% 해결 가능한 방법
 *
 * web.xml에서 크로스 도메인 필터 설정.
 * 참고 http://ooz.co.kr/232
 * @author Seong
 * @create 2017. 04. 17
 */
@Component
public class CORSFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(CORSFilter.class);
 
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        
        logger.info("START CORSFilter set up");
        
        // POST, GET, OPTIONS, DELETE 요청에 대해 허용하겠다는 의미
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        // Access-Control-Max-Age 는 Preflight request를 캐시할 시간입니다. 단위는 초단위이며, 3,600초는 1시간입니다. Preflight Request를 웹브라우저에 캐시한다면 최소 1시간동안에는 서버에 재 요청하지 않을 것
        response.addHeader("Access-Control-Max-Age", "3600");
        // 표준화된 규약은 아니지만, 보통 AJAX 호출이라는 것을 의미하기 위해 비공식적으로 사용되는 절차
        response.addHeader("Access-Control-Allow-Headers", "x-requested-with");
        // 이 부분이 가장 중요한 부분입니다. * 는 모든 도메인에 대해 허용하겠다는 의미입니다. 즉 어떤 웹사이트라도 이 서버에 접근하여 AJAX 요청하여 결과를 가져갈 수 있도록 허용하겠다는 의미입니다.
        // 만약 보안 이슈가 있어서 특정 도메인만 허용해야 한다면 * 대신 특정 도메인만을 지정할 수 있습니다.
        // response.addHeader("Access-Control-Allow-Origin", "http://www.xxx.co.kr");
        response.addHeader("Access-Control-Allow-Origin", "*");

        chain.doFilter(req, res);
        
        logger.info("END CORSFilter set up");
    }
    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}