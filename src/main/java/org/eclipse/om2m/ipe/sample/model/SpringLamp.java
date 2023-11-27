package org.eclipse.om2m.ipe.sample.model;

import jdk.nashorn.internal.parser.JSONParser;

public class SpringLamp {

    public final static String LOCATION = "SpringBoot";

    public final static String TOGGLE = "toggle";

    public final static String TYPE = "LAMP";

    private boolean state = false;

    private String lampId;

    public SpringLamp(String lampId, boolean initState){
        this.lampId = lampId;
        this.state = initState;
    }

    public boolean isState() {
        return state;
    }

    public String getLampId() {
        return lampId;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setLampId(String lampId) {
        this.lampId = lampId;
    }
}
