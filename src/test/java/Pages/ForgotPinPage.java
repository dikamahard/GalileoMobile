package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ForgotPinPage {

    private final AndroidDriver driver;

    public By etPhoneNumber = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[1]");
    public By etEmail = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[2]");
    public By btnForgotPin = By.xpath("//android.widget.Button[@content-desc='Forgot PIN']");
    public By errorMsgInputEmail = By.xpath("//android.view.View[@content-desc=\"Please Input E-mail\"]");
    public By errorMsgEmailFormat = By.xpath("//android.view.View[@content-desc=\"Please Input Correct E-mail Format\"]");
    public By errorMsgInputPhoneNumber = By.xpath("//android.view.View[@content-desc=\"Please Input Phone Number Correctly\"]");
    public By dialogMemberNotFound = By.xpath("//android.view.View[@content-desc=\"Forgot PIN\n75102 - Member not found\"]");
    public By btnOk = By.xpath("//android.widget.Button[@content-desc=\"OK\"]");
    public ForgotPinPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getEtPhoneNumber() {
        return driver.findElement(etPhoneNumber);
    }

    public WebElement getEtEmail() {
        return driver.findElement(etEmail);
    }

    public WebElement getBtnForgotPin() {
        return driver.findElement(btnForgotPin);
    }

    public WebElement getErrorMsgInputEmail() {
        return driver.findElement(this.errorMsgInputEmail);
    }

    public WebElement getErrorMsgEmailFormat() {
        return driver.findElement(this.errorMsgEmailFormat);
    }

    public WebElement getErrorMsgInputPhoneNumber() {
        return driver.findElement(this.errorMsgInputPhoneNumber);
    }

    public WebElement getDialogMemberNotFound() {
        return driver.findElement(this.dialogMemberNotFound);
    }

    public WebElement getBtnOk() {
        return driver.findElement(this.btnOk);
    }

}
