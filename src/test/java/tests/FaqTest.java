package tests;

import Pages.*;
import Utils.AppiumDriverUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static Utils.Helper.tapByCoordinate;
import static Utils.Helper.typeSomething;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FaqTest {

    static AndroidDriver driver;
    @BeforeEach
    public void setUp() throws MalformedURLException {
        // Initialize the driver and open the app before each test
        openApp();
    }

    @AfterEach
    public void tearDown() {
        // Close the app after each test, but don't quit the session
        driver.terminateApp("com.pvs.project.galileo.qa");
    }

    public static void openApp() throws MalformedURLException {
        driver = AppiumDriverUtil.getAndroidDriverQA();

        System.out.println("App Started");
    }

    @Test
    public void faqTest() throws InterruptedException {
        FaqPage faqPage = new FaqPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        typeSomething("123456");

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        Thread.sleep(1000);

        // scroll here until found
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"FAQ\"))")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Account & Profile\"]")));
        faqPage.getChoosenFaq("How to change my PIN?").click();
        Thread.sleep(1000);
        assertTrue(faqPage.getChoosenFaq("How to change my PIN?").isDisplayed());
    }

    @Test
    public void copyClipboardTest() throws InterruptedException {
        FaqPage faqPage = new FaqPage(driver);
        CallCenterPage callCenterPage = new CallCenterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        typeSomething("123456");

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        Thread.sleep(1000);

        // scroll here until found
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"FAQ\"))")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Account & Profile\"]")));
        faqPage.getChoosenFaq("How to change my PIN?").click();
        Thread.sleep(1000);

        tapByCoordinate(950, 360);

        driver.activateApp("com.miui.notes");

        Thread.sleep(3000);

        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Tap to create a text note. Press and hold to create a voice note. Release the button to stop recording and create the note, or slide up to cancel.\"]")).click();
        String  clipboardText = driver.getClipboardText();

        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.miui.notes:id/rich_editor\"]")).click();
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.miui.notes:id/rich_editor\"]")).sendKeys(clipboardText);

        Thread.sleep(3000);
    }

    @Test
    public void contactUsTest() throws InterruptedException {
        FaqPage faqPage = new FaqPage(driver);
        CallCenterPage callCenterPage = new CallCenterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        typeSomething("123456");

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        Thread.sleep(1000);

        // scroll here until found
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"FAQ\"))")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Account & Profile\"]")));
        faqPage.getChoosenFaq("How to change my PIN?").click();
        Thread.sleep(1000);

        faqPage.getTvContactUs().click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(callCenterPage.ivEmail));
        callCenterPage.getIvEmail().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(callCenterPage.emailTitle));
        assertTrue(callCenterPage.getEmailTitle().isDisplayed());
    }
}
