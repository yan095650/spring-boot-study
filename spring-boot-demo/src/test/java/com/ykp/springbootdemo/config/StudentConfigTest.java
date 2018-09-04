package com.ykp.springbootdemo.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试自定义properties
 */
@RunWith( SpringRunner.class )
@SpringBootTest
public class StudentConfigTest {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Autowired
	private StudentConfig studentConfig;
	
	@Test
	public void test_studentConfig() {
		logger.info( "studentConfig:{}", studentConfig );
	}
}
