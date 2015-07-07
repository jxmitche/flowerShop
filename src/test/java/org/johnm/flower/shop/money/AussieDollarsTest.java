package org.johnm.flower.shop.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import org.junit.Test;

public class AussieDollarsTest {

	@Test
	public void checkConstructor12345() {
		final AussieDollars dollars = new AussieDollars(12345);
		final BigDecimal amount = dollars.getAmount();
		
		assertEquals(2, amount.scale());
		final BigDecimal cfAmt = BigDecimal.valueOf(12345, 2);
		assertEquals(cfAmt, amount);
	}
	
	@Test
	public void checkConstructor1() {
		final AussieDollars dollars = new AussieDollars(1);
		final BigDecimal amount = dollars.getAmount();
		
		assertEquals(2, amount.scale());
		final BigDecimal cfAmt = BigDecimal.valueOf(1, 2);
		assertEquals(cfAmt, amount);
	}
	
	@Test
	public void checkConstructor0() {
		final AussieDollars dollars = new AussieDollars(0);
		final BigDecimal amount = dollars.getAmount();
		
		assertEquals(2, amount.scale());
		final BigDecimal cfAmt = BigDecimal.valueOf(0, 2);
		assertEquals(cfAmt, amount);
	}
	
	@Test
	public void checkMultiplyByTwo() {
		final AussieDollars dollars = new AussieDollars(1);
		final AussieDollars result = dollars.multiply(2l);
		
		final AussieDollars cfAmt = new AussieDollars(2);
		assertEquals(cfAmt, result);
	}
	
	@Test
	public void checkMultiplyByZero() {
		final AussieDollars dollars = new AussieDollars(1);
		final AussieDollars result = dollars.multiply(0);
		
		final AussieDollars cfAmt = new AussieDollars(0);
		assertEquals(cfAmt, result);
	}
	
	@Test
	public void checkMultiplyByZeroByOne() {
		final AussieDollars dollars = new AussieDollars(0);
		final AussieDollars result = dollars.multiply(1);
		
		final AussieDollars cfAmt = new AussieDollars(0);
		assertEquals(cfAmt, result);
	}
	
	@Test
	public void checkAdd() {
		final AussieDollars dollars = new AussieDollars(10);
		final AussieDollars lhs = new AussieDollars(4);
		
		final AussieDollars result = dollars.add(lhs);
		
		final AussieDollars cfAmt = new AussieDollars(14);
		assertEquals(cfAmt, result);
	}
	
	@Test
	public void checkAddNullParam() {
		final AussieDollars dollars = new AussieDollars(10);

		try {
			dollars.add(null);
			fail("should not reach here");
		} catch (IllegalArgumentException ex) {
			assertEquals("leftHandSide must not be null", ex.getMessage());
		}
	}
}
