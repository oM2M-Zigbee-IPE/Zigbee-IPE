package org.eclipse.om2m.ipe.sample.model.proxy;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


// HttpProxy
// Object에서 get/post 요청을 하면 HttpProxy.connect()를 호출하여 json을 받는다.

public class HttpProxy {


    private String API_KEY = ipeConfig.getConfig("apiKey");
    
    private String ADDRESS = ipeConfig.getConfig("address");
    
    private String my_url = "http://" + ADDRESS + ":8080/api/" + API_KEY;
    
    private String	DeviceName = null;
    
    private String	id = null;
    
    private String	state = null;
    
    private String	method = null;

    public	JsonNode connect(String DeviceName, String id, String state, String method, String json) throws Exception{
        this.id = id;
        this.state = state;
        this.method = method;
        this.DeviceName = DeviceName;

        my_url = my_url + "/" + DeviceName;
        if (this.id != "")
            my_url = my_url + "/" + id;
        if (this.state != "")
            my_url = my_url + "/" + state;

        URL url = new URL(my_url);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        this.method = method;
        connection.setRequestMethod(this.method);
        if (this.method.equals("POST")) {
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            // POST 데이터 설정 (JSON 형태의 데이터 예시)
            // String postData = "{\"key1\":\"value1\",\"key2\":\"value2\"}";
            String postData = json;
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(postData.getBytes("UTF-8"));
            }
        }
        int responseCode = connection.getResponseCode();


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuffer.append(inputLine);
        }
        bufferedReader.close();

        String body = stringBuffer.toString();
        if (body.charAt(0) == '[' && body.charAt(body.length() - 1) == ']')
            body = body.substring(1, body.length() - 1);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(body);
        return (jsonNode);

    }
}