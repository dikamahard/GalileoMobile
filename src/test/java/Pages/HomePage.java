package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage {


    private final AndroidDriver driver;

    public By btnProfile = By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]");
    public By btnAccountSetting = By.xpath("//android.widget.ImageView[@content-desc='Account Settings']");
    public By btnHome = By.xpath("//android.widget.ImageView[@content-desc='Home']");
    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }


    public WebElement getBtnProfile() {
        return driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]"));
    }
    public WebElement getBtnAccountSetting() {
        return driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Account Settings']"));
    }

    public WebElement getBtnHome() {
        return driver.findElement(btnHome);
    }

//    public WebElement getOnBoardingBanner() {
//        return driver.findElement(By.xpath(""));
//    }


}
