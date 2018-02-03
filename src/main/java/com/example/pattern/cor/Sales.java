package com.example.pattern.cor;

/**
 * 销售，可以批准5%的折扣
 * @author OKali
 *
 */
public class Sales extends PriceHandler {

	@Override
	public void processDiscount(float discount) {
		if (discount <= 0.05) {
			System.out.format("%s批准了折扣:%.2f%n", this.getClass().getName(),discount);
		} else {
			successor.processDiscount(discount); //传递请求
		}
	}

}
