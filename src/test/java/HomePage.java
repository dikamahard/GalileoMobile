import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;

public class HomePage {


    private final AndroidDriver driver;

    By btnProfile = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]");
    By btnAccountSetting = By.xpath("//android.widget.ImageView[@content-desc='Account Settings']");
    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement btnProfile() {
        return driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]"));
    }
    public WebElement btnAccountSetting() {
        return driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Account Settings']"));
    }


}
