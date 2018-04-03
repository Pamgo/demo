package com.okali.concurrency.mq.rabbitmq;

import javax.annotation.Resource;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQClient {

	@Resource
	private RabbitTemplate rabbitTemplate;
	
	public void send(String message) {
		log.info("rabbitMQ send:{}", message);
		rabbitTemplate.convertAndSend(QueueConstants.TEST, message); // 指定队列
	}
}
