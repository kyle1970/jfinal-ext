package jfinal.ext.plugin.guice;

import java.lang.reflect.Field;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class IocInterceptor implements Interceptor {
	static Injector  injector ;
	@Override
	public void intercept(ActionInvocation ai) {
		Controller controller = ai.getController();
		Field[] fields = controller.getClass().getDeclaredFields();
		for (Field field : fields){
			if(field.getAnnotation(Inject.class)==null){
				continue;
			}
//			if(Modifier.isFinal(field.getModifiers())){
//				continue;
//			}
			injectField(controller, field);
		}
		ai.invoke();
	}

	private void injectField(Controller controller, Field field) {
		try {
			field.setAccessible(true);
			field.set(controller, injector.getInstance(field.getType()));
			field.setAccessible(false);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
