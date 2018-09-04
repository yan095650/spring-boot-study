package com.ykp.springbootdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	/**
	 * 当存在mysql驱动类时，初始化MySqlService
	 */
	@Bean
	@Conditional( MySqlDataBaseCondition.class )
	public MySqlService mySqlService() {
		return new MySqlService();
	}
	
	/**
	 * 当存在mysql驱动类时，初始化OracleService
	 */
	@Bean
	@Conditional( OracleDataBaseCondition.class )
	public OracleService oracleService() {
		return new OracleService();
	}
}
