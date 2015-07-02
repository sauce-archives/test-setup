package screen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import util.Device;

/**
 * Created by grago on 15/06/15.
 */
public abstract class AbstractScreen {

    protected final AppiumDriver driver;
    protected final Device device;

    public AbstractScreen(AppiumDriver driver, Device device){
        this.device = device;
        this.driver = driver;

        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

}
