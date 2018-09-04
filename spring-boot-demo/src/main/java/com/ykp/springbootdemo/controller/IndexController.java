package com.ykp.springbootdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注入的时候一定要是Controller 不要是RestController 因为它是rest接口（json格式） 是解析不到html
 */
@Controller
@RequestMapping( "/" )
public class IndexController extends BaseController {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	@RequestMapping( "" )
	public String index() {
		logger.info( "index" );
		return "/index";
	}

}
