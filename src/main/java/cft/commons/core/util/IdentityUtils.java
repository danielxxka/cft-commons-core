package cft.commons.core.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * 
 * @author daniel
 */
public class IdentityUtils {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间有-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid2() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long. 
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}

	/**
	 * 获取本机名称
	 * 
	 * @return
	 */
	public static String getLocalHostName() {
		String localHostname = "";
		try {
			localHostname = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			localHostname = "UnknownHost";
		}

		return localHostname;
	}

}
