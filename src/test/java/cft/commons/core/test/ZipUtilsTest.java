package cft.commons.core.test;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import cft.commons.core.util.ZipUtils;

public class ZipUtilsTest {

	@Test
	public void zipDir() throws IOException {

		ZipUtils zu = new ZipUtils("/test1.zip");
		File file = new File("/test1");

		if (file.exists()) {

			try {
				zu.createZipOut();
				zu.zipFiles(file, "name-in-package");
				zu.closeZipOut();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			throw new IOException("target files are not exsit.");
		}
	}
	
	@Test
	public void upZip(){
		
		try {
			ZipUtils.unZipToFolder("/test1.zip", "/test2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
