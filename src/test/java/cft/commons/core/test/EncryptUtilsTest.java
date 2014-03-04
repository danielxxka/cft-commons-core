package cft.commons.core.test;

import cft.commons.core.helper.encrypt.EncryptUtils;

import org.junit.Test;

public class EncryptUtilsTest {
	
	@Test
	public void test() throws Exception{
		
		String data = "热门行业机密";
		
		System.out.println("SHA512 = " + EncryptUtils.encryptSHA512(data));
		
		

	}

}
