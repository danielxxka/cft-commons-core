package cft.commons.core.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
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

	public static String httpGet(String apiUrl, int cTimeout, int sTimeout) {

		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, cTimeout);
		HttpConnectionParams.setSoTimeout(params, sTimeout);
		HttpClient httpClient = new DefaultHttpClient(params);

		String responseBody = null;

		try {
			HttpGet httpGet = new HttpGet(apiUrl);
			httpGet.addHeader("Accept-Charset", Constants.ENCODING_UTF8);

			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();

			logger.info("HttpClientUtils:httpGet:response statusCode = " + response.getStatusLine().getStatusCode());

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && entity != null) {
				responseBody = EntityUtils.toString(entity, Constants.ENCODING_UTF8);
			}

			httpGet.abort();
		} catch (Exception ex) {
			logger.error("Exception during Http Get: ", ex);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		return responseBody;
	}

	public static String httpPost(Map<String, String> nvpMap, String requestUrl, int cTimeout, int sTimeout)
			throws HttpException {

		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, cTimeout);
		HttpConnectionParams.setSoTimeout(params, sTimeout);
		HttpClient httpClient = new DefaultHttpClient(params);

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

			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();

			logger.info("HttpClientUtils:httpPostXML:response statusCode = " + response.getStatusLine().getStatusCode());

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK && entity != null) {
				responseBody = EntityUtils.toString(entity, Constants.ENCODING_UTF8);
			}

			httpPost.abort();
		} catch (Exception ex) {
			logger.error("Exception during Http Get: ", ex);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		return responseBody;
	}

}
