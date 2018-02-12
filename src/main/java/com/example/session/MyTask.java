package com.example.session;

import java.util.List;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

/**
 * 在任务中应该扫描容器，容器在监听器上，只能传进来
 * 要想得到在监听器上的锁，也把锁传进来
 * @author OKali
 *
 */
public class MyTask extends TimerTask {
	
	private List<HttpSession> sessions;
	
	private Object lock;
	
	public MyTask(List<HttpSession> sessions, Object lock) {
		this.sessions = sessions;
		this.lock = lock;
	}

	@Override
	public void run() {
		for (HttpSession session : sessions) {
			
			// 15秒没人使用，我就移除它了
			if (System.currentTimeMillis() - session.getLastAccessedTime() > (1000 * 15)) {
				session.invalidate();
				sessions.remove(session);
			}
		}
	}

}
