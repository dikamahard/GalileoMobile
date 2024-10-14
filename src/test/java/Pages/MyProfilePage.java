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
    public By btnEmail = By.xpath("//android.widget.ImageView[@content-desc='E-mail']");
    public By btnFaq = By.xpath("//android.widget.ImageView[@content-desc='FAQ']");
    public By btnCallCenter = By.xpath("//android.widget.ImageView[@content-desc='Call Center']");
    public By btnTermsCondition = By.xpath("//android.widget.ImageView[@content-desc='Terms & Condition']");
    public By btnPrivacyPolicy = By.xpath("//android.widget.ImageView[@content-desc='Privacy & Policy']");
    public By btnEnglish = By.xpath("//android.widget.ImageView[@content-desc='English Language']");
    public By btnIndonesia = By.xpath("//android.widget.ImageView[@content-desc='Bahasa Indonesia']");
    public By scrollableScreen = By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View");
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

    public WebElement getBtnEmail() {
        return driver.findElement(btnEmail);
    }

    public WebElement getBtnFaq() {
        return driver.findElement(btnFaq);
    }

    public WebElement getBtnCallCenter() {
        return driver.findElement(btnCallCenter);
    }

    public WebElement getBtnTermsCondition() {
        return driver.findElement(btnTermsCondition);
    }

    public WebElement getBtnPrivacyPolicy() {
        return driver.findElement(btnPrivacyPolicy);
    }

    public WebElement getBtnEnglish() {
        return driver.findElement(btnEnglish);
    }

    public WebElement getBtnIndonesia() {
        return driver.findElement(btnIndonesia);
    }

    public WebElement getScrollableScreen() {
        return driver.findElement(scrollableScreen);
    }
}
