package org.eclipse.om2m.ipe.sample.model;

public class Device {

    /** Default Device location */
    public final static String LOCATION = "Home";
    /** Default Device type */
    public final static String TYPE = "DEVICE";
    /** 온습도계의 온도, 습도 초기값 */
    public final static Float TEMPERATURE = 0.0F;
    public final static Float HUMIDITY = 0.0F;

    /** Device ID */
    private String deviceId;

    public Device(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public static Float getTEMPERATURE() {
        return TEMPERATURE;
    }

    public static Float getHUMIDITY() {
        return HUMIDITY;
    }
}
