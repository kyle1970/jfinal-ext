package test.com.jfinal.plugin.sqlinxml;

import static org.junit.Assert.assertEquals;
import jfinal.ext.plugin.sqlinxml.SqlInXmlPlugin;
import jfinal.ext.plugin.sqlinxml.SqlKit;

import org.junit.Test;


public class TestSqlinxml {

	@Test
	public void test() throws InterruptedException {
		SqlInXmlPlugin plugin = new SqlInXmlPlugin();
		plugin.start();
		assertEquals("select * from blog",SqlKit.sql("blog.findBlog"));
		assertEquals("select * from user",SqlKit.sql("blog.findUser"));
	}
	

}
