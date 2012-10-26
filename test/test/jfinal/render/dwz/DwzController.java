package test.jfinal.render.dwz;

import jfinal.ext.render.DwzRender;

import com.jfinal.core.Controller;

public class DwzController extends Controller {
	public void delete() {
		int id = getParaToInt(0);
		if (id % 2 == 0) {
			render(DwzRender.success());
		} else {
			render(DwzRender.error("该记录已经删除"));
		}
	}
}
