package util;

/**
 * Created by grago on 16/06/15.
 */
public enum Device {

    a_device("device_name", true);

    public final String name;
    public final boolean isPhone;

    private Device(String name, boolean isPhone){
        this.name = name;
        this.isPhone = isPhone;
    }


}
