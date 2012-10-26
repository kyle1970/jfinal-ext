package test.com.jfinal.plugin.config;

import jfinal.ext.plugin.config.ConfigKit;
import jfinal.ext.plugin.config.ConfigPlugin;
import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;


public class TestConfig {
	@BeforeClass
	public static void init() {
		ConfigPlugin configPlugin = new ConfigPlugin();
		configPlugin.addResource(".*.properties");
		configPlugin.start();
	}

	@Test
	public void testGetStr() throws InterruptedException {
		Assert.assertEquals("test",ConfigKit.getStr("name"));
		Assert.assertEquals(1,ConfigKit.getInt("age"));
	}

}
