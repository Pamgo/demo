package com.okali.db.business.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.okali.db.business.bean.User;
import com.okali.db.business.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	private Gson gson = new GsonBuilder().create();
	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public String getUserInfo(@RequestParam("id")Integer id) {
		User userInfo = userService.getUserById(id);
		log.info("输出结果：{}", gson.toJson(userInfo));
		return "success";
	}
	
	@RequestMapping("/getUserInfo2")
	@ResponseBody
	public String getUserInfo2(@RequestParam("id")Integer id) {
		User userInfo = userService.getUserById2(id);
		log.info("输出结果：{}", gson.toJson(userInfo));
		return "success";
	}
}
