package test.com.jfinal.ext;

import jfinal.ext.route.ControllerBind;

import com.jfinal.core.Controller;

@ControllerBind(controllerKey = "/aa",viewPath="WEB-INF/")
//@ControllerBind(controllerKey = "/t")
public class AController extends Controller {
	public void index(){
		setAttr("name", "zhoulei");
		setAttr("age", 10*2+4);
		render("add.html");
	}
}
