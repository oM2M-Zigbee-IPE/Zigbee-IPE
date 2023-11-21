package org.eclipse.om2m.ipe.sample;

import org.eclipse.om2m.commons.constants.Constants;
import org.eclipse.om2m.commons.resource.RequestPrimitive;
import org.eclipse.om2m.commons.resource.ResponsePrimitive;
import org.eclipse.om2m.interworking.service.InterworkingService;
import org.eclipse.om2m.ipe.sample.constants.SampleConstants;

public class Router implements InterworkingService {

    @Override
    public ResponsePrimitive doExecute(RequestPrimitive request) {
        ResponsePrimitive response = new ResponsePrimitive(request);

        /** oM2M의 요청을 parsing 해서 IPE Controller에게 전달한다. */


        return response;
    }

    @Override
    public String getAPOCPath() {

        /** Point of Access 로 앞으로 우리가 만들 AE의 위치를 뜻함. --> { ip:port } */

        return SampleConstants.POA;
    }
}
