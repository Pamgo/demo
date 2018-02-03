package com.example.pattern.observer.weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherSubject {
	
	private List<WeatherObserver> weatherObservers = new ArrayList<>();
	
	public void attach(WeatherObserver e) {
		weatherObservers.add(e);
	}
	
	public void detach(WeatherObserver observer) {
		weatherObservers.remove(observer);
	}
	
	protected void notifyTheWeather() {
		for (WeatherObserver observer : weatherObservers) {
			observer.reply(this);
		}
	}
}
