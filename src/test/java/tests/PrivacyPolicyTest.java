package tests;

import Pages.*;
import Utils.AppiumDriverUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static Utils.Helper.typeSomething;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrivacyPolicyTest {

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
    public void privacyPolicyTest() throws InterruptedException {
        PrivacyPolicyPage privacyPolicyPage = new PrivacyPolicyPage(driver);
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
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"Privacy & Policy\"))")).click();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(privacyPolicyPage.tvTitle));

        assertTrue(privacyPolicyPage.getTvTitle().isDisplayed());
    }
}
