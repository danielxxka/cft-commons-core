package cft.commons.core.test;

import cft.commons.core.util.IdentityUtils;

import org.apache.commons.configuration.AbstractFileConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;

/**
 * @author daniel
 *
 */
public class ConfigurationTest {

	//@Test
	public void main() throws ConfigurationException {
		Configuration config = new PropertiesConfiguration("cft-commons-core.properties");
		
		System.out.println("orginal config value = " + config.getString("project.name", "default"));
		config.setProperty("project.name", IdentityUtils.uuid());

		((AbstractFileConfiguration) config).save();
		
		System.out.println("updated config value = " + config.getString("project.name"));
	}

}
