package org.eclipse.om2m.ipe.sample.model.Lamp;

import org.eclipse.om2m.commons.exceptions.BadRequestException;

public class LampModel {

    private static SpringLamp LAMPS = null;

    public LampModel() {
    }

    public static void setLampState(final String lampId, boolean value) {
        checkLampIdValue(lampId);
        LAMPS.setState(value);

    }

    /** om2m 요청으로 온 lampId가 null이거나 해당 lampId가 존재하지 않을 경우 예외처리 */
    public static void checkLampIdValue(String lampId){
        if(lampId == null || !LAMPS.getLampId().equals(lampId)){
            throw new BadRequestException("Unknow lamp id");
        }
    }

    public static void setModel(SpringLamp lamp) {
        LAMPS = lamp;
    }

    public static boolean getLampValue(String lampId) {
        checkLampIdValue(lampId);
        return LAMPS.getState();
    }


}
