package indi.chenk.monitor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;

public class PropertyUtil {

	private static Map<String,String> maps = new HashMap<String,String>();
	
	public static void addProp(String key,String value) {
		if(Utils.isNull(key)) {
			return;
		}
		maps.put(key, value);
	}
	
	public static void addProp(String path) {
		try {
			File f = new File(path);
			Properties pros = new Properties();
			pros.load(new FileInputStream(f));
			for(Object key:pros.keySet()) {
				String  propKey = key + "";
				maps.put(propKey, pros.getProperty(propKey)+"");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getProp(String key) {
		return maps.get(key);
	}
	
	
}
