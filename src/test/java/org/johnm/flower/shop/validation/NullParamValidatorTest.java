package org.johnm.flower.shop.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class NullParamValidatorTest {
	private NullParamValidator validator;
	
	@Before
	public void setup() {
		validator = new NullParamValidator();
	}
	
	@Test
	public void check_nullObject() {
		final Object param = null;
		final String fieldName = "Obj";
		
		try {
			validator.checkNotNull(param, fieldName);
			fail("should not reach here");
		} catch (IllegalArgumentException ex) {
			assertEquals(fieldName + NullParamValidator.MSG, ex.getMessage());
		}
	}
	
	@Test
	public void checkNotNullObject() {
		final String param = "xxxx";
		final String fieldName = "Obj";
		
		try {
			validator.checkNotNull(param, fieldName);
		} catch (IllegalArgumentException ex) {
			fail("should not reach here");
		}
	}
}
