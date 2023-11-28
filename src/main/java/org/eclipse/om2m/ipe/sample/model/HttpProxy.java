package org.eclipse.om2m.ipe.sample.model;

import java.io.IOException;
import java.util.Iterator;
 
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// HttpProxy
// Object에서 get/post 요청을 하면 HttpProxy.connect()를 호출하여 json을 받는다.

public class HttpProxy {
	private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
	
    private String API_KEY = ipeConfig.getConfig("apiKey");
    
    private String ADDRESS = ipeConfig.getConfig("address");
    
    private String my_url = "http://" + ADDRESS + ":8080/api/" + API_KEY;
    
    private String	DeviceName = null;
    
    private String	id = null;
    
    private String	state = null;
    
    private String	method = null;
    
    public	Device(String DeviceName)
    {
    	// .. 
    }
    public	static  JsonNode    connect(String DeviceName, String id, String state, String method)
    {
    	this.id = id;
    	this.state = state;
    	this.method = method;
        this.DeviceName = DeviceName;

    	my_url = my_url + "/" + name;
    	if (this.id != "")
    		my_url = my_url + "/" + id;
    	if (this.state != "")
    		my_url = my_url + "/" + state;
    	try {
            URL url = new URL(my_url);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            this.method = method;
            connection.setRequestMethod(this.method);
            if (this.method.equals("POST"))
            	connection.setDoOutput(true);
            int responseCode = connection.getResponseCode();
            log.debug("responseCode : {}", responseCode);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null)  {
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();

            String body = stringBuffer.toString();
            if (body[0] == '[' && body[body.length()-1] == ']')
            	body = body.substring(1,body.length()-1);
            log.debug("body : {}", body);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(body);
            return (jsonNode);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}