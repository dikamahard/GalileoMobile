package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PrivacyPolicyPage {

    private final AndroidDriver driver;

    public By tvTitle = By.xpath("//android.view.View[@text='Kebijakan Privasi Galileo Loyalty Program']");
    public PrivacyPolicyPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getTvTitle() {
        return driver.findElement(this.tvTitle);
    }
}
