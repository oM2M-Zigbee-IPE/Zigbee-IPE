package org.eclipse.om2m.ipe.sample.model.proxy;

import java.util.HashMap;
import java.util.Map;

public class ipeConfig {
	private	static Map<String, String> ipeConfigs = new HashMap<String, String>();
	static {
		ipeConfigs.put("ipe", "192.168.0.122");
		ipeConfigs.put("apiKey", "9BBE38D704");
		ipeConfigs.put("sensingInterval", "1000");
		ipeConfigs.put("address", "127.0.0.1");
		ipeConfigs.put("port", "8080");
	}
	public static String getConfig(String key) {
		return ipeConfigs.get(key);
	}
}