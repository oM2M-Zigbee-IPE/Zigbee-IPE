package org.eclipse.om2m.ipe.sample.model;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

public class ADevice implements IDevice {
    @Override
    public String service(String deviceName, String id, String state, String method)
    {
        JsonNode jsonNode = HttpProxy.connect(String deviceName, String id, String state, String method);
        String  value;

        value = "";
        if (method.equals("POST"))
            doPost();
        else if (method.equals("GET"))
            value = doGet();
        return (value);
    }
    public abstract String  doGet();
    public abstract void    doPost();
}
