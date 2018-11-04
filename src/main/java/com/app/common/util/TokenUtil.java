package com.app.common.util;

import java.security.SecureRandom;

/**
 * TokenUtil
 * @author seongilman
 * @create 2017. 05. 09
 */
public class TokenUtil {
	
	protected static SecureRandom random = new SecureRandom();
    
	/**
	 * generateToken
	 * 토큰 생성
	 * @return String
	 */
    public static String generateToken() {
            long longToken = Math.abs(random.nextLong());
            String random = Long.toString( longToken, 16 );
            return random;
    }
    
    /**
     * generateToken
     * 토큰 생성
     * @param username
     * @return String
     */
    public static String generateToken(String username) {
    	long longToken = Math.abs(random.nextLong());
    	String random = Long.toString( longToken, 16 );
    	return ( username + ":" + random );
    }
}
