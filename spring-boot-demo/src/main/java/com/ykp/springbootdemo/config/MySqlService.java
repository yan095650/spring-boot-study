package com.ykp.springbootdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MySqlService {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	public MySqlService() {
		logger.info( ".............START INIT BEAN OF MYSQLSERVICE............." );
	}
}
