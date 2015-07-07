package org.johnm.flower.shop.orders;

import org.johnm.flower.shop.flowers.FlowerProduct;
import org.johnm.flower.shop.flowers.FlowerProductFactory;
import org.johnm.flower.shop.validation.NullParamValidator;

public class OrderLineConvertor {
	private static final String INPUT_LINE_FIELD_SEPARATOR = " ";
	
	private final String line;
	private boolean processedLineSuccessfully;
	private long numberOrdered;
	private FlowerProduct flowerProduct;
	private NullParamValidator nullValidator= new NullParamValidator();
	private FlowerProductFactory flowerProductFactory;
	
	public OrderLineConvertor(final String line) {
		nullValidator.checkNotNull(line, "line");
		
		this.line = line;	
		flowerProductFactory = new FlowerProductFactory();
		processedLineSuccessfully = true;
	}
	
	public OrderLine convertLine() {
		final String[] splitLine = extractFieldsFromLine();
		convertLineFields(splitLine);
		checkIfFlowerProductKnown();
		
		return new OrderLine(numberOrdered, flowerProduct, processedLineSuccessfully);
	}
	
	String[] extractFieldsFromLine() {
		final String[] splitLine = line.split(INPUT_LINE_FIELD_SEPARATOR);
		
		return splitLine;
	}
	
	void convertLineFields(final String[] splitLine) {
		if (splitLine.length == 2) {
			numberOrdered = convertOrderNbr(splitLine[0]);
			flowerProduct = convertProductCode(splitLine[1]);
		} else {
			flowerProduct = flowerProductFactory.getUnknown();
		}
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
	
	void checkIfFlowerProductKnown() {
		if (flowerProduct.isUnknown()) processedLineSuccessfully = false;
	}

	boolean isProcessedLineSuccessfully() {
		return processedLineSuccessfully;
	}

	long getNumberOrdered() {
		return numberOrdered;
	}

	FlowerProduct getFlowerProduct() {
		return flowerProduct;
	}

}
