package com.example.session;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

/**
 * 自定义session扫描器
 * @author OKali
 *
 */
public class ListenerDIY implements ServletContextListener, HttpSessionListener {
	
	private Logger logger = Logger.getLogger(ListenerDIY.class);

	// 服务器已启动就应该创建容器，我们使用的时LinkList(涉及到增删）。容器也应该时线程安全的。
	List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());
	
	// 定义一把锁（Session 添加到容器和扫描容器这两个操作应该同步起来）
	private Object lock = 1;
	
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// 只要session一创建了，就应该添加到容器中
		synchronized (lock) {
			list.add(se.getSession());
		}
		logger.info("Session is created over!");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		logger.info("Session is destroyed!!!");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Timer timer = new Timer();
		// 执行我想要的任务，0秒延迟，每10秒执行一次
		timer.schedule(new MyTask(list, timer), 0, 10 * 1000);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {}

}
