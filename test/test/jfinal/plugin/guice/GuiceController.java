package test.jfinal.plugin.guice;

import jfinal.ext.plugin.guice.IocInterceptor;

import com.google.inject.Inject;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

public class GuiceController extends Controller {
	@Inject
	private  Client client;
	@Before(IocInterceptor.class)
	public void index() {
		client.work();
	}
}
