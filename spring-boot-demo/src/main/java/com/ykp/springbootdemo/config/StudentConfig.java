package com.ykp.springbootdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.Serializable;

//作为spring的组件，让spring来管理这个配置文件的bean
@Component
// 用来指定配置文件的位置
@PropertySource( "classpath:config/student.properties" )
// 在注入属性的时候，可以使用@Value(“${property}”)，这种方式很方便，但也有不足，如果属性较多或者存在继承结构，
// 那么@Value方式就显得力不从心。Spring Boot 提供了另一种方式来配置属性，使用@ConfigurationProperties
@ConfigurationProperties( prefix = "student" )
public class StudentConfig implements Serializable {
	
	private static final long serialVersionUID = -8504422437704831559L;
	
//	@Value( "${student.name}" )
	private String name;
	
//	@Value( "${student.sex}" )
	private String sex;
	
//	@Value( "${student.age}" )
	private Long age;
	
//	@Value( "${student.email}" )
	private String email;
	
	public String getName() {
		return name;
	}
	
	public void setName( String name ) {
		this.name = name;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex( String sex ) {
		this.sex = sex;
	}
	
	public Long getAge() {
		return age;
	}
	
	public void setAge( Long age ) {
		this.age = age;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail( String email ) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "StudentConfig{" + "name='" + name + '\'' + ", sex='" + sex + '\'' + ", age=" + age + ", email='"
				+ email + '\'' + '}';
	}
}
