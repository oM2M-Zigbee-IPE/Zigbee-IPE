package org.eclipse.om2m.ipe.sample.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class SubDevices {
    @JsonProperty("type")
    private String type;
    @JsonProperty("restApi")
    private String restApi;
    @JsonProperty("uuid")
    private List<String> uuid;
    @JsonProperty("items")
    private List<Item> items;
}
