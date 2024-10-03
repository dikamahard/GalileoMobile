package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PinPage {

    private final AndroidDriver driver;

    public By etOldPin = By.xpath("//android.view.View[@content-desc='Enter your old PIN']/following-sibling::android.widget.EditText");
    public By etNewPin = By.xpath("//android.view.View[@content-desc='Enter your new PIN']/following-sibling::android.widget.EditText");
    public By etNewPinAgain = By.xpath("//android.view.View[@content-desc='Enter your new PIN again']/following-sibling::android.widget.EditText");

    public By dialogCantWithSamePin = By.xpath("//android.view.View[@content-desc='Change Pin\nCannot change with the same PIN']");
    public By dialogNewPinNotMatch = By.xpath("//android.view.View[@content-desc=\"Change Pin\nNew PIN didn't match !\"]");
    public By dialogInvalidPin = By.xpath("//android.view.View[@content-desc='Change Pin\n55 - Invalid PIN']");
    public By dialogUpdateSuccess = By.xpath("//android.view.View[@content-desc='Update Success']");
    public By btnOk = By.xpath("//android.widget.Button[@content-desc='OK']");
    public PinPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getDialogCantWithSamePin() {
        return driver.findElement(dialogCantWithSamePin);
    }

    public WebElement getDialogNewPinNotMatch() {
        return driver.findElement(dialogNewPinNotMatch);
    }

    public WebElement getDialogUpdateSuccess() {
        return driver.findElement(dialogUpdateSuccess);
    }

    public WebElement getDialogInvalidPin() {
        return driver.findElement(dialogInvalidPin);
    }

    public WebElement getBtnOk() {
        return driver.findElement(btnOk);
    }

    public WebElement getEtOldPin() {
        return driver.findElement(etOldPin);
    }

    public WebElement getNewPin() {
        return driver.findElement(etNewPin);
    }

    public WebElement getNewPinAgain() {
        return driver.findElement(etNewPinAgain);
    }
}
