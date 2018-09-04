package com.ykp.springbootdemo.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationFailure( HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception ) throws IOException, ServletException {
		logger.info( "登录失败" );
		response.setContentType( "application/json;charset=UTF-8" );
		logger.info( objectMapper.writeValueAsString( exception.getMessage() ) );
		response.getWriter().write( objectMapper.writeValueAsString( exception.getMessage() ) );
		response.sendRedirect( "/login.html" );
	}
}
