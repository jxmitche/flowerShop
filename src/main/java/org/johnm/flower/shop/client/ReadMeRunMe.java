package org.johnm.flower.shop.client;

import java.util.List;

import org.johnm.flower.shop.orders.OrderLine;
import org.johnm.flower.shop.reporting.Reporter;
import org.johnm.flower.shop.reporting.SystemOutReporter;
import org.johnm.flower.shop.service.OrderFlowersService;
import org.johnm.flower.shop.validation.NullParamValidator;

public class ReadMeRunMe {
	private static NullParamValidator nullValidator = new NullParamValidator();
	
	public static void main(String[] args) {
		nullValidator.checkNotNull(args, "args");

		final String fileName = args[0];
		final OrderFlowersService orderFlowersService = new OrderFlowersService();
		final List<OrderLine> orderLines = orderFlowersService.orderFlowers(fileName);
		
		final Reporter reporter = new SystemOutReporter();
		reporter.report(orderLines);
	}
}
