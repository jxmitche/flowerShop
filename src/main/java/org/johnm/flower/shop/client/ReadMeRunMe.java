package org.johnm.flower.shop.client;

import java.util.List;

import org.johnm.flower.shop.input.ReadFile;
import org.johnm.flower.shop.orders.OrderLine;
import org.johnm.flower.shop.orders.OrderProcessor;
import org.johnm.flower.shop.reporting.Reporter;
import org.johnm.flower.shop.reporting.SystemOutReporter;
import org.johnm.flower.shop.validation.NullParamValidator;

public class ReadMeRunMe {
	private static NullParamValidator nullValidator = new NullParamValidator();
	
	public static void main(String[] args) {
		nullValidator.checkNotNull(args, "args");

		//take file name as first param
		final String fileName = args[0];
		final ReadFile readFile = new ReadFile(fileName);
		final List<String> lines = readFile.readFile();
		
		final OrderProcessor orderProcessor = new OrderProcessor(lines);
		orderProcessor.processOrder();
		final List<OrderLine> orderLines = orderProcessor.getOrderLines();
		
		final Reporter reporter = new SystemOutReporter();
		reporter.report(orderLines);
	}
}
