package com.example.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import com.example.model.User;

/**
 * 踢人小任务
 * @author OKali
 * 1、写监听器，监听是否有属性添加在Session里边
 * 2、写简单的登陆页面
 * 3、列出所有的在线用户
 * 4、实现踢人功能（也就是摧毁Session)
 *
 */
public class KickPerson implements HttpSessionAttributeListener {
	
	public KickPerson() {}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		// 得到context对象，看看context对象是否有容器装载session
		ServletContext context = se.getSession().getServletContext();
		
		Map map = (Map) context.getAttribute("map");
		if (map == null) {
			map = new HashMap<>();
			context.setAttribute("map", map);
		}
		
		//----------------------------------------------------------------------------
		
		// 得到sesson属性的值
		Object obj = se.getValue();
		
		// 判断属性内容是否时User对象
		if (obj instanceof User) {
			User user = (User) obj;
			map.put(user.getUsrname(), se.getSession());
		}
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

}
