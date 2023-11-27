package org.eclipse.om2m.ipe.sample.controller;

import org.eclipse.om2m.commons.constants.MimeMediaType;
import org.eclipse.om2m.commons.resource.ContentInstance;
import org.eclipse.om2m.core.service.CseService;
import org.eclipse.om2m.ipe.sample.RequestSender;
import org.eclipse.om2m.ipe.sample.constants.SampleConstants;
import org.eclipse.om2m.ipe.sample.model.Model;
import org.eclipse.om2m.ipe.sample.util.ObixUtil;

public class Controller {

    public static CseService CSE;
    protected static String AE_ID;

    public static void setDeviceState(String deviceId, boolean value){
        // Set the value in the "real world" model

        // Send the information to the CSE
        String targetID = SampleConstants.CSE_PREFIX + "/" + deviceId + "/" + SampleConstants.DATA;
        ContentInstance cin = new ContentInstance();
        cin.setContent(ObixUtil.getStateRep(deviceId, value));
        cin.setContentInfo(MimeMediaType.OBIX + ":" + MimeMediaType.ENCOD_PLAIN);
        RequestSender.createContentInstance(targetID, cin);
    }
    public static String getFormatedDeviceState(String deviceId){
        return ObixUtil.getStateRep(deviceId, getDeviceState(deviceId));
    }
    public static boolean getDeviceState(String deviceId){
        return Model.getDeviceValue(deviceId);
    }
    /**
     * 온도, 습도 받아오기
     * @param deviceId
     */
    public static void getDeviceInfo(String deviceId){
        /**
         * 온습도계가 1개임을 가정
         */
        try{
            String json = Model.deviceToJson(deviceId);
        } catch (Exception e) {
            e.getMessage();
        }
    }
    public static void setCse(CseService cse){
        CSE = cse;
    }



}
