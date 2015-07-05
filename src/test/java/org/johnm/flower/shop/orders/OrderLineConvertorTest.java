package org.johnm.flower.shop.orders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.johnm.flower.shop.flowers.FlowerProduct;
import org.johnm.flower.shop.flowers.FlowerProductFactory;
import org.johnm.flower.shop.flowers.FlowerType;
import org.junit.Test;

public class OrderLineConvertorTest {

	@Test
	public void checkConstructorWithNull() {
		try {
			new OrderLineConvertor(null);
			fail("should not reach here");
		} catch (IllegalArgumentException ex) {
			assertEquals("line must not be null", ex.getMessage());
		}
	}
	
	@Test
	public void checkValidLine() {
		final OrderLineConvertor convertor = new OrderLineConvertor("10 R12");
		final OrderLine orderLine = convertor.convertLine();
		
		assertTrue(orderLine.isValid());
		assertEquals(10, orderLine.getNumberOrdered());
		
		final FlowerProduct rosesProduct = new FlowerProductFactory().getFlowerProductForCode(FlowerType.ROSE.getCode());
		assertEquals(rosesProduct.getFlowerType(), orderLine.getFlowerProduct().getFlowerType());
	}
	
	@Test
	public void checkLineWithUnknownFlowerProduct() {
		final OrderLineConvertor convertor = new OrderLineConvertor("10 XXX");
		final OrderLine orderLine = convertor.convertLine();
		
		assertFalse(orderLine.isValid());
		assertEquals(10, orderLine.getNumberOrdered());
		
		final FlowerProduct unknownProduct = new FlowerProductFactory().getUnknown();
		assertEquals(unknownProduct.getFlowerType(), orderLine.getFlowerProduct().getFlowerType());
	}
	
	@Test
	public void checkLineWithEmptyString() {
		final OrderLineConvertor convertor = new OrderLineConvertor("");
		final OrderLine orderLine = convertor.convertLine();
		
		assertFalse(orderLine.isValid());
		
		final FlowerProduct unknownProduct = new FlowerProductFactory().getUnknown();
		assertEquals(unknownProduct.getFlowerType(), orderLine.getFlowerProduct().getFlowerType());
	}
	
	@Test
	public void checkLineWithOneValueNoSpace() {
		final OrderLineConvertor convertor = new OrderLineConvertor("34eree");
		final OrderLine orderLine = convertor.convertLine();
		
		assertFalse(orderLine.isValid());
		
		final FlowerProduct unknownProduct = new FlowerProductFactory().getUnknown();
		assertEquals(unknownProduct.getFlowerType(), orderLine.getFlowerProduct().getFlowerType());
	}
}
