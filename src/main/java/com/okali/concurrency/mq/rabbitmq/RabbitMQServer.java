package com.okali.concurrency.mq.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RabbitMQServer {

	@RabbitListener(queues = QueueConstants.TEST)  // 拿到该队列的消息
	private void receive(String message) {
		log.info("rabbitMQ :{}", message);
	}
}
