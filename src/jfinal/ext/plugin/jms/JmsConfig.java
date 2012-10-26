package jfinal.ext.plugin.jms;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import jfinal.ext.plugin.config.ConfigKit;
import jfinal.ext.plugin.config.ConfigPlugin;


public class JmsConfig {
	private static  Properties properties;
	
	private static ConfigPlugin configPlugin;
	
	public synchronized static void init(String resoruceLocation) {
		if(properties!=null){
			return;
		}
		InputStream is = JmsPlugin.class.getClassLoader().getResourceAsStream(resoruceLocation);
		if (is == null) {
			throw new RuntimeException("cant find properties in location :"+ resoruceLocation);
		}
		properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			throw new RuntimeException("cant load properties in location :"+ resoruceLocation, e);
		}
	}
		
	public static String getVal(String key) {
		if(configPlugin!=null){
			return ConfigKit.getStr(key);
		}
		Object objVal = properties.get(key);
		return objVal==null?"":objVal+"";
	}
	
	public static Set<String> keys(){
		Set<String> keySet = new HashSet<String>();
		for (Object objVal : properties.keySet()) {
			keySet.add(objVal+"");
		}
		return keySet;
	}

	public static void init(String resoruceLocation, ConfigPlugin configPlugin) {
		JmsConfig.configPlugin = configPlugin;
		init(resoruceLocation);
	}
	
}
