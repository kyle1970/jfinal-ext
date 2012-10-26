package jfinal.ext.render;


import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import com.jfinal.render.Render;
import com.jfinal.render.RenderException;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
@SuppressWarnings("serial")
/**
 *  <http://my.oschina.net/alvinte/blog/69030>
 * @author alvinte
 *
 */
public class FreeMarkerXMLRender extends Render {
	
	private transient static final String encoding = getEncoding();
	private transient static final String contentType = "text/html; charset=" + encoding;
	
	private transient static final Configuration config = new Configuration();
	
	public FreeMarkerXMLRender(String view) {
		this.view = view;
		}

	/**
	 * freemarker can not load freemarker.properies automatically
	 */
	public static Configuration getConfiguration() {
		return config;
	}
	
    static void init(ServletContext servletContext, Locale locale, int template_update_delay) {
        // Initialize the FreeMarker configuration;
        // - Create a configuration instance
        // config = new Configuration();
        // - Templates are stoted in the WEB-INF/templates directory of the Web app.
        config.setServletContextForTemplateLoading(servletContext, "/");	// "WEB-INF/templates"
        // - Set update dealy to 0 for now, to ease debugging and testing.
        //   Higher value should be used in production environment.
        
        if (getDevMode()) {
        	config.setTemplateUpdateDelay(0);
       	}
        else {
        	config.setTemplateUpdateDelay(template_update_delay);
        }
        
        // - Set an error handler that prints errors so they are readable with
        //   a HTML browser.
        // config.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        
        // - Use beans wrapper (recommmended for most applications)
        config.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
        // - Set the default charset of the template files
        config.setDefaultEncoding(encoding);		// config.setDefaultEncoding("ISO-8859-1");
        // - Set the charset of the output. This is actually just a hint, that
        //   templates may require for URL encoding and for generating META element
        //   that uses http-equiv="Content-type".
        config.setOutputEncoding(encoding);			// config.setOutputEncoding("UTF-8");
        // - Set the default locale
        config.setLocale(locale /* Locale.CHINA */ );		// config.setLocale(Locale.US);
        config.setLocalizedLookup(false);
        
        // 去掉int型输出时的逗号, 例如: 123,456
        // config.setNumberFormat("#");		// config.setNumberFormat("0"); 也可以
        config.setNumberFormat("#0.#####");
    }
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void render() {
		response.setContentType(contentType);
        Enumeration<String> attrs = request.getAttributeNames();
		Map root = new HashMap();
		while (attrs.hasMoreElements()) {
			String attrName = attrs.nextElement();
			root.put(attrName, request.getAttribute(attrName));
		}
		
		Writer writer = null;
        try {
			writer = response.getWriter();
			Template template = config.getTemplate(view);
			template.process(root, writer);		// Merge the data-model and the template
		} catch (Exception e) {
			throw new RenderException(e);
		}
		finally {
			try {writer.close();} catch (IOException e) {e.printStackTrace();}
		}
	}
}