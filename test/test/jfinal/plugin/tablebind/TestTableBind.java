package test.jfinal.plugin.tablebind;

import jfinal.ext.plugin.tablebind.AutoTableBindPlugin;
import jfinal.ext.plugin.tablebind.TableNameStyle;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jfinal.plugin.druid.DruidPlugin;

public class TestTableBind {
	@BeforeClass
	public static void init() {
		DruidPlugin c3p0 = new DruidPlugin(
				"jdbc:mysql://127.0.0.1/jfinal_demo", "root", "root");
		AutoTableBindPlugin atbp = new AutoTableBindPlugin(c3p0,TableNameStyle.LOWER);
		atbp.addJar("modelInJar.jar");
		c3p0.start();
		atbp.start();
	}

	@Test
	public void test01() throws InterruptedException {
	}

}
