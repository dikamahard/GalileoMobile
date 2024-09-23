import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverUtil {

    public static AndroidDriver driver;
    public static AndroidDriver getAndroidDriver() throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("deviceName", "Redmi Note 9 Pro");
        //cap.setCapability("udid", "536e636d");
        cap.setCapability("platformVersion", "10");
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("platformName", "Android");
        cap.setCapability("appPackage", "com.pvs.project.galileo");
        cap.setCapability("appActivity", "com.pvs.project.galileo.MainActivity");
        // command for no reinstall every test run
        cap.setCapability("noReset", "true");
        cap.setCapability("fullReset", "false");


        URL url = new URL("http://127.0.0.1:4723/");

        driver = new AndroidDriver(url, cap);

        return driver;
    }

    public static void quitDriver() {
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
