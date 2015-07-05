package org.johnm.flower.shop.flowers;

import static org.junit.Assert.*;

import org.junit.Test;

public class FlowerTypeTest {

	@Test
	public void checkFlowerCodes() {
		assertEquals("R12", FlowerType.ROSE.getCode());
		assertEquals("L09", FlowerType.LILY.getCode());
		assertEquals("T58", FlowerType.TULIP.getCode());
	}
}
