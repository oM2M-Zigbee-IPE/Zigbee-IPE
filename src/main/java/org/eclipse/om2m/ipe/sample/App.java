package org.eclipse.om2m.ipe.sample;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.om2m.ipe.sample.model.Device;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class App
{
    public static void main( String[] args )
    {
        try {
            URL url = new URL("http://localhost:8080/admin/rentals");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            // connection.setDoOutput(true); --> 데이터 전송 POST일 경우 필요함
            int responseCode = connection.getResponseCode();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer stringBuffer = new StringBuffer();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null)  {
                stringBuffer.append(inputLine);
            }
            bufferedReader.close();

            String response = stringBuffer.toString();
            response = response.substring(1,response.length()-1);
            //System.out.println(response);

            ObjectMapper mapper = new ObjectMapper();
            Device device = mapper.readValue(response,Device.class);


            //System.out.println(rentals.getRentalId());
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}