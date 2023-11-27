package org.eclipse.om2m.ipe.sample.model;

import java.util.Map;

public class LampModel {

    private static SpringLamp LAMPS = null;

    public LampModel() {
    }

    public static void setModel(SpringLamp lamp) {
        LAMPS = lamp;
    }

}
