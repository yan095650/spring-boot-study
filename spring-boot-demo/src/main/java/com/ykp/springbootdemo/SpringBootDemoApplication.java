package com.ykp.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 此类在Spring Boot应用程序里有两个作用：配置和启动引导。首先，
 * 这是主要的Spring配置类。虽然Spring Boot的自动配置免除了很多Spring配置，但你还需要进行
 * 少量配置来启用自动配置
 */
@SpringBootApplication
// Servlet注解
@ServletComponentScan
public class SpringBootDemoApplication {// 开启组件扫描和自动配置

	public static void main( String[] args ) {
		SpringApplication.run( SpringBootDemoApplication.class, args );
	}
}
