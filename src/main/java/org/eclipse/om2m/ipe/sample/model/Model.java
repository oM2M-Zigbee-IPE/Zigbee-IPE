package org.eclipse.om2m.ipe.sample.model;

import org.eclipse.om2m.commons.exceptions.BadRequestException;

import java.util.HashMap;
import java.util.Map;

public class Model {
	public Model() {
	}
	private static Map<String, Device> DEVICE = new HashMap<String, Device>();


	/**
	 * 현재 장치의 state 반환
	 * @param deviceId
	 * @return the direct current device state
	 */
	public static Device getDeviceValue(String deviceId) {
		try{
			checkDeviceIdValue(deviceId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return DEVICE.get(deviceId);
	}


	/**
	 * 주어진 장치의 ID 검사
	 * @param deviceId
	 */
	private static void checkDeviceIdValue(String deviceId) {
		if(deviceId == null || !DEVICE.containsKey(deviceId)){
			throw new BadRequestException("Unknown device id");
		}
	}
	public static void setModel(Map<String, Device> deviceMapTmp) {
		DEVICE = deviceMapTmp;
	}

}
