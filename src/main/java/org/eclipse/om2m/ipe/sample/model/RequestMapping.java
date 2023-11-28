package org.eclipse.om2m.ipe.sample.model;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static Map<String, IDevice> devices = new HashMap<String, IDevice>();

    static {
        devices.put("LAMP", new Lamp());
        // append Object...
    }

    public static IDevice getController(String requestUrl) {
        return devices.get(requestUrl);
    }
}