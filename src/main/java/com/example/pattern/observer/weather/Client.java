package com.example.pattern.observer.weather;

public class Client {

	public static void main(String[] args) {
		
		// 0.定义目标对象
		ConcreteWeatherSubject weatherSubject = new ConcreteWeatherSubject();
		// 1.定义观察者
		ConcreteWeatherObserver observerGirl = new ConcreteWeatherObserver();
		observerGirl.setObserverName("女朋友");
		observerGirl.setReplyContent("约会");
		
		ConcreteWeatherObserver observerW = new ConcreteWeatherObserver();
		observerW.setObserverName("Mun");
		observerW.setReplyContent("购物");
		
		// 2.注册观察者
		weatherSubject.attach(observerGirl);
		weatherSubject.attach(observerW);
		
		// 3.发布
		weatherSubject.setWeatherContent("今天天气晴朗");
	}
}
