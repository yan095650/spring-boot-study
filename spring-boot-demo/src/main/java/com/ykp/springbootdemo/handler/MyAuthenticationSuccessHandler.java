package com.ykp.springbootdemo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/**
	 * 登录成功之后的操作
	 */
	@Override
	public void onAuthenticationSuccess( HttpServletRequest request, HttpServletResponse response,
			Authentication authentication ) throws IOException, ServletException {
		logger.info( "登录成功" );
		response.setContentType( "application/json;charset=UTF-8" );
		logger.info( objectMapper.writeValueAsString( authentication ) );
//		response.getWriter().write( objectMapper.writeValueAsString( authentication ) );
		User user = ( User ) authentication.getPrincipal();
		response.sendRedirect( "/index.html" );
	}
}
