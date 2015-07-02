package screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import util.Device;

/**
 * Created by grago on 02/07/15.
 */
public class ExampleScreen extends AbstractScreen {

    @AndroidFindBy(id = "com.example.app:id/element_id")
    @iOSFindBy(name = "element_name")
    private MobileElement element;

    public ExampleScreen(AppiumDriver driver, Device device){
        super(driver, device);
    }

    public void actionOnScreen() {
        // do something using elements on screen
    }

    public boolean conditionOnScreen() {
        // verify a condition related to this screen e.g.: isLoggedIn()
        return true;
    }

}
