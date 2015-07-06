package org.johnm.flower.shop.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ReadFileTest {
	private ReadFile readFile;

	@Before
	public void setup() {
		final URL url = ReadFileTest.class.getClassLoader().getResource("test.txt");
		readFile = new ReadFile(url.getFile());
	}
	
	@Test
	public void check_file() {
		final List<String> lines = readFile.readFile();
		
		assertNotNull(lines);
		assertEquals(1, lines.size());
		assertEquals("10 R12", lines.get(0));
	}
	
	@Test
	public void checkNonExistentFile() {
		readFile = new ReadFile("XXX.txt");

		try {
			readFile.readFile();
			fail("should not reach here");
		} catch (IllegalArgumentException ex) {
			assertEquals("File not found XXX.txt", ex.getMessage());
		}
	}
	
	@Test
	public void check_NullFileName() {
		try {
			readFile = new ReadFile(null);
			fail("should not reach here");
		} catch (IllegalArgumentException ex) {
			assertEquals("Path must not be null", ex.getMessage());
		}
	}
}
