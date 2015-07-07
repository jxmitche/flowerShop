package org.johnm.flower.shop.client;

import java.net.URL;

import org.junit.Test;

public class ReadMeRunMeTest {

	@Test
	public void checkMainExampleFromAssignment() {
		final URL url = ReadMeRunMeTest.class.getClassLoader().getResource("test1.txt");
		final String[] args = {url.getFile()};
		ReadMeRunMe.main(args);
		System.out.println("-----------");
	}
	
	@Test
	public void checkMainForEachFlowerBiggestBundleDoesntWorkMustUseSecondBundle() {
		final URL url = ReadMeRunMeTest.class.getClassLoader().getResource("test2.txt");
		final String[] args = {url.getFile()};
		ReadMeRunMe.main(args);
		System.out.println("-----------");
	}
	
	@Test
	public void checkMainForEachFlowerBiggestBundleAndSecondBundlesDontWorkMustUseThirdBundle() {
		final URL url = ReadMeRunMeTest.class.getClassLoader().getResource("test3.txt");
		final String[] args = {url.getFile()};
		ReadMeRunMe.main(args);
		System.out.println("-----------");
	}
}
