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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LanguageTest {
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
    public void languageTest() throws InterruptedException {
        LanguagePage languagePage = new LanguagePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        String expectedTitle = "Profil Saya";

        Thread.sleep(5000);

        //new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        typeSomething("123456");

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        Thread.sleep(1000);

        // scroll here until found
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"Bahasa Indonesia\"))")).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(languagePage.btnGunakanIndonesia));
        languagePage.getBtnGunkanaIndonesia().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(languagePage.dialogBhsDiubah));
        languagePage.getBtnOk().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(languagePage.expectedTitleIndonesia));
        assertEquals(expectedTitle, languagePage.getExpectedTitleIndonesia().getAttribute("content-desc"));

    }
}
