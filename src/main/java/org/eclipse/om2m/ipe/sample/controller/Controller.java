package org.eclipse.om2m.ipe.sample.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.om2m.commons.constants.MimeMediaType;
import org.eclipse.om2m.commons.resource.ContentInstance;
import org.eclipse.om2m.core.service.CseService;
import org.eclipse.om2m.ipe.sample.RequestSender;
import org.eclipse.om2m.ipe.sample.constants.SampleConstants;
import org.eclipse.om2m.ipe.sample.model.lamp.LampModel;
import org.eclipse.om2m.ipe.sample.model.sensor.SensorModel;
import org.eclipse.om2m.ipe.sample.model.proxy.HttpProxy;
import org.eclipse.om2m.ipe.sample.util.ObixUtil;

public class Controller {
    public static CseService CSE;
    protected static String AE_ID;

    

    /** contentInstance 만들어서 oM2M에 전달 */
    public static void setSensorState(String deviceId)throws Exception{
        HttpProxy proxy = new HttpProxy();
        // 1. 데이터 받아오기
        JsonNode response = proxy.connect("sensors",null,null,"GET",null);
        Double rowTemp = response.get("2").get("state").get("temperature").doubleValue()/ 100;
        Double rowHum = response.get("3").get("state").get("humidity").doubleValue() / 100;

        // 2. SensorMdoel에 저장하기
        SensorModel.setDeviceState(deviceId,String.valueOf(rowTemp),String.valueOf(rowHum));

        // 3. ContentInstance 만들고 RequestSender로 IN-CSE에 요청보내기
        String targetID = SampleConstants.CSE_PREFIX + "/" + deviceId + "/" + SampleConstants.DATA;
        ContentInstance cin = new ContentInstance();
        cin.setContent(ObixUtil.getSensorState(deviceId)); // 받아온 정보 이용해야 함
        cin.setContentInfo(MimeMediaType.OBIX + ":" + MimeMediaType.ENCOD_PLAIN);
        RequestSender.createContentInstance(targetID, cin);
    }

    public static String getFormatedDeviceState(String deviceId){
        return ObixUtil.getSensorState(deviceId);
    }




    /** 아래가 Lamp 관련 코드 */

    /** Spring Lamp 불 끄기 */
    public static void setLampState(String lampId, boolean value){
        // Set the value in the "real world" model
        LampModel.setLampState(lampId, value);
        // Send the information to the CSE
        String targetID = SampleConstants.CSE_PREFIX + "/" + lampId + "/" + SampleConstants.DATA;
        ContentInstance cin = new ContentInstance();
        cin.setContent(ObixUtil.getStateRep(lampId, value));
        cin.setContentInfo(MimeMediaType.OBIX + ":" + MimeMediaType.ENCOD_PLAIN);
        RequestSender.createContentInstance(targetID, cin);
    }

    /** Spring 데이터 받아오기 */
    public static String getFormatedLampState(String lampId) throws  Exception{
        HttpProxy proxy = new HttpProxy();

        JsonNode node = proxy.connect("lamp","lamp1","state","GET",null);
        return ObixUtil.getStateRep(lampId, node.get("status").booleanValue());
    }

    private static boolean getLampState(String lampId) {
        return LampModel.getLampValue(lampId);
    }


    public static void setCse(CseService cse){
        CSE = cse;
    }

}
