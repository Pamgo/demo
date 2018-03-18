package com.okali.concurrency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class TestController {

	@RequestMapping("/test")
	@ResponseBody
	public String test() {
		log.info("test!");
		return "test";
	}
}
