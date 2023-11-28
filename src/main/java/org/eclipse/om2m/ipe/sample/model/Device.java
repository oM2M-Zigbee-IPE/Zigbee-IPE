package org.eclipse.om2m.ipe.sample.model;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Device {

    /** Device ID */
    private String deviceId;

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

    public Device(String deviceId, boolean initState) {
        this.deviceId = deviceId;
        this.state = initState;
    }

    /**
     * 전원 상태 확인
     * @return state
     */
    public boolean getState() {
        return state;
    }

    /**
     * 전원 상태 설정
     * @param state
     */
    public void setState(boolean state) {
        this.state = state;
    }

    /**
     * 장치 ID 반환
     * @return deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * 장치 ID 설정
     * @param deviceId
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 온도 반환
     * @return TEMPERATURE
     */
    public static Float getTEMPERATURE() {
        return TEMPERATURE;
    }

    /**
     * 습도 반환
     * @return HUMIDITY
     */
    public static Float getHUMIDITY() {
        return HUMIDITY;
    }

}
