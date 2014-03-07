package cft.commons.core.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cft.commons.core.constant.Constants;

/**
 * @author daniel
 *
 */
public class HttpClientUtils {

	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	public static String httpGet(String url, int cTimeout, int sTimeout) throws IOException {

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(sTimeout).setConnectTimeout(cTimeout).build();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

		CloseableHttpResponse response = null;
		String responseBody = null;

		try {

			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("Accept-Charset", Constants.ENCODING_UTF8);

			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			int statusCode = response.getStatusLine().getStatusCode();
			logger.info("HttpClientUtils:httpGet:response statusCode = " + statusCode);

			if (statusCode < 400 && entity != null) {
				responseBody = EntityUtils.toString(entity, Constants.ENCODING_UTF8);
			}

		} catch (Exception ex) {
			logger.error("Exception during Http Get: ", ex);
		} finally {
			response.close();
			httpClient.close();
		}

		return responseBody;
	}

	public static String httpPost(Map<String, String> nvpMap, String requestUrl, int cTimeout, int sTimeout)
			throws IOException {

		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(sTimeout).setConnectTimeout(cTimeout).build();
		CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

		CloseableHttpResponse response = null;
		String responseBody = null;

		try {

			HttpPost httpPost = new HttpPost(requestUrl);

			//Set Proxy
			/*			HttpHost hcProxyHost = new HttpHost("proxy.pccw.com", 8080, "http");
						httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, hcProxyHost);*/

			//httpPost.setEntity(new StringEntity(xmlStr, "text/xml", HTTP.UTF_8));

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (String key : nvpMap.keySet()) {
				nvps.add(new BasicNameValuePair(key, nvpMap.get(key)));
			}

			httpPost.setEntity(new UrlEncodedFormEntity(nvps, Constants.ENCODING_UTF8));

			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();

			int statusCode = response.getStatusLine().getStatusCode();
			logger.info("HttpClientUtils:httpPost:response statusCode = " + statusCode);

			if (statusCode < 400 && entity != null) {
				responseBody = EntityUtils.toString(entity, Constants.ENCODING_UTF8);
			}

		} catch (Exception ex) {
			logger.error("Exception during Http Post: ", ex);
		} finally {
			response.close();
			httpClient.close();
		}

		return responseBody;
	}

}
