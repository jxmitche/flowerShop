package org.johnm.flower.shop.orders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.johnm.flower.shop.flowers.FlowerProduct;
import org.johnm.flower.shop.flowers.FlowerProductFactory;
import org.johnm.flower.shop.flowers.FlowerType;
import org.junit.Before;
import org.junit.Test;

public class OrderLineConvertorTest {
private FlowerProductFactory factory;
	
	@Before
	public void setup() {
		factory = new FlowerProductFactory();
	}
	
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
		
		assertTrue(orderLine.isValidOrderLine());
		assertEquals(10, orderLine.getNumberOrdered());
		
		final FlowerProduct rosesProduct = new FlowerProductFactory().getFlowerProductForCode(FlowerType.ROSE.getCode());
		assertEquals(rosesProduct.getFlowerType(), orderLine.getFlowerProduct().getFlowerType());
	}
	
	@Test
	public void checkLineWithUnknownFlowerProduct() {
		final OrderLineConvertor convertor = new OrderLineConvertor("10 XXX");
		final OrderLine orderLine = convertor.convertLine();
		
		assertFalse(orderLine.isValidOrderLine());
		assertEquals(10, orderLine.getNumberOrdered());
		
		final FlowerProduct unknownProduct = new FlowerProductFactory().getUnknown();
		assertEquals(unknownProduct.getFlowerType(), orderLine.getFlowerProduct().getFlowerType());
	}
	
	@Test
	public void checkLineWithEmptyString() {
		final OrderLineConvertor convertor = new OrderLineConvertor("");
		final OrderLine orderLine = convertor.convertLine();
		
		assertFalse(orderLine.isValidOrderLine());
		
		final FlowerProduct unknownProduct = new FlowerProductFactory().getUnknown();
		assertEquals(unknownProduct.getFlowerType(), orderLine.getFlowerProduct().getFlowerType());
	}
	
	@Test
	public void checkLineWithOneValueNoSpace() {
		final OrderLineConvertor convertor = new OrderLineConvertor("34eree");
		final OrderLine orderLine = convertor.convertLine();
		
		assertFalse(orderLine.isValidOrderLine());
		
		final FlowerProduct unknownProduct = new FlowerProductFactory().getUnknown();
		assertEquals(unknownProduct.getFlowerType(), orderLine.getFlowerProduct().getFlowerType());
	}
	@Test
	public void checkConvertOrderNbr() {
		final OrderLineConvertor convertor = new OrderLineConvertor("");
		
		assertEquals(123, convertor.convertOrderNbr("123"));
		assertTrue(convertor.isProcessedLineSuccessfully());
		
		assertEquals(0, convertor.convertOrderNbr("0"));
		assertTrue(convertor.isProcessedLineSuccessfully());
		
		assertEquals(0, convertor.convertOrderNbr("avc"));
		assertFalse(convertor.isProcessedLineSuccessfully());
		
		assertEquals(0, convertor.convertOrderNbr("1.2"));
		assertFalse(convertor.isProcessedLineSuccessfully());
	}
	
	@Test
	public void checkConvertProductCodeTulip() {
		final OrderLineConvertor convertor = new OrderLineConvertor("");
		final FlowerProduct tulipProduct = factory.getFlowerProductForCode(FlowerType.TULIP.getCode());
		
		assertEquals(tulipProduct, convertor.convertProductCode(FlowerType.TULIP.getCode()));
	}
	
	@Test
	public void checkConvertProductCodeUnknown() {
		final OrderLineConvertor convertor = new OrderLineConvertor("");
		final FlowerProduct unknownProduct = factory.getUnknown();
		
		assertEquals(unknownProduct, convertor.convertProductCode("xxx"));
	}
	
	@Test
	public void checkExtractFieldsFromLineEmptyString() {
		final OrderLineConvertor convertor = new OrderLineConvertor("");
		final String[] splitValues = convertor.extractFieldsFromLine();
		
		assertEquals(1, splitValues.length);
		assertEquals("", splitValues[0]);
	}
	
	@Test
	public void checkExtractFieldsFromLineAspaceB() {
		final OrderLineConvertor convertor = new OrderLineConvertor("a b");
		final String[] splitValues = convertor.extractFieldsFromLine();
		
		assertEquals(2, splitValues.length);
		assertEquals("a", splitValues[0]);
		assertEquals("b", splitValues[1]);
	}
	
	@Test
	public void checkExtractFieldsFromLineDoubleSpace() {
		final OrderLineConvertor convertor = new OrderLineConvertor("a  b");
		final String[] splitValues = convertor.extractFieldsFromLine();
		
		assertEquals(3, splitValues.length);
		assertEquals("a", splitValues[0]);
		assertEquals("", splitValues[1]);
		assertEquals("b", splitValues[2]);
	}
	
	@Test
	public void checkConvertLineFieldsTwoValues() {
		final OrderLineConvertor convertor = new OrderLineConvertor("");
		final String[] splitLine = {"2", "R12"};
		
		convertor.convertLineFields(splitLine);
		
		assertEquals(2, convertor.getNumberOrdered());
		final FlowerProduct roseProduct = factory.getFlowerProductForCode(FlowerType.ROSE.getCode());
		
		assertEquals(roseProduct, convertor.getFlowerProduct());
	}
	
	@Test
	public void checkConvertLineFieldsThreeValues() {
		final OrderLineConvertor convertor = new OrderLineConvertor("");
		final String[] splitLine = {"2", "R12", "xx"};
		
		convertor.convertLineFields(splitLine);
		
		assertEquals(0, convertor.getNumberOrdered());
		final FlowerProduct unknownProduct = factory.getFlowerProductForCode(FlowerType.UNKNOWN.getCode());
		
		assertEquals(unknownProduct, convertor.getFlowerProduct());
	}
	
	@Test
	public void checkConvertLineFieldsOneValue() {
		final OrderLineConvertor convertor = new OrderLineConvertor("");
		final String[] splitLine = {"2"};
		
		convertor.convertLineFields(splitLine);
		
		assertEquals(0, convertor.getNumberOrdered());
		final FlowerProduct unknownProduct = factory.getFlowerProductForCode(FlowerType.UNKNOWN.getCode());
		
		assertEquals(unknownProduct, convertor.getFlowerProduct());
	}
}
