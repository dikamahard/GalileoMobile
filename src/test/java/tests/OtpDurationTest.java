package tests;

import Pages.*;
import Utils.AppiumDriverUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OtpDurationTest {

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
    public void durationOf() {

    }

    @Test
    public void durationAddEmailByEmailOtpTest() throws InterruptedException {
        EmailPage emailPage = new EmailPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.etPhoneorEmail));

        Thread.sleep(3000);
        try {
            if (driver.findElement(By.xpath("//android.widget.RelativeLayout[@resource-id='android:id/content']")).isDisplayed()) {
                driver.navigate().back();
                System.out.println("displayed");
            }
        }catch (Exception e) {
            System.out.println("Error displayed");
            System.out.println(e.getMessage());
        }finally {
            System.out.println("finally");
        }

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(myProfilePage.btnEmail));
        myProfilePage.getBtnEmail().click();

        emailPage.getBtnAddEmail().click();
        emailPage.getEtEmail().click();
        emailPage.getEtEmail().sendKeys("rizky20009@mail.unpad.ac.id");


        // otp automation
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

        // start count here
        emailPage.getBtnAddEmail().click();
        Instant start = Instant.now();

        driver.openNotifications();

        // waiting for the email otp notification to show up
        new WebDriverWait(driver, Duration.ofSeconds(120)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.emailNotification));
        // stop count here
        Instant end = Instant.now();
        emailPage.getEmailNotification().click();

        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Duration for AddEmailByEmailOtp : " + timeElapsed + " Seconds");

    }

    @Test
    public void durationAddPhoneNumberByNumberOtpTest() {
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
        action.sendKeys(phoneNum).perform();      // this is doable

        // count start here
        Instant start = Instant.now();

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
        // count ends here
        Instant end = Instant.now();

        Duration timeElapsed = Duration.between(start, end);

        System.out.println("Duration for AddPhoneNumberByEmailOtp : " + timeElapsed + " Seconds");

    }



    public static void openApp() throws MalformedURLException {
        driver = AppiumDriverUtil.getAndroidDriverQA();

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
