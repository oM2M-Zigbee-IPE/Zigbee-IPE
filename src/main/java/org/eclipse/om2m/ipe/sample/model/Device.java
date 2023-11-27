package org.eclipse.om2m.ipe.sample.model;

public class Device {

    /** Default Device location */
    public final static String LOCATION = "Home";
    /** Default Device type */
    public final static String TYPE = "DEVICE";

    /** Device state -> 껐다 키는 용도
     * 그런데 센서인데 이게 필요할까?
     * 일단 넣음
     */
    private boolean state = false;

    /** 온습도계의 온도, 습도 초기값 */
    public final static Float TEMPERATURE = 0.0F;
    public final static Float HUMIDITY = 0.0F;

    /** Device ID */
    private String deviceId;

    public Device(String deviceId, boolean initState) {
        this.deviceId = deviceId;
        this.state = initState;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
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
