package org.johnm.flower.shop.flowers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FlowerProductFactoryTest {
	private FlowerProductFactory factory;
	
	@Before
	public void setup() {
		factory = new FlowerProductFactory();
	}
	
	@Test
	public void checkGetFlowerProductForCodeWithRose() {
		final FlowerProduct product = factory.getFlowerProductForCode(FlowerType.ROSE.getCode());
		
		assertNotNull(product);
		assertEquals(FlowerType.ROSE, product.getFlowerType());
		
		final List<Bundle> bundles = product.getBundles();
		
		assertEquals(2, bundles.size());
		assertEquals(new Bundle(5, 699), bundles.get(0));
		assertEquals(new Bundle(10, 1299), bundles.get(1));
	}
	
	@Test
	public void checkGetFlowerProductForCodeWithLily() {
		final FlowerProduct product = factory.getFlowerProductForCode(FlowerType.LILY.getCode());
		
		assertNotNull(product);
		assertEquals(FlowerType.LILY, product.getFlowerType());
		
		final List<Bundle> bundles = product.getBundles();
		
		assertEquals(3, bundles.size());
		assertEquals(new Bundle(3, 995), bundles.get(0));
		assertEquals(new Bundle(6, 1695), bundles.get(1));
		assertEquals(new Bundle(9, 2495), bundles.get(2));
	}
	
	@Test
	public void checkGetFlowerProductForCodeWithTulip() {
		final FlowerProduct product = factory.getFlowerProductForCode(FlowerType.TULIP.getCode());
		
		assertNotNull(product);
		assertEquals(FlowerType.TULIP, product.getFlowerType());
		
		final List<Bundle> bundles = product.getBundles();
		
		assertEquals(3, bundles.size());
		assertEquals(new Bundle(3, 595), bundles.get(0));
		assertEquals(new Bundle(5, 995), bundles.get(1));
		assertEquals(new Bundle(9, 1699), bundles.get(2));
	}
	
	@Test
	public void checkGetFlowerProductForCodeWithNullCode() {
		try {
			factory.getFlowerProductForCode(null);
			fail("should not reach here");
		} catch (IllegalArgumentException ex) {
			assertEquals("code must not be null", ex.getMessage());
		}
	}
	
	@Test
	public void checkGetFlowerProductForCodeWithUnknownCode() {
		try {
			factory.getFlowerProductForCode("adsoijdfljsadfljdsaflkjdflakj");
			fail("should not reach here");
		} catch (IllegalArgumentException ex) {
			assertEquals("Unknown code: adsoijdfljsadfljdsaflkjdflakj", ex.getMessage());
		}
	}
}
