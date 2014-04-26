package cft.commons.core.test;

import cft.commons.core.util.IdentityUtils;

import org.junit.Test;

public class IdentityUtilsTest {

	@Test
	public void testUuid() {
		System.out.println(IdentityUtils.uuid());
		System.out.println(IdentityUtils.uuid2());

		for (int i = 1; i <= 10; i++) {
			System.out.println(IdentityUtils.randomLong());
		}
	}

	//@Test
	public void testHostName() {
		System.out.println("host name = " + IdentityUtils.getLocalHostName());
	}

}
