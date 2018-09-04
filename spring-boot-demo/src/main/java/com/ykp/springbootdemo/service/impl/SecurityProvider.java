package com.ykp.springbootdemo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * UsernamePasswordAuthenticationToken继承AbstractAuthenticationToken实现Authentication
 * 所以当在页面中输入用户名和密码之后首先会进入到UsernamePasswordAuthenticationToken验证(Authentication)，
 * 然后生成的Authentication会被交由AuthenticationManager来进行管理
 * 而AuthenticationManager管理一系列的AuthenticationProvider，
 * 而每一个Provider都会通UserDetailsService和UserDetail来返回一个
 * 以UsernamePasswordAuthenticationToken实现的带用户名和密码以及权限的Authentication
 */
@Component
public class SecurityProvider implements AuthenticationProvider {
	
	@Autowired
	private MyUserDetailsServiceImpl userDetailsService;
	
	@Override
	public Authentication authenticate( Authentication authentication ) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = ( UsernamePasswordAuthenticationToken ) authentication;
		UserDetails userDetails = userDetailsService.loadUserByUsername( token.getName() );
		if ( userDetails == null ) {
			throw new UsernameNotFoundException( "找不到该用户" );
		}
		if ( !userDetails.getPassword().equals( token.getCredentials().toString() ) ) {
			throw new BadCredentialsException( "密码错误" );
		}
		return new UsernamePasswordAuthenticationToken( userDetails, userDetails.getPassword(),
				userDetails.getAuthorities() );
	}
	
	@Override
	public boolean supports( Class<?> authentication ) {
		return UsernamePasswordAuthenticationToken.class.equals( authentication );
	}
	
}
