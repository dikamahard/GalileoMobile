package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private final AndroidDriver driver;

    public By btnRegister = By.xpath("//android.widget.Button[@content-desc='Register']");

    public By btnSignIn = By.xpath("//android.widget.Button[@content-desc='Sign In']");
    public By onBoardingBanner = By.xpath("//android.widget.ImageView/following-sibling::android.widget.EditText");
    public By loginBanner = By.xpath("//android.view.View[@content-desc='Welcome, Please Login']");

    public By etPhoneorEmail = By.xpath("//android.widget.EditText");
    public By dialogMemberNotFound = By.xpath("//android.view.View[@content-desc='Sign In Failed\n75102 - Member not found']");
    public By errorMsgWrongFormatNumber = By.xpath("//android.view.View[@content-desc='Please Input Phone Number Correctly']");
    public By errorMsgWrongFormatEmail = By.xpath("//android.view.View[@content-desc='Please Input Correct E-mail Format']");
    public By errorMsgBlankData = By.xpath("//android.view.View[@content-desc='Please Input Phone Number']");
    public By dialogInvalidPin = By.xpath("//android.view.View[@content-desc='Authentication\n55 - Invalid PIN']");
    public By dialogInvalidPin3Time = By.xpath("//android.view.View[@content-desc='Authentication\nInvalid PIN exceed max attempt, please login again']");
    public By btnOk = By.xpath("//android.widget.Button[@content-desc='OK']");
    public By btnForgotPin = By.xpath("//android.view.View[@content-desc='Forgot PIN']");
    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getBtnRegister() {
        return driver.findElement(btnRegister);
    }

    public WebElement getBtnSignIn() {
        return driver.findElement(btnSignIn);
    }

    public WebElement getBtnOk() {
        return driver.findElement(btnOk);
    }

    public int getBtnForgotPinX() {
        int x = driver.findElement(btnForgotPin).getLocation().getX();
        int y = driver.findElement(btnForgotPin).getLocation().getY();

        int x2 = driver.findElement(btnForgotPin).getLocation().getX() + driver.findElement(btnForgotPin).getSize().getWidth();
        int y2 = driver.findElement(btnForgotPin).getLocation().getY() + driver.findElement(btnForgotPin).getSize().getHeight();

        return (x2 - x)/2 + 83;
    }

    public int getBtnForgotPinY() {
        int x = driver.findElement(btnForgotPin).getLocation().getX();
        int y = driver.findElement(btnForgotPin).getLocation().getY();

        int x2 = driver.findElement(btnForgotPin).getLocation().getX() + driver.findElement(btnForgotPin).getSize().getWidth();
        int y2 = driver.findElement(btnForgotPin).getLocation().getY() + driver.findElement(btnForgotPin).getSize().getHeight();

        return y2;
    }
    public WebElement getEtPhoneorEmail() {
        return driver.findElement(etPhoneorEmail);
    }

    public WebElement getDialogMemberNotFound() {
        return driver.findElement(this.dialogMemberNotFound);
    }

    public WebElement getErrorMsgWrongFormatNumber() {
        return driver.findElement(this.errorMsgWrongFormatNumber);
    }

    public WebElement getErrorMsgWrongFormatEmail() {
        return driver.findElement(this.errorMsgWrongFormatEmail);
    }

    public WebElement getErrorMsgBlankData() {
        return driver.findElement(this.errorMsgBlankData);
    }

    public WebElement getDialogInvalidPin() {
        return driver.findElement(this.dialogInvalidPin);
    }

    public WebElement getDialogInvalidPin3Times() {
        return driver.findElement(this.dialogInvalidPin3Time);
    }

    public WebElement getOnBoardingBanner() {
        return driver.findElement(onBoardingBanner);
    }

    public WebElement getLoginBanner() {
        return driver.findElement(this.loginBanner);
    }

    public void clickBtnRegister() {
        this.getBtnRegister().click();
    }

    public void clickBtnSignIn() {
        this.getBtnSignIn().click();
    }

    public void clickBtnOk() {
        this.getBtnOk().click();
    }

}
