package org.eclipse.om2m.ipe.sample.model.sensor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.om2m.commons.exceptions.BadRequestException;
import org.eclipse.om2m.ipe.sample.model.lamp.SpringLamp;

import java.util.HashMap;
import java.util.Map;

public class SensorModel {
	public SensorModel() {
	}
	private static Sensor DEVICE = null;

	/**
	 * Sets the device state.
	 * @param deviceId - Application ID
	 */
	public static void setDeviceState(final String deviceId) {
		checkSensorIdValue(deviceId);
		DEVICE.setTemperature();
		DEVICE.setHumidity();
	}

	public static void checkSensorIdValue(String lampId){
		if(lampId == null || !DEVICE.getDeviceId().equals(lampId)){
			throw new BadRequestException("Unknow lamp id");
		}
	}

	public static Sensor getDEVICE() {
		return DEVICE;
	}
	public static void setSensor(Sensor sensor) {
		DEVICE = sensor;
	}

}
