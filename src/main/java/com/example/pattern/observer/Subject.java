package com.example.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 目标
 * @author OKali
 *
 */
public class Subject {
	
	private List<Observer> observers = new ArrayList<>();
	
	private String subjectState;
	
	public String getSubjectState() {
		return subjectState;
	}
	
	public void setSubjectState(String subjectState) {
		this.subjectState = subjectState;
		this.notifyObservers();
	}
	
	// 添加观察者
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	/**
	 * 通知观察者
	 */
	protected void notifyObservers() {
		for (Observer observer : observers) {
			observer.updateState(this);
		}
	}

}
