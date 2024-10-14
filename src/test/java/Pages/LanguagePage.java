package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LanguagePage {

    private final AndroidDriver driver;

    public By btnGunakanIndonesia = By.xpath("//android.widget.Button[@content-desc='Gunakan Bahasa Indonesia']");
    public By btnUseEnglish = By.xpath("//android.widget.Button[@content-desc='Use English']");
    public By btnOk = By.xpath("//android.widget.Button[@content-desc='OK']");
    public By dialogBhsDiubah = By.xpath("//android.view.View[@content-desc='Bahasa telah diubah!']");
    public By dialogLanguageChanged = By.xpath("//android.view.View[@content-desc='Language has been changed !']");
    public By expectedTitleIndonesia = By.xpath("//android.view.View[@content-desc='Profil Saya']");
    public By expectedTitleEnglish = By.xpath("//android.view.View[@content-desc='My Profile']");

    public LanguagePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getBtnGunkanaIndonesia() {
        return driver.findElement(this.btnGunakanIndonesia);
    }

    public WebElement getBtnUseEnglish() {
        return driver.findElement(this.btnUseEnglish);
    }

    public WebElement getBtnOk() {
        return driver.findElement(this.btnOk);
    }

    public WebElement getDialogBhsDiubah() {
        return driver.findElement(this.dialogBhsDiubah);
    }

    public WebElement getDialogLanguageChnged() {
        return driver.findElement(this.dialogLanguageChanged);
    }

    public WebElement getExpectedTitleIndonesia() {
        return driver.findElement(this.expectedTitleIndonesia);
    }

    public WebElement getExpectedTitleEnglish() {
        return driver.findElement(this.expectedTitleEnglish );
    }
}
