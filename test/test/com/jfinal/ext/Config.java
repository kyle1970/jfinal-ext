package test.com.jfinal.ext;

import jfinal.ext.route.AutoControllerRegist;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.tx.TxByRegex;
import com.jfinal.render.ViewType;

public class Config extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setEncoding("utf-8");
		me.setDevMode(true);
		me.setViewType(ViewType.JSP);
	}
   
	@Override
	public void configRoute(Routes me) {
		AutoControllerRegist.regist(me);
//		me.add("/t", AController.class,"WEB-INF");

//		me.add("/t",AController.class);
	}

	@Override
	public void configPlugin(Plugins me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new TxByRegex(".*.save"));
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub
		
	}

}
