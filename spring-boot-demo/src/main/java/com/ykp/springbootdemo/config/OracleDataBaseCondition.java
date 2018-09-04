package com.ykp.springbootdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class OracleDataBaseCondition implements Condition {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Override
	public boolean matches( ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata ) {
		try {
			logger.info( "............start execute OracleDataBaseCondition.matches............" );
			conditionContext.getClassLoader().loadClass( "oracle.jdbc.driver.OracleDriver" );
			return true;
		} catch ( ClassNotFoundException e ) {
			return false;
		}
		
	}
}
