package com.okali.concurrency.threadlocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/threadlocal")
@Slf4j
public class ThreadLocalController {

	@RequestMapping("/test")
	@ResponseBody
	public Long test() {
		return RequestHolder.getId();
	}
}
