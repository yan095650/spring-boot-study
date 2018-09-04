package com.ykp.springbootdemo.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * cookie工具类
 */
public class CookieUtil {
	
	/**
	 * 添加cookie 浏览器关闭即失效
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void addCookie( HttpServletResponse response, String name, String value ) {
		addCookie( response, name, value, -1 );
	}
	
	/**
	 * 添加cookie 设置失效时间
	 * @param response
	 * @param name
	 * @param value
	 * @param expire
	 *            时效时间 单位为S 负数即随浏览器关闭而时效， 0立即失效
	 */
	public static void addCookie( HttpServletResponse response, String name, String value, int expire ) {
		try {
			if ( value != null ) {
				value = URLEncoder.encode( value, "UTF-8" );
			}
		} catch ( UnsupportedEncodingException e ) {
			e.printStackTrace();
		}
		Cookie cookie = new Cookie( name, value );
		cookie.setMaxAge( expire );
		cookie.setPath( "/" );
		cookie.setHttpOnly( true );
		response.addCookie( cookie );
	}
	
	/**
	 * 根据name获取cookie值
	 * @param request
	 * @param name
	 * @return
	 */
	public static String getCookie( HttpServletRequest request, String name ) {
		Map<String, Cookie> cookieMap = getCookieMap( request );
		Cookie cookie = cookieMap.get( name );
		if ( null != cookie && cookie.getValue() != null ) {
			try {
				return URLDecoder.decode( cookie.getValue(), "UTF-8" );
			} catch ( UnsupportedEncodingException e ) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 删除cookie
	 * @param response
	 * @param name
	 */
	public static void delCookie( HttpServletResponse response, String name ) {
		addCookie( response, name, null, 0 );
	}
	
	/**
	 * 根据请求request获取所有cookie
	 * @param request
	 * @return
	 */
	public static Map<String, Cookie> getCookieMap( HttpServletRequest request ) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if ( null != cookies ) {
			for ( int i = 0; i < cookies.length; i++ ) {
				cookieMap.put( cookies[ i ].getName(), cookies[ i ] );
			}
		}
		return cookieMap;
	}
	
}
