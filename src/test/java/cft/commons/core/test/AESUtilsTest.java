package cft.commons.core.test;

import cft.commons.core.helper.encrypt.AESUtils;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class AESUtilsTest {

	@Test
	public void testASE() {

		String str = "重要中文机密信息：user_token:!@#$%^&*12345678";
		System.out.println("org str = " + str);

		byte[] key = AESUtils.generateAesKey();
		String keyStr = Base64.encodeBase64String(key);
		//System.out.println("key = " + key.toString());
		System.out.println("密匙字符串 = "+ keyStr);

		//开始加密
		byte[] ecData = AESUtils.encrypt(str.getBytes(), key);
		String tsStr = Base64.encodeBase64String(ecData);
		System.out.println("encrypt str = " + tsStr);

		//开始解密
		String deStr = new String(AESUtils.decrypt(Base64.decodeBase64(tsStr), Base64.decodeBase64(keyStr)));
		System.out.println("decrypt str = " + deStr);
	}

}
