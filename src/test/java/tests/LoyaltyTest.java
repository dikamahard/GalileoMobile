package tests;

import Pages.*;
import Utils.AppiumDriverUtil;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static Utils.Helper.tapByCoordinate;
import static Utils.Helper.typeSomething;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoyaltyTest {

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


    @Test
    public void registerLoyaltyProgramTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        LoyaltyPage loyaltyPage = new LoyaltyPage(driver);
        HomePage homePage = new HomePage(driver);
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
        typeSomething("123456");

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnExplore().click();

        // TODO: Scroll until located
        //
        ///////////////////////

        Thread.sleep(3000);
        loyaltyPage.getLoyaltyProgram("Chillax Loyalty Program").click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.btnRegister));
        loyaltyPage.getBtnRegister().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        typeSomething("123456");

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.dialogWelcomeToLoyalty));
        assertTrue(loyaltyPage.getDialogWelcomeToLoyalty().isDisplayed());
    }

    @Test
    public void detailLoyaltyProgramMembershipTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        LoyaltyPage loyaltyPage = new LoyaltyPage(driver);
        HomePage homePage = new HomePage(driver);
        Actions action = new Actions(driver);

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
        typeSomething("123456");

        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnHome));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnSeeMembershipDetail));
        //tapByCoordinate(838,791);
        homePage.getBtnSeeMembershipDetail().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.btnLoyaltyTier));
        loyaltyPage.getBtnLoyaltyTier().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.tabLoyaltyTierCalculation));
        loyaltyPage.getIvLoyaltyTierDetail().click();
        Thread.sleep(3000);

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.tabLoyaltyTierReward));
        loyaltyPage.getTabLoyaltyTierReward().click();
        loyaltyPage.getIvLoyaltyTierDetail().click();
        Thread.sleep(3000);

        driver.navigate().back();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.btnHistory));
        loyaltyPage.getBtnHistory().click();
        Thread.sleep(3000);

        driver.navigate().back();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.btnSaving));
        loyaltyPage.getBtnSaving().click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//android.view.View[@content-desc='Your saving detail'])[2]")));
        //assertTrue(driver.findElement(By.xpath("//android.view.View[@content-desc='Your saving detail'])[2]")).isDisplayed());

        driver.navigate().back();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.btnExpiring));
        loyaltyPage.getBtnExpiring().click();
        Thread.sleep(3000);

        driver.navigate().back();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.btnUsed));
        loyaltyPage.getBtnUsed().click();
        Thread.sleep(3000);

        driver.navigate().back();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.btnVoucherStore));
        loyaltyPage.getBtnVoucherStore().click();
        Thread.sleep(3000);
        // TODO: Deep dive into all voucher feature
    }

    @Test
    public void registerLoyaltyFalsePinTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        LoyaltyPage loyaltyPage = new LoyaltyPage(driver);
        HomePage homePage = new HomePage(driver);
        Actions action = new Actions(driver);

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
        typeSomething("123456");

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnExplore().click();

        // TODO: Scroll until located for searching loyalty program
        //
        ///////////////////////

        Thread.sleep(3000);
        loyaltyPage.getLoyaltyProgram("Bcast Loyalty").click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.btnRegister));
        loyaltyPage.getBtnRegister().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        typeSomething("232323");

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loyaltyPage.dialogInvalidPin));
        assertTrue(loyaltyPage.getDialogInvalidPin().isDisplayed());
    }

    @Test
    public void unregisterLoyalty() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        LoyaltyPage loyaltyPage = new LoyaltyPage(driver);
        HomePage homePage = new HomePage(driver);
        Actions action = new Actions(driver);

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
        typeSomething("123456");

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnLoyaltyProgram().click();

        Thread.sleep(3000);
        // DELETETION START HERE
        int startX =1000;
        int endX =55;
        int y = 1300;

//        new TouchAction(driver)
//                .longPress(ElementOption.element(loyaltyPage.getRegisteredLoyalty("Bcast Loyalty"),startX, y))
//                .moveTo(ElementOption.element(loyaltyPage.getRegisteredLoyalty("Bcast Loyalty"), endX, y))
//                .release()
//                .perform();

        // Create a PointerInput object to simulate finger touch
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        // Define the sequence of touch actions
        Sequence swipe = new Sequence(finger, 1);
        // Start the swipe action at the initial location (startX, y)
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), startX, y));
        // Press down (simulating longPress)
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        // Move the finger to the end location (endX, y) to perform the swipe
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, y));
        // Release the finger (simulating release)
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        // Perform the swipe action
        driver.perform(List.of(swipe));

        Thread.sleep(3000);


    }

    public static void openApp() throws MalformedURLException {
        driver = AppiumDriverUtil.getAndroidDriverQA();

        System.out.println("App Started");
    }
}
