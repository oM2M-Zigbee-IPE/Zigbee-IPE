package org.eclipse.om2m.ipe.sample.model;

public class DeviceDto {
    private Float temp;
    private Float humid;

    public DeviceDto(Float temp, Float humid) {
        this.temp = temp;
        this.humid = humid;
    }
}
