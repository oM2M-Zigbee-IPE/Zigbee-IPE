package org.eclipse.om2m.ipe.zigbee.model.sensor;

import org.eclipse.om2m.commons.exceptions.BadRequestException;

public class SensorModel {
	public SensorModel() {
	}
	private static Sensor DEVICE = null;

	/**
	 * Sets the device state.
	 * @param deviceId - Application ID
	 */
	public static void setDeviceState(final String deviceId,String temperature, String humidity) {
		checkSensorIdValue(deviceId);
		DEVICE.setTemperature(temperature);
		DEVICE.setHumidity(humidity);
	}

	public static void checkSensorIdValue(String lampId){
		if(lampId == null || !DEVICE.getDeviceId().equals(lampId)){
			
			throw new BadRequestException(lampId+" error");
		}
	}

	public static Sensor getDEVICE() {
		return DEVICE;
	}
	public static void setSensor(Sensor sensor) {
		DEVICE = sensor;
	}

}
