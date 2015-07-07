package org.johnm.flower.shop.reporting;

import java.util.List;

import org.johnm.flower.shop.flowers.Bundle;
import org.johnm.flower.shop.money.AussieDollars;
import org.johnm.flower.shop.orders.OrderLine;

public class SystemOutReporter implements Reporter {
	static final String SPACE = " ";
	static final String TIMES = "X";
	static final String INDENT = "      ";

	public void report(final List<OrderLine> orderLines) {
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

	String createOrderLineSummary(final OrderLine orderLine) {
		final String firstLine = orderLine.getNumberOrdered() + SPACE
				+ orderLine.getFlowerProduct().getFlowerType().getCode()
				+ SPACE + orderLine.getTotalPrice();
		
		return firstLine;
	}

	void outputBundles(final OrderLine orderLine) {
		for (Bundle bundle : orderLine.getHowManyOfEachBundle().keySet()) {
			final long howManyBundles = orderLine.getHowManyOfEachBundle().get(bundle);
			
			if (howManyBundles != 0) {
				final String bundleLine = createBundleLine(howManyBundles, bundle);
				System.out.println(bundleLine);
			}
		}
	}

	String createBundleLine(final long howManyBundles, final Bundle bundle) {
		final long numberPerBundle = bundle.getNumberInBundle();
		final AussieDollars pricePerBundle = bundle.getPricePerBundle();
		
		return INDENT + howManyBundles + SPACE + TIMES + SPACE + numberPerBundle + SPACE + pricePerBundle;
	}
}