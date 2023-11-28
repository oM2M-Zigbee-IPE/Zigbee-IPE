package org.eclipse.om2m.ipe.sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.eclipse.om2m.commons.constants.Constants;
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
            String lampid= null;
            if(request.getQueryStrings().containsKey("lampid")){
                lampid = request.getQueryStrings().get("lampid").get(0);
            }
            //LOGGER.info("Received request in Sample IPE: op=" + operation + " ; lampid=" + lampid);
            switch(op){
                case SET_ON:
                    Controller.setLampState(lampid, true);
                    response.setResponseStatusCode(ResponseStatusCode.OK);
                    break;
                case SET_OFF:
                    Controller.setLampState(lampid, false);
                    response.setResponseStatusCode(ResponseStatusCode.OK);
                    break;
                case GET_STATE_DIRECT:
                    String content = Controller.getFormatedLampState(lampid);
                    response.setContent(content);
                    request.setReturnContentType(MimeMediaType.OBIX);
                    response.setResponseStatusCode(ResponseStatusCode.OK);
                    break;
                default:
                    throw new BadRequestException();
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
