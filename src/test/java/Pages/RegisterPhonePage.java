package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterPhonePage {

    private final AndroidDriver driver;

    public RegisterPhonePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public By btnAddPhoneNumber = By.xpath("//android.widget.Button[@content-desc='Add Phone Number']");
    public By etPhoneNumber = By.xpath("//android.widget.EditText");
    public By btnCountry = By.xpath("//android.widget.EditText/child::android.view.View");
    public By dialogUpdateSuccess = By.xpath("//android.view.View[@content-desc='Update Success']");
    public By btnOk = By.xpath("//android.widget.Button[@content-desc='OK']");
    public By dialogDeletePhoneNumber = By.xpath("//android.view.View[@content-desc='Phone Number\nAre you sure you want to delete this Phone?\nNo']");
    public By btnYes = By.xpath("//android.widget.Button[@content-desc='Yes']");

    public WebElement addedPhoneNum(String phoneNumber) {
        return driver.findElement(By.xpath("//android.widget.ImageView[contains(@content-desc, '"+phoneNumber+"')]"));
    }
    public WebElement getBtnAddPhoneNumber() {
        return driver.findElement(this.btnAddPhoneNumber);
    }

    public WebElement getEtPhoneNumber() {
        return driver.findElement(this.etPhoneNumber);
    }

    public WebElement getBtnCountry() {
        return driver.findElement(this.btnCountry);
    }

    public WebElement getDialogUpdateSuccess() {
        return driver.findElement(this.dialogUpdateSuccess);
    }

    public WebElement getBtnOk() {
        return driver.findElement(this.btnOk);
    }

    public WebElement getBtnYes() {
        return driver.findElement(this.btnYes);
    }

}
