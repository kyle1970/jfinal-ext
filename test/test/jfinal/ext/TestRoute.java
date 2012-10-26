package test.jfinal.ext;

import static org.junit.Assert.assertEquals;
import jfinal.ext.test.ControllerTestCase;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestRoute extends ControllerTestCase {
	@BeforeClass
	public static void init() throws Exception{
		start(new Config());
	}
	@Test
	public void testAController() throws Exception {
		invoke("/aa");
		assertEquals("zhoulei", findAttrAfterInvoke("name"));
		assertEquals(24, findAttrAfterInvoke("age"));
	}
	@Test
	public void testBController() throws Exception {
		invoke("/bb");
		assertEquals("zhoulei", findAttrAfterInvoke("name"));
		assertEquals(24, findAttrAfterInvoke("age"));
	}


}
