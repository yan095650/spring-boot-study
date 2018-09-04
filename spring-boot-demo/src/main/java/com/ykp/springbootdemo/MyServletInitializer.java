package com.ykp.springbootdemo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * package war
 */
public class MyServletInitializer extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure( SpringApplicationBuilder builder ) {
		return builder.sources( SpringBootDemoApplication.class );
	}
}
