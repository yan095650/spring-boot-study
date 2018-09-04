package com.ykp.springbootdemo.manager;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ykp.service.activemq.api.JmsProducerService;
import com.ykp.service.redis.api.RedisTemplateService;
import com.ykp.service.user.api.RoleService;
import com.ykp.service.user.api.StudentService;
import com.ykp.service.user.api.UserService;
import com.ykp.service.user.model.User;
import org.springframework.stereotype.Component;

@Component
public class ServiceManager extends BaseManager {
	
	@Reference
	public RedisTemplateService _redisTemplateService;
	
	public static RedisTemplateService redisTemplateService;
	
	@Reference
	public RoleService _roleService;
	
	public static RoleService roleService;
	
	@Reference( version = "1.0.0" )
	public StudentService _studentService;
	
	public static StudentService studentService;
	
	@Reference
	public UserService _userService;
	
	public static UserService userService;
	
	@Reference
	public JmsProducerService<User> _jmsProducerService;
	
	public static JmsProducerService<User> jmsProducerService;
}
