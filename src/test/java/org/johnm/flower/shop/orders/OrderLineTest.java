package org.johnm.flower.shop.orders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.johnm.flower.shop.flowers.Bundle;
import org.johnm.flower.shop.flowers.FlowerProduct;
import org.johnm.flower.shop.flowers.FlowerProductFactory;
import org.johnm.flower.shop.flowers.FlowerType;
import org.junit.Before;
import org.junit.Test;

public class OrderLineTest {
	private OrderLine orderLine;
	private FlowerProduct product;
	
	@Before
	public void setup() {
		product = new FlowerProductFactory().getFlowerProductForCode(FlowerType.ROSE.getCode());
		orderLine = new OrderLine(115, product, true);
	}
	
	@Test
	public void checkcalculateBundlesToFillOrder115Roses() {
		orderLine.calculateBundlesToFillOrder();
		
		final Map<Bundle, Long> results = orderLine.getHowManyOfEachBundle();
		
		assertFalse(results.isEmpty());	
		assertTrue(11 == results.get(product.getBundles().get(0)));
		assertTrue(1 == results.get(product.getBundles().get(1)));
		
		assertFalse(orderLine.isCantMatchNumberOrderedToBundleSizes());
	}
	
	@Test
	public void checkcalculateBundlesToFillOrder5Roses() {
		orderLine = new OrderLine(5, product, true);
		orderLine.calculateBundlesToFillOrder();
		
		final Map<Bundle, Long> results = orderLine.getHowManyOfEachBundle();
		
		assertFalse(results.isEmpty());	
		assertTrue(0 == results.get(product.getBundles().get(0)));
		assertTrue(1 == results.get(product.getBundles().get(1)));
		
		assertFalse(orderLine.isCantMatchNumberOrderedToBundleSizes());
	}
	
	@Test
	public void checkcalculateBundlesToFillOrder10Roses() {
		orderLine = new OrderLine(10, product, true);
		orderLine.calculateBundlesToFillOrder();
		
		final Map<Bundle, Long> results = orderLine.getHowManyOfEachBundle();
		
		assertFalse(results.isEmpty());	
		assertTrue(1 == results.get(product.getBundles().get(0)));
		assertTrue(0 == results.get(product.getBundles().get(1)));
		
		assertFalse(orderLine.isCantMatchNumberOrderedToBundleSizes());
	}
	
	@Test
	public void checkcalculateBundlesToFillOrder11Roses() {
		orderLine = new OrderLine(11, product, true);
		orderLine.calculateBundlesToFillOrder();
		
		final Map<Bundle, Long> results = orderLine.getHowManyOfEachBundle();
		
		assertFalse(results.isEmpty());	
		assertTrue(0 == results.get(product.getBundles().get(0)));
		assertTrue(0 == results.get(product.getBundles().get(1)));
		
		assertTrue(orderLine.isCantMatchNumberOrderedToBundleSizes());
	}
	
	@Test
	public void checkcalculateBundlesToFillOrder116Roses() {
		orderLine = new OrderLine(116, product, true);
		orderLine.calculateBundlesToFillOrder();
		
		final Map<Bundle, Long> results = orderLine.getHowManyOfEachBundle();
		
		assertFalse(results.isEmpty());	
		assertTrue(0 == results.get(product.getBundles().get(0)));
		assertTrue(0 == results.get(product.getBundles().get(1)));
		
		assertTrue(orderLine.isCantMatchNumberOrderedToBundleSizes());
	}

	@Test
	public void checkCalculateFlowersInThisBundle() {
		assertEquals(0, orderLine.calculateFlowersInThisBundle(2, 10));
		assertEquals(10, orderLine.calculateFlowersInThisBundle(10, 10));
		assertEquals(20, orderLine.calculateFlowersInThisBundle(20, 10));
		assertEquals(200, orderLine.calculateFlowersInThisBundle(200, 10));
	}
}
