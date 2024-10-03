package Utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverUtil {

    public static AndroidDriver driver;
    public static AndroidDriver getAndroidDriver() throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();

//        cap.setCapability("deviceName", "POCO X3 NFC");
//        cap.setCapability("platformVersion", "11");

        //cap.setCapability("udid", "536e636d");
        cap.setCapability("deviceName", "Redmi Note 9 Pro");
        cap.setCapability("platformVersion", "10");
        cap.setCapability("automationName", "UiAutomator2");
        cap.setCapability("platformName", "Android");
        cap.setCapability("appPackage", "com.pvs.project.galileo");      // prod
//        cap.setCapability("appPackage", "com.pvs.project.galileo.dev");  //dev
        cap.setCapability("appActivity", "com.pvs.project.galileo.MainActivity");
        // command for no reinstall every test run
        cap.setCapability("noReset", "true");
        cap.setCapability("fullReset", "false");


//        cap.setCapability("deviceName", "Test Phone");
//        cap.setCapability("platformVersion", "10");
//        cap.setCapability("automationName", "UiAutomator2");
//        cap.setCapability("platformName", "Android");
//        cap.setCapability("path", "C:\\Users\\User\\Documents\\Dev\\GalileoMobile\\src\\apk\\ApiDemos-debug.apk");


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
