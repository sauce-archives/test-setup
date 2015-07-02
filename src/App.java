import io.appium.java_client.AppiumDriver;
import screen.ExampleScreen;
import util.Device;

/**
 * Created by grago on 16/06/15.
 */
public class App {

    private final Device device;
    private final AppiumDriver driver;

    public App(Device device, AppiumDriver driver){
        this.device = device;
        this.driver = driver;
    }

    public ExampleScreen exampleScreen() { return new ExampleScreen(driver, device); }

}
