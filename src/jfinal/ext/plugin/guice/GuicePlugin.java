package jfinal.ext.plugin.guice;

import com.google.inject.Guice;
import com.google.inject.Module;
import com.jfinal.plugin.IPlugin;

public class GuicePlugin implements IPlugin {
	private Module module;
	public GuicePlugin(Module module){
		this.module = module;
	}
	@Override
	public boolean start() {
		IocInterceptor.injector = Guice.createInjector(module);
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}

}
