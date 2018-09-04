package com.ykp.springbootdemo.service.impl;

import com.ykp.springbootdemo.manager.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
		logger.info( "用户的用户名: {}", username );
		com.ykp.service.user.model.User user = ServiceManager.userService.queryByUserName( username );
		if ( null == user ) {
			return null;
		}
		
		logger.info( "username:{},role_name:{}", username, "" );
		
		// 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
		User userDetail = new User( username, user.getPassword(),
				AuthorityUtils.commaSeparatedStringToAuthorityList( user.getRoleName() ) );
		return userDetail;
	}
	
}
