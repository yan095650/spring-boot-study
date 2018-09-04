package com.ykp.springbootdemo.controller;

import com.ykp.service.user.model.Role;
import com.ykp.springbootdemo.manager.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/role/" )
public class RoleController extends BaseController {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	@RequestMapping( "queryById" )
	public Role queryById( Integer roleId ) {
		logger.info( "roleId:{}", roleId );
		Role role = ServiceManager.roleService.queryById( roleId );
		logger.info( "role:{}", role );
		return role;
	}
}
