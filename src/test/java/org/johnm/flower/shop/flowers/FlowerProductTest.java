package org.johnm.flower.shop.flowers;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FlowerProductTest {
	private List<Bundle> bundles;
	
	@Before
	public void setup() {
		bundles = new ArrayList<Bundle>();
	}
	
	@Test
	public void checkConstructor() {
		final FlowerProduct flowerProduct = new FlowerProduct(FlowerType.ROSE, bundles);
		
		assertEquals(FlowerType.ROSE, flowerProduct.getFlowerType());
		assertEquals(bundles, flowerProduct.getBundles());
	}
	
	@Test
	public void checkConstructorNullFlowerType() {
		try {
			new FlowerProduct(null, bundles);
			fail("should not reach here");
		} catch (IllegalArgumentException ex) {
			assertEquals("flowerType must not be null", ex.getMessage());
		}
	}
	
	@Test
	public void checkConstructorNullBundleList() {
		try {
			new FlowerProduct(FlowerType.ROSE, null);
			fail("should not reach here");
		} catch (IllegalArgumentException ex) {
			assertEquals("bundles must not be null", ex.getMessage());
		}
	}
}
