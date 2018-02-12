package com.example.session;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 统计在线人数
 * 分析：
 * 网站中一般使用Session来标识某用户是否登陆了，如果登陆了，就在Session域中保持相对应的属性。
 * 如果没有登陆，那么Session的属性应该为空
 * 现在，我们想要同i的四网站的在线人数，我们应该这样做：我们监听是否有新的Session创建了，如果新创建了session，那么在线人数就应该+1.这个在线人数
 * 是整个站点的，所哦也应该有Context对象保存。
 * 
 * @author OKali
 * 1、监听Session是否被创建了
 * 2、如果Session被创建了，那么在Context的域对象的值就应该+1
 * 3、如果Session从内存中移除了，那么在Context的域对象的值就应该-1
 *
 */
@WebListener("countOnline")
public class CountOnline implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// 获取得到Context对象，使用Context域对象保持用户在线人数
		ServletContext context = se.getSession().getServletContext();
		
		// 直接判断Context 对象是否存在这个域，如果存在就人数 +1，如果不存在，那么久将属性设置到Context域中
		Integer num = (Integer) context.getAttribute("onlinePersonCount");
		if (num == null) {
			context.setAttribute("onlinePersonCount", 1);
		} else {
			num ++;
			context.setAttribute("onlinePersonCount", num);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		ServletContext context = se.getSession().getServletContext();
		Integer num = (Integer) se.getSession().getAttribute("onlinePersonCount");
		
		if (num == null) {
			context.setAttribute("onlinePersonCount", 1);
		} else {
			num--;
			context.setAttribute("onlinePersonCount", num);
		}
	}

}
