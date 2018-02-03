package com.example.pattern.cor;

import java.util.Random;

public class Customer {
	
	private PriceHandler priceHandler;
	
	public void setPriceHandler(PriceHandler priceHandler) {
		this.priceHandler = priceHandler;
	}
	
	public void requestPrice(float discount) {
		priceHandler.processDiscount(discount);
	}
	
	public static void main(String[] args) {
		Customer customer = new Customer();
		
		Random random = new Random();
		for (float i = 0.001f; i<= 100f; i++) {
			System.out.print(i + ":");
			customer.setPriceHandler(PriceHandleFactory.createPriceHandler());
			customer.requestPrice(random.nextFloat());
		}
	}

}
