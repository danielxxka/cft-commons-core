package cft.commons.core.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cft.commons.core.util.HttpClientUtils;

/**
 * @author daniel
 *
 */
public class HttpClientUtilsTest {
	
	
		@SuppressWarnings("unused")
		@Test
		public void httpPost() throws Exception{

		String xmlString = "<bean id=\"rptspmb002\" class=\"org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView\">"
				+ "<property name=\"url\" value=\"/WEB-INF/classes/rpt/rptspmb001/rptspmb001.jasper\" /></bean> ";
		
		Map<String,String> map = new HashMap<String,String>();
		//map.put("deviceId", "ABCD");
		map.put("deviceToken", "@#%@#^@!#^@!#!%%!");
		//map.put("createDate", "2013-10-01 11:23:00");
		
		
		System.out.println(HttpClientUtils.httpPost(map, "http://localhost/api/test", 5000, 5000));

	}
}
