package cft.commons.core.helper.encrypt;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import cft.commons.core.util.Exceptions;

/**
 * @author daniel
 *
 */
public class AESUtils {

	private static final String KEY_ALGORITHM = "AES";
	private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final int DEFAULT_AES_KEYSIZE = 128;

	public static byte[] encrypt(byte[] data, byte[] key) {
		return aes(data, key, Cipher.ENCRYPT_MODE);
	}

	public static byte[] decrypt(byte[] data, byte[] key) {
		return aes(data, key, Cipher.DECRYPT_MODE);
	}

	private static byte[] aes(byte[] data, byte[] key, int mode) {
		try {
			SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(mode, secretKey);
			return cipher.doFinal(data);

		} catch (GeneralSecurityException e) {
			throw Exceptions.unchecked(e);
		}
	}

	public static byte[] generateAesKey() {
		return generateAesKey(DEFAULT_AES_KEYSIZE);
	}

	public static byte[] generateAesKey(int keysize) {
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
			keyGenerator.init(keysize);
			SecretKey secretKey = keyGenerator.generateKey();
			return secretKey.getEncoded();
		} catch (GeneralSecurityException e) {
			throw Exceptions.unchecked(e);
		}
	}
}
