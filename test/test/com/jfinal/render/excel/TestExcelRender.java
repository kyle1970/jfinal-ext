package test.com.jfinal.render.excel;

import jfinal.test.ControllerTestCase;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestExcelRender extends ControllerTestCase {
	@BeforeClass
	public static void init() throws Exception{
		start(new ExcelConfig());
	}
	
	@Test
	public void testDeleteError() throws Exception {
		invoke("/excel");
	}
}
