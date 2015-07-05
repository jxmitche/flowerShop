package org.johnm.flower.shop.orders;

import org.johnm.flower.shop.flowers.FlowerProduct;
import org.johnm.flower.shop.flowers.FlowerProductFactory;
import org.johnm.flower.shop.validation.NullParamValidator;

public class OrderLineConvertor {
	private final String line;
	private boolean processedLineSuccessfully;
	private long numberOrdered;
	private FlowerProduct flowerProduct;
	private NullParamValidator nullValidator;
	private FlowerProductFactory flowerProductFactory;
	
	public OrderLineConvertor(final String line) {
		nullValidator = new NullParamValidator();
		nullValidator.checkNotNull(line, "line");
		
		this.line = line;	
		flowerProductFactory = new FlowerProductFactory();
		processedLineSuccessfully = true;
	}
	
	public OrderLine convertLine() {
		final String[] splitLine = line.split(" ");
		
		if (splitLine.length == 2) {
			numberOrdered = convertOrderNbr(splitLine[0]);
			flowerProduct = convertProductCode(splitLine[1]);
		} else {
			flowerProduct = flowerProductFactory.getUnknown();
		}

		checkFlowerProductKnown();
		
		return new OrderLine(numberOrdered, flowerProduct, processedLineSuccessfully);
	}
	
	long convertOrderNbr(final String number) {
		try {
			return Long.parseLong(number);
		} catch (NumberFormatException ex) {
			processedLineSuccessfully = false;
		}
		
		return 0;
	}
	
	FlowerProduct convertProductCode(final String code) {
		return flowerProductFactory.getFlowerProductForCode(code);
	}
	
	void checkFlowerProductKnown() {
		if (flowerProduct.isUnknown()) processedLineSuccessfully = false;
	}

}
