package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoyaltyPage {

    private final AndroidDriver driver;

    public By btnRegister = By.xpath("//android.widget.Button[@content-desc='Register']");
    public By tabDescription = By.xpath("//android.view.View[@content-desc='Description\nTab 1 of 3']");
    public By tabPromo = By.xpath("//android.view.View[@content-desc='Promo\nTab 2 of 3']");
    public By tabVoucherStore = By.xpath("//android.view.View[@content-desc='Voucher Store\nTab 3 of 3']");
    public By btnSeeAll = By.xpath("//android.view.View[@content-desc='See All']");
    public By dialogWelcomeToLoyalty = By.xpath("//android.view.View[@content-desc='Welcome to the loyalty program of Chillax Loyalty Program']");
    public By btnX = By.xpath("//android.widget.FrameLayout[@resource-id=\"android:id/content\"]/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[1]");
    public By btnHistory = By.xpath("//android.view.View[@content-desc='History']");
    public By btnSaving = By.xpath("//android.widget.ImageView[contains(@content-desc, 'how much you save this month!')]");
    public By btnExpiring = By.xpath("//android.widget.Button[contains(@content-desc, 'Expiring')]");
    public By btnUsed = By.xpath("//android.widget.Button[contains(@content-desc, 'Used')]");
    public By btnVoucherStore = By.xpath("//android.widget.Button[@content-desc='Voucher\nVoucher Store']");
    public By btnLoyaltyTier = By.xpath("//android.view.View[@content-desc='Chillax Loyalty Program\nGold Member']");
    public By tabLoyaltyTierCalculation = By.xpath("//android.view.View[@content-desc='Calculation\nTab 1 of 2']");
    public By tabLoyaltyTierReward = By.xpath("//android.view.View[@content-desc='Reward\nTab 2 of 2']");
    public By ivLoyaltyTierDetail = By.xpath("//android.widget.ImageView[@content-desc='Gold Member']");
    public By dialogInvalidPin = By.xpath("//android.view.View[@content-desc='Authentication\n55 - Invalid PIN']");
    public By btnOk = By.xpath("//android.widget.Button[@content-desc=\"OK\"]");
    public LoyaltyPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getLoyaltyProgram(String loyaltyName) {
        return driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='"+loyaltyName+"']"));
    }

    public WebElement getBtnRegister() {
        return driver.findElement(this.btnRegister);
    }

    public WebElement getTabDescription() {
        return driver.findElement(this.tabDescription);
    }

    public WebElement getTabPromo() {
        return driver.findElement(this.tabPromo);
    }

    public WebElement getTabVoucherStore() {
        return driver.findElement(this.tabVoucherStore);
    }

    public WebElement getBtnSeeAll() {
        return driver.findElement(this.btnSeeAll);
    }

    public WebElement getIvMerchantPartner(int col) {
        return driver.findElement(By.xpath("//android.view.View[@content-desc='Merchant Partner']/android.view.View[1]/android.view.View/android.view.View/android.view.View["+ col +"]"));
    }

    public WebElement getDialogWelcomeToLoyalty() {
        return driver.findElement(this.dialogWelcomeToLoyalty);
    }

    public WebElement getBtnX() {
        return driver.findElement(this.btnX);
    }

    public WebElement getBtnHistory() {
        return driver.findElement(this.btnHistory);
    }

    public WebElement getBtnSaving() {
        return driver.findElement(this.btnSaving);
    }

    public WebElement getBtnExpiring() {
        return driver.findElement(this.btnExpiring);
    }

    public WebElement getBtnUsed() {
        return driver.findElement(this.btnUsed);
    }

    public WebElement getBtnVoucherStore() {
        return driver.findElement(this.btnVoucherStore);
    }

    public WebElement getBtnLoyaltyTier() {
        return driver.findElement(this.btnLoyaltyTier);
    }

    public WebElement getTabLoyaltyTierCalculation() {
        return driver.findElement(this.tabLoyaltyTierCalculation);
    }

    public WebElement getTabLoyaltyTierReward() {
        return driver.findElement(this.tabLoyaltyTierReward);
    }

    public WebElement getIvLoyaltyTierDetail() {
        return driver.findElement(this.ivLoyaltyTierDetail);
    }

    public WebElement getDialogInvalidPin() {
        return driver.findElement(this.dialogInvalidPin);
    }

    public WebElement getBtnOk() {
        return driver.findElement(this.btnOk);
    }

    public WebElement getRegisteredLoyalty(String loyaltyName) {
        return driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='"+ loyaltyName +"']"));
    }

}
