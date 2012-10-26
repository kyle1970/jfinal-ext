package test.jfinal.ext;

import jfinal.ext.route.ControllerBind;

import com.jfinal.core.Controller;

@ControllerBind(controllerKey = "/bb")
//@ControllerBind(controllerKey = "/t")
public class BController extends Controller {
	public void index(){
		setAttr("name", "zhoulei");
		setAttr("age", 10*2+4);
		render("add.html");
	}
}
