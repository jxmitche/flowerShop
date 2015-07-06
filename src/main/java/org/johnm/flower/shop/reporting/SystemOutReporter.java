package org.johnm.flower.shop.reporting;

import java.util.List;

import org.johnm.flower.shop.flowers.Bundle;
import org.johnm.flower.shop.money.AussieDollars;
import org.johnm.flower.shop.orders.OrderLine;

public class SystemOutReporter implements Reporter {
	private static final String SPACE = " ";
	private static final String INDENT = "      ";

	public void report(List<OrderLine> orderLines) {
		for (OrderLine orderLine : orderLines) {
			final String orderLineSummary = createOrderLineSummary(orderLine);
			System.out.println(orderLineSummary);
			
			if (orderLine.isCantMatchNumberOrderedToBundleSizes()) {
				System.out.println(INDENT + "Failed to match number ordered to bundles sizes");
			} else {
				outputBundles(orderLine);
			}
		}
	}

	private String createOrderLineSummary(OrderLine orderLine) {
		final String firstLine = orderLine.getNumberOrdered() + SPACE
				+ orderLine.getFlowerProduct().getFlowerType().getCode()
				+ SPACE + orderLine.getTotalPrice();
		
		return firstLine;
	}

	private void outputBundles(OrderLine orderLine) {
		for (Bundle bundle : orderLine.getHowManyOfEachBundle().keySet()) {
			final long howManyBundles = orderLine.getHowManyOfEachBundle().get(bundle);
			
			if (howManyBundles != 0) {
				final long numberPerBundle = bundle.getNumberInBundle();
				final AussieDollars pricePerBundle = bundle.getPricePerBundle();
				
				final String bundleLine = INDENT + howManyBundles + SPACE + numberPerBundle + SPACE + pricePerBundle;
				System.out.println(bundleLine);
			}
		}
	}

}
