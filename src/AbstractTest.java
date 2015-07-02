import io.appium.java_client.android.AndroidDriver;
import org.junit.Rule;
import org.junit.rules.TestName;
import util.Device;

import static java.lang.System.out;
import static util.AppiumDriverBuilder.forAndroid;
import static util.Device.a_device;

/**
 * Created by grago on 15/06/15.
 */
public abstract class AbstractTest {

    @Rule
    public TestName testName = new TestName();

    private AndroidDriver driver;
    private final Device device;

    protected App app;

    public AbstractTest() {
        this.device = a_device;
    }

    public AbstractTest(Device device) {
        this.device = device;
    }

    public void connect() {

        out.println("Device: " + device.name);
        driver = forAndroid().againstLocalhost().build();

        app = new App(device, driver);

    }

    /* Something you will be doing in your tests. */
    public void action() {

        if (app.exampleScreen().conditionOnScreen() == false) {
            app.exampleScreen().actionOnScreen();
        }

    }

    public void disconnect() {
        driver.quit();
    }

}
