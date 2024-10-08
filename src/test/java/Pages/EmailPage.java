package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EmailPage {

    private final AndroidDriver driver;

    public By btnAddEmail = By.xpath("//android.widget.Button[@content-desc='Add Email']");
    public By etEmail = By.xpath("//android.view.View[@content-desc='E-mail']/following-sibling::android.widget.EditText");
    public By errorMsgInputEmail = By.xpath("//android.view.View[@content-desc='Please Input E-mail']");
    public By errorMsgEmailFormat = By.xpath("//android.view.View[@content-desc='Please Input Correct E-mail Format']");
    public By emailNotification = By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='PVS Galileo']");
    public By tvOTP = By.xpath("//android.widget.TextView[@text='Please use the following OTP Code to continue the login process:']/following-sibling::android.widget.TextView");
    public By dialogUpdateSuccess = By.xpath("//android.view.View[@content-desc='Update Success']");
    public By btnOk = By.xpath("//android.widget.Button[@content-desc='OK']");
    public By dialogAlreadyRegistered = By.xpath("//android.view.View[@content-desc='E-mail\nEmail already registered']");
    public By dialogOtpFailed = By.xpath("//android.view.View[@content-desc='OTP Failed\n[FAILED] Invalid OTP']");
    public By dialogInvalidPin = By.xpath("//android.view.View[@content-desc='Authentication\n55 - Invalid PIN']");
    public By dialogPinExceedMaxAttempt = By.xpath("//android.view.View[@content-desc='Authentication\nInvalid PIN exceed max attempt, please login again']");
    public By btnDeleteEmail = By.xpath("//android.view.View[@content-desc='rhdhdh@gmail.com\nrizky20009@mail.unpad.ac.id']/android.view.View[2]");
    public By dialogSureDeleteEmail = By.xpath("//android.view.View[@content-desc='E-mail\nAre you sure you want to delete this Email?\nNo']");
    public By btnYes = By.xpath("//android.widget.Button[@content-desc='Yes']");
    public EmailPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getBtnAddEmail() {
        return driver.findElement(btnAddEmail);
    }

    public WebElement getEtEmail() {
        return driver.findElement(etEmail);
    }

    public WebElement getErrorMsgInputEmail() {
        return driver.findElement(errorMsgInputEmail);
    }

    public WebElement getEmailNotification() {
        return driver.findElement(emailNotification);
    }

    public WebElement getTvOtp() {
        return driver.findElement(tvOTP);
    }

    public WebElement getDialogUpdateSuccess() {
        return driver.findElement(dialogUpdateSuccess);
    }

    public WebElement getDialogAlreadyRegistered() {
        return driver.findElement(dialogAlreadyRegistered);
    }

    public WebElement getBtnOk() {
        return driver.findElement(btnOk);
    }

    public WebElement getDialogOtpFailed() {
        return driver.findElement(dialogOtpFailed);
    }

    public WebElement getErrorMsgEmailFormat() {
        return driver.findElement(errorMsgEmailFormat);
    }

    public WebElement getDialogInvalidPin() {
        return driver.findElement(dialogInvalidPin);
    }

    public WebElement getDialogPinExceedMaxAttempt() {
        return driver.findElement(dialogPinExceedMaxAttempt);
    }

    public WebElement getDialogSureDeleteEmail() {
        return  driver.findElement(dialogSureDeleteEmail);
    }

    public WebElement getBtnDeleteEmail() {
        return driver.findElement(btnDeleteEmail);
    }

    public WebElement getBtnYes() {
        return driver.findElement(btnYes);
    }
}
