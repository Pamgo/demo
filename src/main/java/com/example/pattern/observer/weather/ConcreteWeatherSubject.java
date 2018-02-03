package com.example.pattern.observer.weather;

public class ConcreteWeatherSubject extends WeatherSubject {
	
	private String weatherContent;	// 获取天气信息
	
	public void setWeatherContent(String weatherContent) {
		this.weatherContent = weatherContent;
		this.notifyTheWeather();
	}
	
	public String getWeatherContent() {
		return weatherContent;
	}
}
