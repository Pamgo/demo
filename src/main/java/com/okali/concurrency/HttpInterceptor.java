package com.okali.concurrency;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.okali.concurrency.threadlocal.RequestHolder;

import lombok.extern.slf4j.Slf4j;

/**
 * 拦截器
 * @author OKali
 *
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle");
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		RequestHolder.remove();
		log.info("afterCompletion");
		return;
	}
}
