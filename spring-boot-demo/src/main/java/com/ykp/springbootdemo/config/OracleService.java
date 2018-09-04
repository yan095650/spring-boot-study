package com.ykp.springbootdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OracleService {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	public OracleService() {
		logger.info( ".............START INIT BEAN OF ORACLESERVICE............." );
	}
}
