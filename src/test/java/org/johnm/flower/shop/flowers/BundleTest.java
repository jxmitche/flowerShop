package org.johnm.flower.shop.flowers;

import static org.junit.Assert.assertEquals;

import org.johnm.flower.shop.money.AussieDollars;
import org.junit.Test;

public class BundleTest {
	
	@Test
	public void checkBundleConstructorValues() {
		final Bundle bundle = new Bundle(1, 299);
		
		assertEquals(1, bundle.getNumberInBundle());
		
		final AussieDollars dollars = new AussieDollars(299);
		assertEquals(dollars, bundle.getPricePerBundle());
		
		assertEquals(new Bundle(1, 299), bundle);
	}
}
