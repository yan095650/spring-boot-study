package com.ykp.springbootdemo.controller;

import com.ykp.service.user.model.User;
import com.ykp.springbootdemo.manager.ServiceManager;
import com.ykp.springbootdemo.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Component
public class BaseController {
	
	public static String TOKEN_NAME;
	
	public static String WEB_USER;
	
	public static Long WEB_USER_TIME_OUT;
	
	@Value( "${common.TOKEN_NAME}" )
	public void setTokenName( String tokenName ) {
		TOKEN_NAME = tokenName;
	}
	
	@Value( "${common.WEB_USER}" )
	public void setWebUser( String webUser ) {
		WEB_USER = webUser;
	}
	
	@Value( "${common.WEB_USER_TIME_OUT}" )
	public void setWebUserTimeOut( Long webUserTimeOut ) {
		WEB_USER_TIME_OUT = webUserTimeOut;
	}
	
	public static String getToken() {
		HttpServletRequest request = ( ( ServletRequestAttributes ) RequestContextHolder.getRequestAttributes() )
				.getRequest();
		return CookieUtil.getCookie( request, TOKEN_NAME );
	}
	
	/**
	 * 保存User对象
	 */
	protected void setUser( String token, User user ) {
		// 最后缓存用户信息
		ServiceManager.redisTemplateService.add( WEB_USER + token, user, WEB_USER_TIME_OUT, TimeUnit.HOURS );
	}
	
	/**
	 * 保存User对象
	 */
	protected void setUser( User user ) {
		// 最后缓存用户信息
		ServiceManager.redisTemplateService.add( WEB_USER + getToken(), user, WEB_USER_TIME_OUT, TimeUnit.HOURS );
	}
	
	/**
	 * 获取User对象
	 */
	public static User getUser() throws Exception {
		User user = ( User ) ServiceManager.redisTemplateService.get( WEB_USER + getToken() );
		return user;
	}
	
	/**
	 * 删除User对象
	 */
	public static void removeUser() throws Exception {
		ServiceManager.redisTemplateService.delete( WEB_USER + getToken() );
	}
}
