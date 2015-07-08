package org.johnm.flower.shop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.List;

import org.johnm.flower.shop.flowers.FlowerProduct;
import org.johnm.flower.shop.flowers.FlowerProductFactory;
import org.johnm.flower.shop.flowers.FlowerType;
import org.johnm.flower.shop.orders.OrderLine;
import org.junit.Before;
import org.junit.Test;

public class OrderFlowersServiceTest {
	private OrderFlowersService orderFlowerService;
	
	@Before
	public void setup() {
		orderFlowerService = new OrderFlowersService();
	}
	
	@Test
	public void checkOrderFlowers() {
		final URL url = OrderFlowersServiceTest.class.getClassLoader().getResource("test.txt");
		final List<OrderLine> orderLines = orderFlowerService.orderFlowers(url.getFile());
		
		assertNotNull(orderLines);
		assertEquals(1, orderLines.size());
		final OrderLine orderLine = orderLines.get(0);
		assertNotNull(orderLine);
		assertTrue(orderLine.isValidOrderLine());
		assertEquals(10, orderLine.getNumberOrdered());
		
		final FlowerProduct rosesProduct = new FlowerProductFactory().getFlowerProductForCode(FlowerType.ROSE.getCode());
		assertEquals(rosesProduct.getFlowerType(), orderLine.getFlowerProduct().getFlowerType());
	}
}
