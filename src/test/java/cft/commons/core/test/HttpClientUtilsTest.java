package cft.commons.core.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cft.commons.core.util.HttpClientUtils;

/**
 * @author daniel
 *
 */
public class HttpClientUtilsTest {

	@Test
	public void httpGet() throws IOException {
			System.out.println("Content = " + HttpClientUtils.httpGet("http://www.baidu.com", 3000, 5000));
	}

	@Test
	public void httpPost() throws Exception {

		String xmlString = "<bean id=\"rptspmb002\" class=\"org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView\">"
				+ "<property name=\"url\" value=\"/WEB-INF/classes/rpt/rptspmb001/rptspmb001.jasper\" /></bean> ";

		Map<String, String> map = new HashMap<String, String>();
		map.put("xml", xmlString);
		map.put("deviceToken", "@#%@#^@!#^@!#!%%!");
		map.put("createDate", "2013-10-01 11:23:00");

		System.out.println(HttpClientUtils.httpPost(map, "http://localhost/api/test", 2000, 5000));

	}
}
