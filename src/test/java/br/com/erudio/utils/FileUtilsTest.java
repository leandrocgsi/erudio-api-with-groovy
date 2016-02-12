package br.com.erudio.utils;

import org.junit.Before;
import org.junit.Test;

import br.com.erudio.utils.FileUtils;

public class FileUtilsTest {
	
	FileUtils fileUtils = new FileUtils();
	
	@Before
	public void setup() {
	}
	
	@Test
	public void copyFileTest() {
		fileUtils.copyFile("C://Users//leandro.goncalvesc.CTIS//Desktop//pom2.xml", "C://Users//leandro.goncalvesc.CTIS//Desktop//COPY//copied.xml");
	}
	
	@Test
	public void printFileTest() {
		fileUtils.printFile("C://Users//leandro.goncalvesc.CTIS//Desktop//pom2.xml");
	}
	
	@Test
	public void fileToStringTest() {
		System.out.println(fileUtils.fileToString("C://Users//leandro.goncalvesc.CTIS//Desktop//pom2.xml"));
	}

}
