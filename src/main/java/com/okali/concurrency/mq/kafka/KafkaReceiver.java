package com.okali.concurrency.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 接收端
 * @author OKali
 *
 */
@Component
@Slf4j
public class KafkaReceiver {

	@KafkaListener(topics= {TopicConstants.TEST})
	public void receive(ConsumerRecord<?, ?> record) {
		log.info("record:{}", record);
	}
}
