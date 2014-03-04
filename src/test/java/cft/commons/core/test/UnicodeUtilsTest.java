package cft.commons.core.test;

import cft.commons.core.util.UnicodeUtils;

import org.junit.Test;

/**
 * @author daniel
 *
 */
public class UnicodeUtilsTest {

	@Test
	public void test() {

		String unStr = UnicodeUtils.toUnicode("使用 Unicode 編碼方式");

		System.out.println(unStr);
		System.out.println(UnicodeUtils.deUnicode(unStr));

	}

}
