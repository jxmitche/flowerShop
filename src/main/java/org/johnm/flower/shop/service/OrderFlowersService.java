package org.johnm.flower.shop.service;

import java.util.List;

import org.johnm.flower.shop.input.ReadFile;
import org.johnm.flower.shop.orders.OrderLine;
import org.johnm.flower.shop.orders.OrderProcessor;

public class OrderFlowersService {

	public List<OrderLine> orderFlowers(final String fileName) {
		final ReadFile readFile = new ReadFile(fileName);
		final List<String> lines = readFile.readFile();
		
		final OrderProcessor orderProcessor = new OrderProcessor(lines);
		orderProcessor.processOrder();
		final List<OrderLine> orderLines = orderProcessor.getOrderLines();
		
		return orderLines;
	}
}
