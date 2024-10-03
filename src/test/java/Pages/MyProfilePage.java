package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyProfilePage {

    private final AndroidDriver driver;


    public By btnVehicle = By.xpath("//android.widget.ImageView[@content-desc='Vehicle']");
    public By btnSignout = By.xpath("//android.widget.ImageView[@content-desc='Sign Out']");
    public By btnPhoneNumber = By.xpath("//android.widget.ImageView[@content-desc='Phone Number']");
    public By btnYes = By.xpath("//android.widget.Button[@content-desc='Yes']");
    public By btnPin = By.xpath("//android.widget.ImageView[@content-desc='PIN']");
    public By btnBank = By.xpath("//android.widget.ImageView[@content-desc='Bank Card']");
    public MyProfilePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getBtnVehicle() {
        return driver.findElement(btnVehicle);
    }

    public WebElement getBtnYes() {
        return driver.findElement(btnYes);
    }

    public WebElement getBtnSignout() {
        return driver.findElement(btnSignout);
    }

    public WebElement getBtnPhoneNumber() {
        return driver.findElement(btnPhoneNumber);
    }

    public WebElement getBtnPin() {
        return driver.findElement(btnPin);
    }

    public WebElement getBtnBank() {
        return driver.findElement(btnBank);
    }


}
