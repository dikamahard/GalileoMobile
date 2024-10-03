package tests;

import Pages.HomePage;
import Pages.MyProfilePage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import Pages.LoginPage;
import Utils.AppiumDriverUtil;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

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
    public void signInTest() throws InterruptedException {

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
//        loginPage.getEtPhoneorEmail().sendKeys("081119608545");
        loginPage.getEtPhoneorEmail().sendKeys("087883849227");

        loginPage.clickBtnSignIn();

        // Deny all permission dialog
        Thread.sleep(2000);
        handlePermissionDialog("pictures and record", "DENY");
        Thread.sleep(2000);
        handlePermissionDialog("location", "DENY");
        Thread.sleep(2000);
        handlePermissionDialog("photos and media", "DENY");
        Thread.sleep(2000);
        handlePermissionDialog("location", "DENY &");

        //otp automation
        driver.openNotifications();
        try {
            WebElement notificationsDelete = driver.findElement(By.xpath("//android.view.View[@content-desc='Clear all notifications.']"));
            if(notificationsDelete.isDisplayed()) {
                notificationsDelete.click();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
            driver.navigate().back();
        }

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

        // input PIN
        String pin = "123456";
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        //driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(pin);
        //inputPIn();
        Actions action = new Actions(driver);
        action.sendKeys("123456").perform();

        // assertion home page is showed. (need  some adjustment if opening for the first time)
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnHome));
        assertTrue(homePage.getBtnHome().isDisplayed());
    }

    @Test
    public void loginTest() throws MalformedURLException {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
//        driver.findElement(By.xpath("//android.widget.EditText")).click();
//        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("123456");

        Actions action = new Actions(driver);
        action.sendKeys("123456").perform();

        // assertion home page is showed.
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnHome));
        assertTrue(homePage.getBtnHome().isDisplayed());

    }

    @Test
    public void signoutTest() throws MalformedURLException {
        HomePage homePage = new HomePage(driver);
        MyProfilePage profilePage = new MyProfilePage(driver);

        //precond (user must be logged in the app)
        loginTest();

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        //Scroll
        driver.findElement(AppiumBy.androidUIAutomator(        "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"Sign Out\"))")).click();

        profilePage.getBtnYes().click();

    }

    @Test
    public void loginWithBlankDataTest() throws MalformedURLException {

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister));

        loginPage.clickBtnSignIn();

        assertTrue(loginPage.getErrorMsgBlankData().isDisplayed());

    }


    @Test
    public void loginWithUnregisteredEmailTest() throws MalformedURLException {


        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("lala@gmail.com");
        loginPage.clickBtnSignIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.dialogMemberNotFound));

        assertTrue(loginPage.getDialogMemberNotFound().isDisplayed());


    }

    @Test
    public void loginWithUnregisteredNumberTest() throws MalformedURLException {

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("08132686752");
        loginPage.clickBtnSignIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.dialogMemberNotFound));

        assertTrue(loginPage.getDialogMemberNotFound().isDisplayed());

    }

    @Test
    public void loginWithNumberLessThan8DigitTest() throws MalformedURLException {

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("9737298");
        loginPage.clickBtnSignIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.errorMsgWrongFormatNumber));

        assertTrue(loginPage.getErrorMsgWrongFormatNumber().isDisplayed());

    }

    @Test
    public void loginWithNumberMoreThan20Digit() throws MalformedURLException {

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("081234567890123456789054");
        loginPage.clickBtnSignIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.errorMsgWrongFormatNumber));

        assertTrue(loginPage.getErrorMsgWrongFormatNumber().isDisplayed());

    }

    @Test
    public void loginWithEmailWithoutAdd() throws MalformedURLException {

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("ichaputrisya.gmail.com");
        loginPage.clickBtnSignIn();

        assertTrue(loginPage.getErrorMsgWrongFormatEmail().isDisplayed());
    }

    @Test
    public void loginWithEmailWithoutDomain() throws MalformedURLException {

        LoginPage loginPage = new LoginPage(driver);


        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("ichaputrisya@gmail");
        loginPage.clickBtnSignIn();

        assertTrue(loginPage.getErrorMsgWrongFormatEmail().isDisplayed());
    }

    @Test
    public void loginWithIncorrectPin() {
        Actions action = new Actions(driver);
        LoginPage loginPage = new LoginPage(driver);

        // wait
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        // input wrong pin
        action.sendKeys("111111").perform();

        // assert dialog invalid pin
        assertTrue(loginPage.getDialogInvalidPin().isDisplayed());

        loginPage.clickBtnOk();
    }

    @Test
    public void loginWithIncorrectPin3Time() throws InterruptedException {
        Actions action = new Actions(driver);
        LoginPage loginPage = new LoginPage(driver);
//        TouchAction touch = new TouchAction(driver);

        // precond : user already signed in

        // wait
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        //input wrong pin 3 times
        action.sendKeys("111111").perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.dialogInvalidPin));
        assertTrue(loginPage.getDialogInvalidPin().isDisplayed());
        loginPage.clickBtnOk();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));


        tapByCoordinate(306, 875);

        //driver.findElement(By.xpath("//android.widget.EditText")).click();
        //driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("123456");
        action.sendKeys("111111").perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.dialogInvalidPin));
        assertTrue(loginPage.getDialogInvalidPin().isDisplayed());
        loginPage.clickBtnOk();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        tapByCoordinate(306, 875);
        action.sendKeys(driver.findElement(By.xpath("//android.widget.EditText")),"333333").perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.dialogInvalidPin));
        assertTrue(loginPage.getDialogInvalidPin().isDisplayed());
        loginPage.clickBtnOk();

        // dialog exceed max attempt
        assertTrue(loginPage.getDialogInvalidPin3Times().isDisplayed());
        loginPage.clickBtnOk();

        // redirect to login page
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));
        assertTrue(loginPage.getOnBoardingBanner().isDisplayed());
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
        driver.perform(Arrays.asList(tap));
    }

    public static void handlePermissionDialog(String permissionName, String handle) {
        driver.findElement(By.xpath("//android.widget.TextView[contains(@text, '"+permissionName+"')]/parent::android.widget.LinearLayout/parent::android.widget.LinearLayout/following-sibling::android.widget.LinearLayout/child::android.widget.Button[contains(@text, '"+handle+"')]")).click();
    }
}
