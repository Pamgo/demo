package com.okali.ioc;

public class SpringIocTest {

	public static void main(String[] args) {
		SelfClassPathXMLApplicationContext applicationContext = new SelfClassPathXMLApplicationContext("beans.xml");
		CarService carService = (CarService) applicationContext.getBean("carService");
		carService.sell();
	}
}
