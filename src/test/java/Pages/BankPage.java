package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BankPage {
    private final AndroidDriver driver;

    public By dialogCheckTermsAgreement = By.xpath("//android.view.View[@content-desc='Bank Card\nPlease check our terms & agreement']");
    public By btnOk = By.xpath("//android.widget.Button[@content-desc='OK']");
    public By errorMsgInputCannotEmpty = By.xpath("//android.view.View[@content-desc='Input cannot empty']");
    public By btnAddBankCard = By.xpath("//android.widget.Button[@content-desc='Add Bank Card']");
    public By checkbox = By.xpath("//android.widget.CheckBox");
    public By etCardName = By.xpath("//android.view.View[@content-desc='Cardholder name\nCard Number']/android.widget.EditText[1]");
    public By etCardNumber = By.xpath("//android.view.View[@content-desc='Cardholder name\nCard Number']/android.widget.EditText[2]");
    public By dialogUpdateSuccess = By.xpath("//android.view.View[@content-desc='Update Success']");
    public By bannerEnterPin = By.xpath("//android.view.View[@content-desc='Enter PIN']");
    public By dialogPinExceedMax = By.xpath("//android.view.View[@content-desc='Authentication\nInvalid PIN exceed max attempt, please login again']");
    public By dialogInvalidPin = By.xpath("//android.view.View[@content-desc='Authentication\n55 - Invalid PIN']");
    public BankPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getDialogCheckTermsAgreement() {
        return driver.findElement(this.dialogCheckTermsAgreement);
    }

    public WebElement getCheckBox() {
        return driver.findElement(this.checkbox);
    }

    public WebElement getBtnOk() {
        return driver.findElement(this.btnOk);
    }

    public WebElement getEtCardName() {
        return driver.findElement(this.etCardName);
    }

    public WebElement getEtCardNumber() {
        return driver.findElement(this.etCardNumber);
    }

    public WebElement getDialogUpdateSuccess() {
        return driver.findElement(this.dialogUpdateSuccess);
    }
    public WebElement getDialogIvalidPin() {
        return driver.findElement(this.dialogInvalidPin);
    }

    public WebElement getDialogPinExceedMax() {
        return driver.findElement(this.dialogPinExceedMax);
    }

    public WebElement getBannerEnterPin() {
        return driver.findElement(this.bannerEnterPin);
    }

    public WebElement getErrorMsgInputCannotEmpty() {
        return driver.findElement(this.errorMsgInputCannotEmpty);
    }

    public WebElement getBtnAddBankCard() {
        return driver.findElement(this.btnAddBankCard);
    }


}
