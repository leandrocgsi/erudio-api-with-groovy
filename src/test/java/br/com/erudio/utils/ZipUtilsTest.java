package br.com.erudio.utils;

import org.junit.Before;
import org.junit.Test;

public class ZipUtilsTest {
	
	ZipUtils zipUtils = new ZipUtils();
	
	@Before
	public void setup() {
	}
	
	@Test
	public void zipFileTest() {
		zipUtils.zipFile("C://Users//LEANDRO//Desktop//teste2.zip");
	}

}
