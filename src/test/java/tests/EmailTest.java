package tests;

import Pages.*;
import Utils.AppiumDriverUtil;
import dev.failsafe.internal.util.Assert;
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
import java.util.Arrays;

import static Utils.Helper.tapByCoordinate;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailTest {

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
    public void addRegisterEmail() throws InterruptedException {
        EmailPage emailPage = new EmailPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        // sign in (qa)
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.etPhoneorEmail));
        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("087883849227");

        loginPage.clickBtnSignIn();

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

        Thread.sleep(3000);
//        if (driver.findElement(By.xpath("//android.widget.RelativeLayout[@resource-id='android:id/content']")).isDisplayed()) {
//            driver.navigate().back();
//            System.out.println("test if");
//        }
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

        emailPage.getBtnAddEmail().click();
        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.tvOTP));



        driver.openNotifications();

        // waiting for the email otp notification to show up
        new WebDriverWait(driver, Duration.ofSeconds(120)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.emailNotification));

        emailPage.getEmailNotification().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.tvOTP));
        String emailOtpText = driver.findElement(emailPage.tvOTP).getText();

        System.out.println(emailOtpText);
        //String otpCode = otpText.replaceAll("[^0-9]", "");

        //navigate back to galileo
        driver.activateApp("com.pvs.project.galileo.qa");

        // input otp
        action.sendKeys(emailOtpText).perform();

        Thread.sleep(3000);
        //new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.RelativeLayout[@resource-id='android:id/content']")));
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

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogUpdateSuccess));

        assertTrue(emailPage.getDialogUpdateSuccess().isDisplayed());
        emailPage.getBtnOk().click();
    }

    @Test
    public void addEmailEmptyEmail() throws InterruptedException {
        EmailPage emailPage = new EmailPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

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
        emailPage.getBtnAddEmail().click();

        assertTrue(emailPage.getErrorMsgInputEmail().isDisplayed());

    }

    @Test
    public void addEmailAlreadyRegistered() throws InterruptedException {
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
        emailPage.getBtnAddEmail().click();

        assertTrue(emailPage.getDialogAlreadyRegistered().isDisplayed());

    }

    @Test
    public void addEmailWrongFormat() throws InterruptedException {
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
        emailPage.getEtEmail().sendKeys("rizky20009");
        emailPage.getBtnAddEmail().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.errorMsgEmailFormat));
        assertTrue(emailPage.getErrorMsgEmailFormat().isDisplayed());

    }

    @Test
    public void addEmailFalseOtp3Times() throws InterruptedException {
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
        emailPage.getEtEmail().sendKeys("sadfsa12@gmail.com");


        emailPage.getBtnAddEmail().click();
        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.tvOTP));

        // input false otp 3 times
        action.sendKeys("121212").perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogOtpFailed));
        emailPage.getBtnOk().click();
        action.sendKeys("444555").perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogOtpFailed));
        emailPage.getBtnOk().click();
        action.sendKeys("232323").perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogOtpFailed));
        emailPage.getBtnOk().click();


        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.btnSignIn));

        assertTrue(loginPage.getBtnSignIn().isDisplayed());
    }

    @Test
    public void addEmailFalseOtp() throws InterruptedException {
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
        emailPage.getEtEmail().sendKeys("asdasd12@gmail.com");


        emailPage.getBtnAddEmail().click();
        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.tvOTP));

        // input false otp
        action.sendKeys("121212").perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogOtpFailed));

        assertTrue(emailPage.getDialogOtpFailed().isDisplayed());
    }

    @Test
    public void addEmailFalsePin() throws InterruptedException {
        EmailPage emailPage = new EmailPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
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
        inputPIn();

        // error display here

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

        emailPage.getBtnAddEmail().click();
        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.tvOTP));



        driver.openNotifications();

        // waiting for the email otp notification to show up
        new WebDriverWait(driver, Duration.ofSeconds(120)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.emailNotification));

        emailPage.getEmailNotification().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.tvOTP));
        String emailOtpText = driver.findElement(emailPage.tvOTP).getText();

        System.out.println(emailOtpText);
        //String otpCode = otpText.replaceAll("[^0-9]", "");

        //navigate back to galileo
        driver.activateApp("com.pvs.project.galileo.qa");

        // input otp
        action.sendKeys(emailOtpText).perform();

        Thread.sleep(3000);
        //new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.RelativeLayout[@resource-id='android:id/content']")));
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
        inputFalsePIn();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogInvalidPin));
        assertTrue(emailPage.getDialogInvalidPin().isDisplayed());
    }

    @Test
    public void addEmailFalsePin3Times() throws InterruptedException {
        EmailPage emailPage = new EmailPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
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
        inputPIn();

        // error display here

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

        emailPage.getBtnAddEmail().click();
        //new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.tvOTP));


        driver.openNotifications();

        // waiting for the email otp notification to show up
        new WebDriverWait(driver, Duration.ofSeconds(300)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.emailNotification));

        emailPage.getEmailNotification().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.tvOTP));
        String emailOtpText = driver.findElement(emailPage.tvOTP).getText();

        System.out.println(emailOtpText);
        //String otpCode = otpText.replaceAll("[^0-9]", "");

        //navigate back to galileo
        driver.activateApp("com.pvs.project.galileo.qa");

        // input otp
        action.sendKeys(emailOtpText).perform();

        Thread.sleep(3000);
        //new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.RelativeLayout[@resource-id='android:id/content']")));
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
        inputFalsePIn();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogInvalidPin));
        emailPage.getBtnOk().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        tapByCoordinate(466, 560);
        inputFalsePIn();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogInvalidPin));
        emailPage.getBtnOk().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        tapByCoordinate(466, 560);
        inputFalsePIn();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogInvalidPin));
        emailPage.getBtnOk().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogPinExceedMaxAttempt));

        assertTrue(emailPage.getDialogPinExceedMaxAttempt().isDisplayed());
    }

    @Test
    public void deleteEmailTest() throws InterruptedException {
        EmailPage emailPage = new EmailPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
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
        inputPIn();

        // error display here

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(myProfilePage.btnEmail));
        myProfilePage.getBtnEmail().click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.btnDeleteEmail));
        emailPage.getBtnDeleteEmail().click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogSureDeleteEmail));
        emailPage.getBtnYes().click();

        Thread.sleep(3000);
        //new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.RelativeLayout[@resource-id='android:id/content']")));
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

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(emailPage.dialogUpdateSuccess));
        assertTrue(emailPage.getDialogUpdateSuccess().isDisplayed());
    }

    //TODO: delete email wrong pin and wrong pin 3 times


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


}
