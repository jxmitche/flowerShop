package org.johnm.flower.shop.money;

import static org.junit.Assert.assertEquals;

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
	
	public void checkConstructor1() {
		final AussieDollars dollars = new AussieDollars(1);
		final BigDecimal amount = dollars.getAmount();
		
		assertEquals(2, amount.scale());
		final BigDecimal cfAmt = BigDecimal.valueOf(1, 2);
		assertEquals(cfAmt, amount);
	}
	
	public void checkConstructor0() {
		final AussieDollars dollars = new AussieDollars(0);
		final BigDecimal amount = dollars.getAmount();
		
		assertEquals(2, amount.scale());
		final BigDecimal cfAmt = BigDecimal.valueOf(0, 2);
		assertEquals(cfAmt, amount);
	}
}
