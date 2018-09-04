package com.ykp.springbootdemo.filter;

import com.ykp.springbootdemo.controller.BaseController;
import com.ykp.springbootdemo.utils.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * spring-boot里边filter配置方式有两种：
 * 一种是通过bean的形式:在一个bean里边注册FilterRegistrationBean，通过代码的形式注册自定义的过滤器
 * 一种是通过注解形式:在filter的实现类上添加@WebFilter，在项目入口地方添加@ServletComponentScan
 * TOKEN过滤器，主要是通过TOKEN来作为用户唯一标示，登录之后可以用此TOKEN来进行用户相关信息的保存
 */
//@WebFilter( filterName = "tokenFilter", urlPatterns = "/*" )
public class TokenFilter implements Filter {

	@Override
	public void init( FilterConfig filterConfig ) throws ServletException {
		
	}
	
	@Override
	public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException,
			ServletException {
		HttpServletRequest req = ( HttpServletRequest ) request;
		HttpServletResponse resp = ( HttpServletResponse ) response;
		
		// 获取token
		String token = BaseController.getToken();
		
		// 如果token为空，则添加自定义token
		if ( StringUtils.isBlank( token ) ) {
			token = UUID.randomUUID().toString().replaceAll( "-", "" );
			CookieUtil.addCookie( resp, BaseController.TOKEN_NAME, token, 24 * 60 * 60 );
		}
		
		chain.doFilter( request, response );
	}
	
	@Override
	public void destroy() {
		
	}
}
