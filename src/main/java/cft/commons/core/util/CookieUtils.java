package cft.commons.core.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie Utils
 * 
 * @author daniel
 *
 */
public class CookieUtils {

	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setPath("/");
		if (maxAge > 0)
			cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	public static Cookie getCookie(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = CookieUtils.readCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = (Cookie) cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	public static String getCookieValue(HttpServletRequest request, String name) {
		if (name != null) {
			Cookie cookie = getCookie(request, name);
			if (cookie != null) {
				return cookie.getValue();
			}
		}
		return "";
	}

	public static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (int i = 0; i < cookies.length; i++) {
				cookieMap.put(cookies[i].getName(), cookies[i]);
			}
		}
		return cookieMap;
	}

	public static boolean deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		if (name != null) {
			Cookie cookie = getCookie(request, name);
			if (cookie != null) {
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
				return true;
			}
		}
		return false;
	}
}
