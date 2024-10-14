package tests;

import Pages.ForgotPinPage;
import Pages.LoginPage;
import Utils.AppiumDriverUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

public class ForgotPinTest {

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

    public void forgotPinTest() {
        LoginPage loginPage = new LoginPage(driver);
        ForgotPinPage forgotPinPage = new ForgotPinPage(driver);


        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));


        tapByCoordinate(loginPage.getBtnForgotPinX(), loginPage.getBtnForgotPinY());

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(forgotPinPage.btnForgotPin));

        forgotPinPage.getEtPhoneNumber().click();
        forgotPinPage.getEtPhoneNumber().sendKeys("87883849227");

        forgotPinPage.getEtEmail().click();
        forgotPinPage.getEtEmail().sendKeys("rizky20009@mail.unpad.ac.id");

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

    public static void tapByCoordinate(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        // Tap by coordinate method //

        // Create a sequence to simulate a touch action
        Sequence tap = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), x, y))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the action
        driver.perform(Collections.singletonList(tap));
    }

}
