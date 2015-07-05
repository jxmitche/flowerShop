package org.johnm.flower.shop.orders;

import org.johnm.flower.shop.flowers.FlowerProduct;
import org.johnm.flower.shop.validation.NullParamValidator;

public class OrderLine {
	private boolean isValid;
	private long numberOrdered;
	private FlowerProduct flowerProduct;
	private NullParamValidator nullValidator;
	
	public OrderLine(final long numberOrdered, final FlowerProduct flowerProduct, final boolean isValid) {
		nullValidator = new NullParamValidator();
		nullValidator.checkNotNull(flowerProduct, "flowerProduct");
		
		this.numberOrdered = numberOrdered;
		this.flowerProduct = flowerProduct;
		this.isValid = isValid;
	}

	public boolean isValid() {
		return isValid;
	}

	public long getNumberOrdered() {
		return numberOrdered;
	}

	public FlowerProduct getFlowerProduct() {
		return flowerProduct;
	}
	
}
