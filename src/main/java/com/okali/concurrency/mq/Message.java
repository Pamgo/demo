package com.okali.concurrency.mq;

import java.util.Date;

import lombok.Data;

@Data
public class Message {

	private long id;
	
	private String msg;
	
	private Date sendTime;
}
