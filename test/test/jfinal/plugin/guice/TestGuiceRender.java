package test.jfinal.plugin.guice;

import jfinal.ext.test.ControllerTestCase;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestGuiceRender extends ControllerTestCase {
	@BeforeClass
	public static void init() throws Exception{
		start(new GuiceConfig());
	}
	
	@Test
	public void test() throws Exception {
		invoke("/guice");
	}
}
