package util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

/**
 * Created by grago on 02/07/15.
 */
public abstract class AppiumDriverBuilder<SELF, DRIVER> {

    public static AndroidDriverBuilder forAndroid() { return new AndroidDriverBuilder(); }

    public static class AndroidDriverBuilder extends AppiumDriverBuilder<AndroidDriverBuilder, AndroidDriver>{

        private Optional<String> bundleId = Optional.empty();
        private Optional<String> udid = Optional.empty();

        public AndroidDriverBuilder app(String bundleId){

            this.bundleId = Optional.of(bundleId);
            return this;

        }

        public AndroidDriver build() {

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformName", "Android");

            return new AndroidDriver(endpoint, capabilities);

        }

    }

    public static IOSDriverBuilder forIOS(){
        return new IOSDriverBuilder();
    }

    public static class IOSDriverBuilder extends AppiumDriverBuilder<IOSDriverBuilder, IOSDriver>{

        private Optional<String> bundleId = Optional.empty();
        private Optional<String> udid = Optional.empty();

        public IOSDriverBuilder app(String bundleId){
            this.bundleId = Optional.of(bundleId);

            return this;
        }

        public IOSDriverBuilder onDevice(String udid){
            this.udid = Optional.of(udid);

            return this;

        }

        public IOSDriver build(){
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "iOS");
//            capabilities.setCapability("browserName", "");
//            capabilities.setCapability("appium-version", "1.0");
            capabilities.setCapability("platformName", "iOS");
//            capabilities.setCapability("waitForAppScript", "$.delay(3000); UIATarget.localTarget().frontMostApp().alert(); $.acceptAlert(); true");
//            capabilities.setCapability("autoAcceptAlerts", "true");
//            capabilities.setCapability("platformVersion", "8.1");
//            capabilities.setCapability("deviceName", "ABC");
            // capabilities.setCapability("nativeWebTap", "true");
//            capabilities.setCapability("app", "/Users/aluedeke/Downloads/doscout_resigned.ipa");

            if(bundleId.isPresent()){
                capabilities.setCapability("bundleId", bundleId.get());
            }

            if(testObjectConfig.isPresent()){
                capabilities.setCapability("testobject_api_key", testObjectConfig.get().apiKey);
                capabilities.setCapability("testobject_app_id", testObjectConfig.get().appId);
                capabilities.setCapability("testobject_device", testObjectConfig.get().deviceId);
                if(testObjectConfig.get().suiteName.isPresent()){
                    capabilities.setCapability("testobject_suite_name", testObjectConfig.get().suiteName.get());
                }
                if(testObjectConfig.get().testName.isPresent()){
                    capabilities.setCapability("testobject_test_name", testObjectConfig.get().testName.get());
                }
            }

            return new IOSDriver(endpoint, capabilities);
        }
    }

    protected URL endpoint;
    protected Optional<TestObjectConfig> testObjectConfig = Optional.empty();


    public SELF againstLocalhost(){
        try {
            this.endpoint = new URL("http://0.0.0.0:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return (SELF) this;
    }

    public SELF againstHost(String host, int port){
        try {
            this.endpoint = new URL("http://" + host  +  ":" + port + "/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return (SELF) this;
    }

    private static class TestObjectConfig {

        private final String apiKey;
        private final int appId;
        private final String deviceId;
        private final Optional<String> suiteName;
        private final Optional<String> testName;

        public TestObjectConfig(String apiKey, int appId, String deviceId) {
            this.apiKey = apiKey;
            this.appId = appId;
            this.deviceId = deviceId;
            this.suiteName = Optional.empty();
            this.testName = Optional.empty();
        }

        public TestObjectConfig(String apiKey, int appId, String deviceId, String suiteName, String testName) {
            this.apiKey = apiKey;
            this.appId = appId;
            this.deviceId = deviceId;
            this.suiteName = Optional.of(suiteName);
            this.testName = Optional.of(testName);
        }
    }

    public SELF againstTestobject(String apiKey, int appId, String deviceId){
        try {
            this.testObjectConfig = Optional.of(new TestObjectConfig(apiKey, appId, deviceId));
            this.endpoint = new URL("https://app.testobject.com:443/api/appium/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return (SELF) this;
    }

    public SELF againstTestobject(String apiKey, int appId, String deviceId, String suiteName, String testName){
        try {
            this.testObjectConfig = Optional.of(new TestObjectConfig(apiKey, appId, deviceId, suiteName, testName));
            this.endpoint = new URL("https://app.testobject.com:443/api/appium/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        return (SELF) this;
    }

    public abstract DRIVER build();

}