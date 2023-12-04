package org.eclipse.om2m.ipe.zigbee.model.proxy;

import java.util.HashMap;
import java.util.Map;

public class ipeConfig {
	private	static Map<String, String> ipeConfigs = new HashMap<String, String>();
	static {
		ipeConfigs.put("ipe", "172.19.16.43");
		ipeConfigs.put("apiKey", "D9DF3B2018");
		ipeConfigs.put("sensingInterval", "1000");
		ipeConfigs.put("address", "172.19.16.43");
		ipeConfigs.put("port", "8080");
	}
	public static String getConfig(String key) {
		return ipeConfigs.get(key);
	}
}

//http://172.19.16.43/api/D9DF3B2018/sensors