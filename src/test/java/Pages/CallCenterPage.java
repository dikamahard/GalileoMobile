package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CallCenterPage {

    private final AndroidDriver driver;

    public By ivEmail = By.xpath("//android.widget.ImageView[@content-desc='customer.support@galileo.co.id']");
    public By composeTo = By.xpath("//android.widget.Button[@content-desc='customer.support@galileo.co.id, customer.support@galileo.co.id']");
    public By emailTitle = By.xpath("//android.widget.EditText[@resource-id='com.google.android.gm:id/subject']");
    public CallCenterPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getIvEmail() {
        return driver.findElement(this.ivEmail);
    }

    public WebElement getComposeTo() {
        return driver.findElement(this.composeTo);
    }

    public WebElement getEmailTitle() {
        return driver.findElement(this.emailTitle);
    }


}
