package cft.commons.core.test;

import cft.commons.core.helper.encrypt.DESUtils;

import org.junit.Test;

public class DESUtilsTest {

	@Test
	public void main() throws Exception {

		final String key = "wwerfdf123!@#669";
		DESUtils des = new DESUtils(key);

		String str1 = "重要机密文件内容,http://www.finance.org/payment=ture&count=999";
		System.out.println(" 加密前： " + str1);
		// DES 加密字符串
		String str2 = des.encryptStr(str1);
		System.out.println(" 加密后： " + str2);

		// DES 解密字符串
		DESUtils des2 = new DESUtils(key);
		String deStr = des2.decryptStr(str2);
		System.out.println(" 解密后： " + deStr);

	/*	des.encryptFile("/test1.zip", "/test1.zip.des");
		des.decryptFile("/test1.zip.des", "/eee.zip");*/
	}

}
