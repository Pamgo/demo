package com.example.pattern.observer.weather;


public class ConcreteWeatherObserver implements WeatherObserver {
	
	private String observerName; // 观察者名字
	
	private String weatherContent; // 天气内容，从目标对象获取
	
	private String replyContent;   // 响应回复内容

	@Override
	public void reply(WeatherSubject weatherSubject) {
		weatherContent = ((ConcreteWeatherSubject)weatherSubject).getWeatherContent(); //拉模型
		System.out.println(observerName +"收到天气信息：" + weatherContent +"," + replyContent);
	}

	public String getObserverName() {
		return observerName;
	}

	public void setObserverName(String observerName) {
		this.observerName = observerName;
	}

	public String getWeatherContent() {
		return weatherContent;
	}

	public void setWeatherContent(String weatherContent) {
		this.weatherContent = weatherContent;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	

}
