package org.eclipse.om2m.ipe.zigbee.model.sensor;



public class Sensor {

    /** Device ID */
    private String deviceId;

    /** Default Device location */
    public final static String LOCATION = "Home";
    /** Default Device type */
    public final static String TYPE = "SENSOR";


    /** 온습도계의 온도, 습도 초기값 */
    public static String temperature = "36.5" ;
    public static String humidity = "60";



    public Sensor(String deviceId) {
        this.deviceId = deviceId;
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
    public static String getTEMPERATURE() {
        return temperature;
    }

    /**
     * 습도 반환
     * @return HUMIDITY
     */
    public static String getHUMIDITY() {
        return humidity;
    }

    public void setTemperature(String temperature) {
        Sensor.temperature = temperature;
    }

    public void setHumidity(String humidity) {
        Sensor.humidity = humidity;
    }

}
