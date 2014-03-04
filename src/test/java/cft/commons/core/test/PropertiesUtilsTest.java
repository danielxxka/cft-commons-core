package cft.commons.core.test;

import java.util.Properties;

import org.junit.Test;

import cft.commons.core.util.PropertiesUtils;

public class PropertiesUtilsTest {

	@Test
	public void loadProperties() {

		Properties p = new PropertiesUtils("classpath:/cft-commons-core.properties").getProperties();

		System.out.println("project.name = " + p.getProperty("project.name"));
	}

}
