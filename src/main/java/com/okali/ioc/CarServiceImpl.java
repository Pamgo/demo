package com.okali.ioc;

public class CarServiceImpl implements CarService {
	
	private Car car;

	@Override
	public void sell() {
		System.out.println("xxxx");
		car.clearCar();
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

}
