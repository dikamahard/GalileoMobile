package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TermsConditionPage {

    private final AndroidDriver driver;

    public By tvTitle =By.xpath("//android.view.View[@text='Syarat dan Ketentuan Galileo Loyalty Program']");

    public TermsConditionPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getTvTitle() {
        return driver.findElement(this.tvTitle);
    }


}
