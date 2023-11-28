
package org.eclipse.om2m.ipe.sample.model;

public	interface IDevice {
    public String service(String deviceName, String id, String state, String method);
}
