package org.johnm.flower.shop.client;

import java.net.URL;

import org.junit.Test;

public class ReadMeRunMeTest {

	@Test
	public void checkMain() {
		final URL url = ReadMeRunMeTest.class.getClassLoader().getResource("test1.txt");
		final String[] args = {url.getFile()};
		ReadMeRunMe.main(args);
	}
}
