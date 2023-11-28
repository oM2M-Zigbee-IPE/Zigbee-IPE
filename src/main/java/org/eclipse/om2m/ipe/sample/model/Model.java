package org.eclipse.om2m.ipe.sample.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.om2m.commons.exceptions.BadRequestException;

import java.util.HashMap;
import java.util.Map;

public class Model {
	public Model() {
	}
	private static Map<String, Device> DEVICE = new HashMap<String, Device>();

	/**
	 * Sets the device state.
	 * @param deviceId - Application ID
	 * @param value - measured state
	 */
	public static void setDeviceState(final String deviceId, boolean value) {
		checkDeviceIdValue(deviceId);
		DEVICE.get(deviceId).setState(value);
	}

	/**
	 * Gets the direct current Device state
	 * @param deviceId
	 * @return the direct current Device state
	 */

	/**
	 * 현재 장치의 state 반환
	 * @param deviceId
	 * @return the direct current device state
	 */
	public static boolean getDeviceValue(String deviceId) {
		try{
			checkDeviceIdValue(deviceId);
		}catch(Exception e){
			e.getMessage();
		}
		return DEVICE.get(deviceId).getState();
	}
	public static Device getDevice(String deviceId){
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
	public static void setModel(Map<String, Device> deviceMap) {
		DEVICE = deviceMap;
	}

	/**
	 * device 객체 안의 모든 정보들을 json으로 내보냄
	 * @param deviceId
	 * @return json
	 * @throws Exception
	 */
	public static String deviceToJson(String deviceId) throws Exception {
		Map<String, Device> map = new HashMap<String, Device>();
		map.put("device", DEVICE.get(deviceId));
		String json = new ObjectMapper().writeValueAsString(map);
		System.out.println("json = " + json);
		return json;
	}
}
