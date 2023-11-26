package org.eclipse.om2m.ipe.sample.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class DeviceModel {
    @JsonProperty("schema")
    private String schema;
    @JsonProperty("manufacturerName")
    private String manufacturerName;
    @JsonProperty("modelId")
    private String modelId;
    @JsonProperty("product")
    private String product;
    @JsonProperty("sleeper")
    private boolean sleeper;
    @JsonProperty("status")
    private String status;
    @JsonProperty("subdevices")
    private List<SubDevices> subdevices;
}