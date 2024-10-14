package tests;

import Pages.EmailPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.MyProfilePage;
import Utils.AppiumDriverUtil;
import io.appium.java_client.android.AndroidDriver;
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

public class SwitchAppTest {
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
    public void switchAppTest() throws InterruptedException {
        EmailPage emailPage = new EmailPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);


        Thread.sleep(3000);


        // switch to gmail
        driver.activateApp("com.google.android.gm");

        By tvGalileo = By.xpath("//android.widget.TextView[@resource-id=\"com.google.android.gm:id/senders\" and @text=\"PVS Galileo\"]");

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(tvGalileo));
        driver.findElement(tvGalileo).click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.tvOTP));
        String emailOtpText = driver.findElement(emailPage.tvOTP).getText();

        System.out.println(emailOtpText);

        //navigate back to galileo
        driver.activateApp("com.pvs.project.galileo.qa");

        // input otp
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.btnSignIn));
        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys(emailOtpText);


    }


    public static void openApp() throws MalformedURLException {
        driver = AppiumDriverUtil.getAndroidDriverQA();

        System.out.println("App Started");
    }
}
