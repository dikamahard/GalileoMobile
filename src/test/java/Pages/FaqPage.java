package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FaqPage {

    private final AndroidDriver driver;

    public By tvChangeMyPin = By.xpath("//android.view.View[@content-desc='How to change my PIN?']");
    public By tvContactUs = By.xpath("//android.view.View[@content-desc='Contact Us']");
    public FaqPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getChoosenFaq(String faqName) {
        return driver.findElement(By.xpath("//android.view.View[@content-desc='"+faqName+"']"));
    }

    public WebElement getTvContactUs() {
        return driver.findElement(this.tvContactUs);
    }
}
