package org.johnm.flower.shop.orders;

import java.util.ArrayList;
import java.util.List;

import org.johnm.flower.shop.validation.NullParamValidator;

public class OrderProcessor {
	private NullParamValidator nullValidator = new NullParamValidator();
	private List<String> lines;
	private List<OrderLine> orderLines;

	public OrderProcessor(List<String> lines) {
		nullValidator.checkNotNull(lines, "lines");
		
		this.lines = lines;
		orderLines = new ArrayList<OrderLine>();
	}
	
	public void processOrder() {
		convertLines();
		calculateBundlesForLines();
	}
	
	void convertLines() {
		for (String line : lines) {
			final OrderLineConvertor lineConvertor = new OrderLineConvertor(line);
			final OrderLine orderLine = lineConvertor.convertLine();
			orderLines.add(orderLine);
		}
	}
	
	void calculateBundlesForLines() {
		//TODO: JM: fill this in...
	}
}
