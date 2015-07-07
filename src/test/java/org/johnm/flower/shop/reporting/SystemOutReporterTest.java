package org.johnm.flower.shop.reporting;

import static org.junit.Assert.assertEquals;

import org.johnm.flower.shop.flowers.Bundle;
import org.johnm.flower.shop.flowers.FlowerProduct;
import org.johnm.flower.shop.flowers.FlowerProductFactory;
import org.johnm.flower.shop.flowers.FlowerType;
import org.johnm.flower.shop.money.AussieDollars;
import org.johnm.flower.shop.orders.OrderLine;
import org.junit.Before;
import org.junit.Test;

public class SystemOutReporterTest {
	private SystemOutReporter reporter;
	private OrderLine orderLine;
	private Bundle bundle;
	
	@Before
	public void setup() {
		reporter = new SystemOutReporter();
		final FlowerProduct product = new FlowerProductFactory().getFlowerProductForCode(FlowerType.ROSE.getCode());
		orderLine = new OrderLine(115, product, true);
		bundle = new Bundle(1, 299);
	}
	
	@Test
	public void checkCreateOrderLineSummary() {
		final String firstLine = orderLine.getNumberOrdered() + SystemOutReporter.SPACE
				+ orderLine.getFlowerProduct().getFlowerType().getCode()
				+ SystemOutReporter.SPACE + orderLine.getTotalPrice();
		
		assertEquals(firstLine, reporter.createOrderLineSummary(orderLine));
	}
	
	@Test
	public void checkCreateBundleLine() {
		final long numberPerBundle = bundle.getNumberInBundle();
		final AussieDollars pricePerBundle = bundle.getPricePerBundle();
		final String result = SystemOutReporter.INDENT + 1 + SystemOutReporter.SPACE + 
				numberPerBundle + SystemOutReporter.SPACE + pricePerBundle;

		assertEquals(result, reporter.createBundleLine(1, bundle));
	}
}
