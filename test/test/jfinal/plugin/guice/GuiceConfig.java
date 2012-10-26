package test.jfinal.plugin.guice;

import static com.google.inject.name.Names.named;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

import jfinal.ext.plugin.guice.GuicePlugin;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.render.ViewType;

public class GuiceConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setEncoding("utf-8");
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/guice", GuiceController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		GuicePlugin guicePlugin = new GuicePlugin(new Module() {
			public void configure(Binder binder) {
				Properties p = new Properties();
				try {
					p.load(new InputStreamReader(this.getClass().getClassLoader()
							.getResourceAsStream("guice.properties")));
				} catch (IOException e) {
					e.printStackTrace();
					assert false;
				}
				Enumeration e = p.keys();
				while (e.hasMoreElements()) {
					String key = (String) e.nextElement();
					String value = (String) p.get(key);
					binder.bindConstant().annotatedWith(named("config." + key))
							.to(value);
				}
			}
		});
		me.add(guicePlugin);

	}

	@Override
	public void configInterceptor(Interceptors me) {
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub

	}

}
