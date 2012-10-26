package test.jfinal.plugin.cron4j;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import jfinal.ext.plugin.cron.Cron4jPlugin;

import org.junit.Test;

public class TestCron4j {

	@Test
	public void test() throws InterruptedException {
		Cron4jPlugin cron4jPlugin = new Cron4jPlugin("cronjob.properties");
		cron4jPlugin.start();
		TimeUnit.MINUTES.sleep(2);
		assertEquals(2, JobA.callTime);
		assertEquals(0, JobB.callTime);
		
	}

}
