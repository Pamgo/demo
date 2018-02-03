package com.example.pattern.observer;

/**
 * 观察者
 * @author OKali
 *
 */
public class ConcreteObserver implements Observer {
	
	private String observerState;   // 观察者状态（目标状态和观察者状态保持一致）

	/**
	 * 获取目标类的状态同步到观察者状态
	 */
	@Override
	public void updateState(Subject subject) {
		observerState = subject.getSubjectState();
	}

}
