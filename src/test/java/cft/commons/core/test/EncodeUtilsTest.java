/**
 * 
 */
package cft.commons.core.test;

import cft.commons.core.util.EncodeUtils;

import org.junit.Test;

/**
 * @author daniel
 *
 */
public class EncodeUtilsTest {

	@Test
	public void main() {

		String url = "http://www.google.com/search?hl=zh-cn&newwindow=1&q=中国大百科在线全文检索&btng=搜索&lr=";

		String encodeUrl = EncodeUtils.urlEncode(url);

		System.out.println(encodeUrl);

		if (EncodeUtils.isUtf8Url(encodeUrl)) {
			System.out.println(EncodeUtils.urlDecode(encodeUrl));
		} else {
			System.out.println("not utf8encodeUrl");
		}

		String htmlDoc = "<html>body</html>";

		System.out.println(EncodeUtils.escapeHtml(htmlDoc));
		System.out.println(EncodeUtils.unescapeHtml(EncodeUtils.escapeHtml(htmlDoc)));


		System.out.println(EncodeUtils.encodeBase62("hello world!".getBytes()));
		

		String baseUrlSafe64Str = EncodeUtils.encodeBase64URLSafeString(url.getBytes());
		String base64Str = EncodeUtils.encodeBase64String(url.getBytes());
		System.out.println(baseUrlSafe64Str);
		System.out.println(base64Str);
		
		System.out.println(EncodeUtils.decodeBase64String(baseUrlSafe64Str));
		System.out.println(EncodeUtils.decodeBase64String(base64Str));
		

	}

}
