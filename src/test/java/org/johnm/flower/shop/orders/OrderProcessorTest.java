package org.johnm.flower.shop.orders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class OrderProcessorTest {
	private List<String> lines;
	private OrderProcessor orderProcessor;
	
	@Before
	public void setup() {
		lines = new ArrayList<String>();
		lines.add("10 R12");
		
		orderProcessor = new OrderProcessor(lines);  
	}
	
	@Test
	public void checkConvertLines() {
		orderProcessor.convertLines();
		final List<OrderLine> orderLines = orderProcessor.getOrderLines();
		
		assertFalse(orderLines.isEmpty());
		assertEquals(1, orderLines.size());
	}
	
	@Test
	public void checkNullParam() {
		try {
			new OrderProcessor(null);
			fail("should not reach here");
		} catch (IllegalArgumentException ex) {
			assertEquals("lines must not be null", ex.getMessage());
		}
	}
}
