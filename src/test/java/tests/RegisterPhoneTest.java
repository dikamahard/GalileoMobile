package tests;

import Pages.*;
import Utils.AppiumDriverUtil;
import dev.failsafe.internal.util.Assert;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterPhoneTest {
    static AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        // Initialize the driver and open the app before each test
        openApp();
    }

    @AfterEach
    public void tearDown() {
        // Close the app after each test, but don't quit the session
        driver.terminateApp("com.pvs.project.galileo");
    }

    @Test
    public void addRegisterPhoneTest() throws InterruptedException {
        RegisterPhonePage registerPhonePage = new RegisterPhonePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        String phoneNum = "087883849227";

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(myProfilePage.btnPhoneNumber));
        myProfilePage.getBtnPhoneNumber().click();

        registerPhonePage.getBtnAddPhoneNumber().click();
        registerPhonePage.getEtPhoneNumber().click();
        //registerPhonePage.getEtPhoneNumber().sendKeys("087883849227");  // why cannot do? cause the element was blocked by saved number popup
        action.sendKeys(phoneNum).perform();      // this is doable
        //driver.hideKeyboard();

        //otp automation
        driver.openNotifications();
        try {
            // condition if there are notification in the notification bar
            WebElement notificationsDelete = driver.findElement(By.xpath("//android.view.View[@content-desc='Clear all notifications.']"));
            if(notificationsDelete.isDisplayed()) {
                notificationsDelete.click();
            }
        }catch (Exception e) {
            // if not than press back
            System.out.println(e.getMessage());
            driver.navigate().back();
        }

        registerPhonePage.getBtnAddPhoneNumber().click();

        driver.openNotifications();

        // waiting for the otp notification to show up
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@resource-id='android:id/text' and contains(@text, 'verification code')]")
        ));

        String otpText = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text' and contains(@text, 'verification code')]")).getText();
        String otpCode = otpText.replaceAll("[^0-9]", "");

        // input otp
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(otpCode);

        // input pin
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        inputPIn();
        System.out.println("done input pin");

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPhonePage.dialogUpdateSuccess));

        assertTrue(registerPhonePage.getDialogUpdateSuccess().isDisplayed());
        registerPhonePage.getBtnOk().click();

        assertTrue(registerPhonePage.addedPhoneNum(phoneNum).isDisplayed());
    }

    @Test
    public void deleteRegisterPhoneTest() {
        RegisterPhonePage registerPhonePage = new RegisterPhonePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        String phoneNum = "087883849227";

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(myProfilePage.btnPhoneNumber));
        myProfilePage.getBtnPhoneNumber().click();

        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='+6287883849227\n+62087883849227']/android.view.View[2]")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPhonePage.dialogDeletePhoneNumber));

        registerPhonePage.getBtnYes().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPhonePage.dialogUpdateSuccess));
        assertTrue(registerPhonePage.getDialogUpdateSuccess().isDisplayed());
        registerPhonePage.getBtnOk().click();
    }


    public static void openApp() throws MalformedURLException {
        driver = AppiumDriverUtil.getAndroidDriver();

        System.out.println("App Started");
    }

    public static void inputPIn() {
        driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
        System.out.println("key 1 pressed");
        driver.pressKey(new KeyEvent(AndroidKey.DIGIT_2));
        System.out.println("key 2 pressed");
        driver.pressKey(new KeyEvent(AndroidKey.DIGIT_3));
        System.out.println("key 3 pressed");
        driver.pressKey(new KeyEvent(AndroidKey.DIGIT_4));
        System.out.println("key 4 pressed");
        driver.pressKey(new KeyEvent(AndroidKey.DIGIT_5));
        System.out.println("key 5 pressed");
        driver.pressKey(new KeyEvent(AndroidKey.DIGIT_6));
        System.out.println("key 6 pressed");
    }
}
