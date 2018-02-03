package com.example.pattern.cor;

/**
 * CEO可以处理55%以内的折扣，超出将拒绝申请
 * @author OKali
 *
 */
public class CEO extends PriceHandler {

	@Override
	public void processDiscount(float discount) {
		if (discount <= 0.55) {
			System.out.format("%s批准了折扣:%.2f%n", this.getClass().getName(),discount);
		} else {
			System.out.format("%s拒绝了折扣:%.2f%n", this.getClass().getName(),discount);
		}
	}

}
