package cft.commons.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

import cft.commons.core.constant.Constants;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符编码工具类 
 * 
 * @author: daniel
 * 
 */
public class EncodeUtils {

	private static final Logger logger = LoggerFactory.getLogger(EncodeUtils.class);

	private static char[] BASE62 = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

	/**
	 * URL UTF8编码
	 */
	public static final String urlEncode(String text) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < text.length(); i++) {

			char c = text.charAt(i);
			if (c >= 0 && c <= 255) {
				result.append(c);
			} else {

				byte[] b = new byte[0];
				try {
					b = Character.toString(c).getBytes(Constants.ENCODING_UTF8);
				} catch (Exception ex) {
				}

				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					result.append("%" + Integer.toHexString(k).toUpperCase());
				}

			}
		}
		return result.toString();
	}

	/**
	 * URL UTF8 解码
	 */
	public static final String urlDecode(String text) {
		String result = "";
		int p = 0;

		if (text != null && text.length() > 0) {
			text = text.toLowerCase();
			p = text.indexOf("%e");
			if (p == -1)
				return text;

			while (p != -1) {
				result += text.substring(0, p);
				text = text.substring(p, text.length());
				if (text == "" || text.length() < 9)
					return result;

				result += CodeToWord(text.substring(0, 9));
				text = text.substring(9, text.length());
				p = text.indexOf("%e");
			}

		}

		return result + text;
	}

	/**
	 * utf8URL编码转字符
	 */
	private static final String CodeToWord(String text) {
		String result;

		if (Utf8codeCheck(text)) {
			byte[] code = new byte[3];
			code[0] = (byte) (Integer.parseInt(text.substring(1, 3), 16) - 256);
			code[1] = (byte) (Integer.parseInt(text.substring(4, 6), 16) - 256);
			code[2] = (byte) (Integer.parseInt(text.substring(7, 9), 16) - 256);
			try {
				result = new String(code, Constants.ENCODING_UTF8);
			} catch (UnsupportedEncodingException ex) {
				result = null;
			}
		} else {
			result = text;
		}

		return result;
	}

	/**
	 * 编码是否有效
	 * @param text
	 * @return
	 */
	private static final boolean Utf8codeCheck(String text) {
		String sign = "";
		if (text.startsWith("%e"))
			for (int i = 0, p = 0; p != -1; i++) {
				p = text.indexOf("%", p);
				if (p != -1)
					p++;
				sign += p;
			}
		return sign.equals("147-1");
	}

	/**
	 * 判断是否Utf8Url编码
	 * @param text
	 * @return
	 */
	public static final boolean isUtf8Url(String text) {
		text = text.toLowerCase();
		int p = text.indexOf("%");
		if (p != -1 && text.length() - p > 9) {
			text = text.substring(p, p + 9);
		}
		return Utf8codeCheck(text);
	}

	/**
	 * Html 转码.
	 */
	public static String escapeHtml(String html) {
		return StringEscapeUtils.escapeHtml(html);
	}

	/**
	 * Html 解码.
	 */
	public static String unescapeHtml(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml(htmlEscaped);
	}

	/**
	 * Xml 转码.
	 */
	public static String escapeXml(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * Xml 解码.
	 */
	public static String unescapeXml(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}

	/**
	 * 转换编码 ISO-8859-1到UTF-8
	 * @param text
	 * @return
	 */
	public static final String ISO2UTF8(String text) {
		String result = "";
		try {
			result = new String(text.getBytes("ISO-8859-1"), Constants.ENCODING_UTF8);
		} catch (UnsupportedEncodingException ex) {
			result = ex.toString();
		}
		return result;
	}

	/**
	 * 转换编码 UTF-8到ISO-8859-1
	 * @param text
	 * @return
	 */
	public static final String UTF82ISO(String text) {
		String result = "";
		try {
			result = new String(text.getBytes(Constants.ENCODING_UTF8), "ISO-8859-1");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		return result;
	}

	/**
	 * Hex编码.
	 */
	public static String encodeHex(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw Exceptions.unchecked(e);
		}
	}

	/**
	 * Base64编码.
	 */
	public static String encodeBase64String(byte[] input) {
		return Base64.encodeBase64String(input);
	}

	/**
	 * Base64解码.
	 */
	public static String decodeBase64String(String input) {
		return new String(Base64.decodeBase64(input));
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
	 */
	public static String encodeBase64URLSafeString(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base62编码。
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[((input[i] & 0xFF) % BASE62.length)];
		}
		return new String(chars);
	}

	/**
	 * 对象转换成字节数组
	 * 
	 * 
	 * @param obj
	 * @return
	 */
	public final static byte[] objToBytes(Object obj) {
		ByteArrayOutputStream bao = null;
		ObjectOutputStream oos = null;
		try {
			bao = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bao);
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			return bao.toByteArray();
		} catch (Exception e) {
			logger.error("Exception during EncodeUtils.objToBytes:", e);
			return null;
		} finally {
			try {
				if (bao != null) {
					bao.close();
					bao = null;
				}
			} catch (IOException e) {
				logger.error("Exception during EncodeUtils.objToBytes:", e);
			}
		}
	}

	/**
	 * 字节数组转成对象
	 * 
	 * @param bytes
	 * @return
	 */
	public final static Object bytesToObj(byte[] bytes) {
		ByteArrayInputStream bai = null;
		ObjectInputStream ois = null;
		try {
			bai = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bai);
			Object obj = ois.readObject();
			ois.close();
			ois = null;
			return obj;
		} catch (Exception e) {
			logger.error("Exception during EncodeUtils.bytesToObj:", e);
			return null;
		} finally {
			try {
				if (bai != null) {
					bai.close();
					bai = null;
				}
			} catch (IOException e) {
				logger.error("Exception during EncodeUtils.bytesToObj:", e);
			}
		}
	}
}
