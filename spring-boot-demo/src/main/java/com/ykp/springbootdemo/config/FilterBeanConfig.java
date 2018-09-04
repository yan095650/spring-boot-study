package com.ykp.springbootdemo.config;

import com.ykp.springbootdemo.filter.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * filter配置
 */
@Configuration
public class FilterBeanConfig {
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		
		// tokenFilter
		filterRegistrationBean.setFilter( tokenFilter() );
		filterRegistrationBean.addUrlPatterns( "/*" );
		filterRegistrationBean.setName( "tokenFilter" );
		
		return filterRegistrationBean;
	}
	
	@Bean
	public TokenFilter tokenFilter() {
		return new TokenFilter();
	}
}
