package tests;

import Pages.*;
import Utils.AppiumDriverUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BankTest {
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

    public static void openApp() throws MalformedURLException {
        driver = AppiumDriverUtil.getAndroidDriver();

        System.out.println("App Started");
    }

    @Test
    public void registerBankTest() {
        BankPage bankPage = new BankPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        myProfilePage.getBtnBank().click();
        bankPage.getBtnAddBankCard().click();
        bankPage.getEtCardName().click();
        driver.findElement(bankPage.etCardName).sendKeys("QWERTY");
        bankPage.getEtCardNumber().click();
        driver.findElement(bankPage.etCardNumber).sendKeys("5555444433332222");

        bankPage.getCheckBox().click();
        driver.hideKeyboard();
        bankPage.getBtnAddBankCard().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bankPage.bannerEnterPin));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bankPage.dialogUpdateSuccess));
        assertTrue(bankPage.getDialogUpdateSuccess().isDisplayed());

        bankPage.getBtnOk().click();
    }

    @Test
    public void registerBankBlankCardNameTest() {
        BankPage bankPage = new BankPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        myProfilePage.getBtnBank().click();
        bankPage.getBtnAddBankCard().click();
        bankPage.getEtCardNumber().click();
        driver.findElement(bankPage.etCardNumber).sendKeys("5555444433332222");

        bankPage.getCheckBox().click();
        driver.hideKeyboard();
        bankPage.getBtnAddBankCard().click();

        assertTrue(bankPage.getErrorMsgInputCannotEmpty().isDisplayed());

    }

    @Test
    public void registerBankBlankCardNumberTest() {
        BankPage bankPage = new BankPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        myProfilePage.getBtnBank().click();
        bankPage.getBtnAddBankCard().click();
        bankPage.getEtCardName().click();
        driver.findElement(bankPage.etCardName).sendKeys("QWERTY");

        bankPage.getCheckBox().click();
        driver.hideKeyboard();
        bankPage.getBtnAddBankCard().click();

        assertTrue(bankPage.getErrorMsgInputCannotEmpty().isDisplayed());
    }

    @Test
    public void registerBankWithoutCheckbox() {
        BankPage bankPage = new BankPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        myProfilePage.getBtnBank().click();
        bankPage.getBtnAddBankCard().click();
        bankPage.getEtCardName().click();
        driver.findElement(bankPage.etCardName).sendKeys("QWERTY");
        bankPage.getEtCardNumber().click();
        driver.findElement(bankPage.etCardNumber).sendKeys("5555444433332222");

        driver.hideKeyboard();
        bankPage.getBtnAddBankCard().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bankPage.dialogCheckTermsAgreement));

        assertTrue(bankPage.getDialogCheckTermsAgreement().isDisplayed());

    }

    @Test
    public void registerBankFalsePin3Times() {
        BankPage bankPage = new BankPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        myProfilePage.getBtnBank().click();
        bankPage.getBtnAddBankCard().click();
        bankPage.getEtCardName().click();
        driver.findElement(bankPage.etCardName).sendKeys("QWERTY");
        bankPage.getEtCardNumber().click();
        driver.findElement(bankPage.etCardNumber).sendKeys("5555444433332222");

        driver.hideKeyboard();
        bankPage.getCheckBox().click();
        bankPage.getBtnAddBankCard().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bankPage.bannerEnterPin));
        action.sendKeys("111111").perform();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bankPage.dialogInvalidPin));

        bankPage.getBtnOk().click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bankPage.bannerEnterPin));
        tapByCoordinate(325,575);
        action.sendKeys("111111").perform();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bankPage.dialogInvalidPin));

        bankPage.getBtnOk().click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bankPage.bannerEnterPin));
        tapByCoordinate(325,575);
        action.sendKeys("111111").perform();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bankPage.dialogInvalidPin));

        bankPage.getBtnOk().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(bankPage.dialogPinExceedMax));
        assertTrue(bankPage.getDialogPinExceedMax().isDisplayed());

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

    public static void inputFalsePIn() {
        driver.pressKey(new KeyEvent(AndroidKey.DIGIT_2));
        System.out.println("key 2 pressed");
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

    public static void tapByCoordinate(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Tap by coordinate method //

        // Create a sequence to simulate a touch action
        Sequence tap = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the action
        driver.perform(Arrays.asList(tap));
    }


}
