package com.ykp.springbootdemo.controller;

import com.ykp.service.user.model.User;
import com.ykp.springbootdemo.manager.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/jms/" )
public class JmsController {
	
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger( getClass() );
	
	/**
	 * 序列化转换并发送消息(订阅发布模式)
	 * @param message 需要发送的消息
	 */
	@RequestMapping( "convertAndSendForTopic" )
	public String convertAndSendForTopic( String message ) {
		ServiceManager.jmsProducerService.convertAndSendForTopic( "sample.topic", message );
		return "convertAndSend success!";
	}
	
	/**
	 * 序列化转换并发送消息(生产者/消费者模式)
	 * @param message 需要发送的消息
	 */
	@RequestMapping( "convertAndSendForQueue" )
	public String convertAndSendForQueue( String message ) {
		ServiceManager.jmsProducerService.convertAndSendForQueue( "sample.queue", message );
		return "convertAndSend success!";
	}
	
	/**
	 * 序列化转换并发送消息，接受消息并转换成指定的class类型
	 * @param message 发送的消息
	 */
	@RequestMapping( "convertSendAndReceive" )
	public User convertSendAndReceive( User message ) {
		User receiveMsg = ServiceManager.jmsProducerService.convertSendAndReceive( "sample.queue", message, User.class );
		return receiveMsg;
	}
}
