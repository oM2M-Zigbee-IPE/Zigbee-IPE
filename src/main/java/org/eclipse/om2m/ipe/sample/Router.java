package org.eclipse.om2m.ipe.sample;

import org.eclipse.om2m.commons.constants.MimeMediaType;
import org.eclipse.om2m.commons.constants.ResponseStatusCode;
import org.eclipse.om2m.commons.exceptions.BadRequestException;
import org.eclipse.om2m.commons.resource.RequestPrimitive;
import org.eclipse.om2m.commons.resource.ResponsePrimitive;
import org.eclipse.om2m.interworking.service.InterworkingService;
import org.eclipse.om2m.ipe.sample.constants.Operations;
import org.eclipse.om2m.ipe.sample.constants.SampleConstants;
import org.eclipse.om2m.ipe.sample.controller.Controller;

public class Router implements InterworkingService {


    @Override
    public ResponsePrimitive doExecute(RequestPrimitive request) {
        ResponsePrimitive response = new ResponsePrimitive(request);
        if(request.getQueryStrings().containsKey("op")){
            String operation = request.getQueryStrings().get("op").get(0);
            Operations op = Operations.getOperationFromString(operation);
            String deviceId= null;
            if(request.getQueryStrings().containsKey("deviceId")){
                deviceId = request.getQueryStrings().get("deviceId").get(0);
            }
            else if(request.getQueryStrings().containsKey("lampid")){
                deviceId = request.getQueryStrings().get("lampid").get(0);
            }
            try{
                switch(op){
                    case SET_ON:
                        Controller.setLampState(deviceId, true);
                        response.setResponseStatusCode(ResponseStatusCode.OK);
                        break;
                    case SET_OFF:
                        Controller.setLampState(deviceId, false);
                        response.setResponseStatusCode(ResponseStatusCode.OK);
                        break;
                    case GET_STATE_DIRECT:
                        String content1 = Controller.getFormatedLampState(deviceId);
                        response.setContent(content1);
                        request.setReturnContentType(MimeMediaType.OBIX);
                        response.setResponseStatusCode(ResponseStatusCode.OK);
                        break;
                    case GET_SENSOR_STATE:
                        Controller.setSensorState(deviceId);
                        response.setResponseStatusCode(ResponseStatusCode.OK);
                        break;
                    case GET_RECENT_STATE:
                        String content2 = Controller.getFormatedDeviceState(deviceId);
                        response.setContent(content2);
                        request.setReturnContentType(MimeMediaType.OBIX);
                        response.setResponseStatusCode(ResponseStatusCode.OK);
                        break;
                    default:
                        throw new BadRequestException();
                }
            }
            catch (Exception exception){
                exception.printStackTrace();
            }
        }
        if(response.getResponseStatusCode() == null){
            response.setResponseStatusCode(ResponseStatusCode.BAD_REQUEST);
        }
        return response;
    }
    @Override
    public String getAPOCPath() {

        /** Point of Access 로 앞으로 우리가 만들 AE의 위치를 뜻함. --> { ip:port } */

        return SampleConstants.POA;
    }
}
