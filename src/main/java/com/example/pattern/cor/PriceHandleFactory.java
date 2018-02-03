package com.example.pattern.cor;

public class PriceHandleFactory {

	/**
	 * 工厂方法：不在于参数的个数，而在于返回值，业务与逻辑分离
	 * @return
	 */
	public static PriceHandler createPriceHandler() {
		PriceHandler sales = new Sales();
		PriceHandler manager = new Manager();
		PriceHandler director = new Director();
		PriceHandler vp = new VicePresident();
		PriceHandler ceo = new CEO();
		
		sales.setSuccessor(manager);
		manager.setSuccessor(director);
		director.setSuccessor(vp);
		vp.setSuccessor(ceo);
		
		return sales;
	}

}
