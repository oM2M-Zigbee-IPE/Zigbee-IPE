/*******************************************************************************
 * Copyright (c) 2013-2020 LAAS-CNRS (www.laas.fr)
 * 7 Colonel Roche 31077 Toulouse - France
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Initial Contributors:
 *     Thierry Monteil : Project manager, technical co-manager
 *     Mahdi Ben Alaya : Technical co-manager
 *     Samir Medjiah : Technical co-manager
 *     Khalil Drira : Strategy expert
 *     Guillaume Garzone : Developer
 *     François Aïssaoui : Developer
 *
 * New contributors :
 *******************************************************************************/
package org.eclipse.om2m.ipe.zigbee.util;

import org.eclipse.om2m.commons.constants.Constants;
import org.eclipse.om2m.commons.obix.Bool;
import org.eclipse.om2m.commons.obix.Contract;
import org.eclipse.om2m.commons.obix.Obj;
import org.eclipse.om2m.commons.obix.Op;
import org.eclipse.om2m.commons.obix.Str;
import org.eclipse.om2m.commons.obix.Uri;
import org.eclipse.om2m.commons.obix.io.ObixEncoder;
import org.eclipse.om2m.ipe.zigbee.constants.Operations;
import org.eclipse.om2m.ipe.zigbee.model.sensor.Sensor;
import org.eclipse.om2m.ipe.zigbee.model.lamp.SpringLamp;

public class ObixUtil {

	public static String getLampDescriptorRep(String cseId, String appId, String stateCont) {
		String prefix = cseId+"/"+ Constants.CSE_NAME + "/" + appId;
		// oBIX
		Obj obj = new Obj();
		obj.add(new Str("type", SpringLamp.TYPE));
		obj.add(new Str("location", SpringLamp.LOCATION));
		obj.add(new Str("appId",appId));


		// 현재 Lamp 상태 확인 URI 등록
		Op opStateDirect = new Op();
		opStateDirect.setName("getLamp(Direct)");
		opStateDirect.setHref(new Uri(prefix + "?op="+ Operations.GET_STATE_DIRECT+"&lampid=" + appId));
		opStateDirect.setIs(new Contract("execute"));
		opStateDirect.setIn(new Contract("obix:Nil"));
		opStateDirect.setOut(new Contract("obix:Nil"));
		obj.add(opStateDirect);

		// OP SwitchON
		Op opON = new Op();
		opON.setName("switchON");
		opON.setHref(new Uri(prefix + "?op="+ Operations.SET_ON +"&lampid=" + appId));
		opON.setIs(new Contract("execute"));
		opON.setIn(new Contract("obix:Nil"));
		opON.setOut(new Contract("obix:Nil"));
		obj.add(opON);

		// OP SwitchOFF
		Op opOFF = new Op();
		opOFF.setName("switchOFF");
		opOFF.setHref(new Uri(prefix  + "?op=" + Operations.SET_OFF + "&lampid=" + appId));
		opOFF.setIs(new Contract("execute"));
		opOFF.setIn(new Contract("obix:Nil"));
		opOFF.setOut(new Contract("obix:Nil"));
		obj.add(opOFF);

		return ObixEncoder.toString(obj);
	}

	public static String getSensorDescriptorRep(String cseId, String appId, String stateCont) {
		String prefix = cseId+"/"+ Constants.CSE_NAME + "/" + appId;
		// oBIX
		Obj obj = new Obj();
		obj.add(new Str("type", SpringLamp.TYPE));
		obj.add(new Str("location", SpringLamp.LOCATION));
		obj.add(new Str("appId",appId));

		// 가장 최근 센서 데이터 가져오는 uri 생성
		Op opStateDirect = new Op();
		opStateDirect.setName("getSensor(local)");
		opStateDirect.setHref(new Uri(prefix + "?op="+ Operations.GET_RECENT_STATE+"&deviceId=" + appId));
		opStateDirect.setIs(new Contract("execute"));
		opStateDirect.setIn(new Contract("obix:Nil"));
		opStateDirect.setOut(new Contract("obix:Nil"));
		obj.add(opStateDirect);

		// Conbee에서 데이터 받아오는 uri 생성
		Op opON = new Op();
		opON.setName("getSensor(remote)");
		opON.setHref(new Uri(prefix + "?op="+ Operations.GET_SENSOR_STATE +"&deviceId=" + appId));
		opON.setIs(new Contract("execute"));
		opON.setIn(new Contract("obix:Nil"));
		opON.setOut(new Contract("obix:Nil"));
		obj.add(opON);


		return ObixEncoder.toString(obj);
	}


	public static String getStateRep(String Id, boolean value) {
		// oBIX
		Obj obj = new Obj();
		obj.add(new Str("type", Sensor.TYPE));
		obj.add(new Str("location", Sensor.LOCATION));
		obj.add(new Str("deviceId",Id));
		obj.add(new Bool("state",value));
		return ObixEncoder.toString(obj);
	}

	/** oM2M에게 최근 센서 정보 전달 */
	public static String getSensorState(String Id) {
		// oBIX
		Obj obj = new Obj();
		obj.add(new Str("type", Sensor.TYPE));
		obj.add(new Str("location", Sensor.LOCATION));
		obj.add(new Str("deviceId",Id));
		obj.add(new Str("temperature", Sensor.getTEMPERATURE()));
		obj.add(new Str("humidity", Sensor.getHUMIDITY()));
		return ObixEncoder.toString(obj);
	}
}
