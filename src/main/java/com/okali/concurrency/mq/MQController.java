package com.okali.concurrency.mq;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okali.concurrency.mq.kafka.KafkaSender;
import com.okali.concurrency.mq.rabbitmq.RabbitMQClient;

@Controller
@RequestMapping("/mq")
public class MQController {

	@Resource
	private RabbitMQClient rabbitMQClient;
	
	@Resource
	private KafkaSender kafkaSender;
	
	@RequestMapping("/send")
	@ResponseBody
	public String send(@RequestParam("message")String message) {
		rabbitMQClient.send(message);
		kafkaSender.send(message);
		return "success";
	}
}
