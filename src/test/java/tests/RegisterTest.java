package tests;

import Pages.LoginPage;
import Pages.RegisterPage;
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

import static Utils.Helper.typeSomething;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest {
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
    public void registerTest() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");

        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");
        // android 10 only
        /*
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(
           calendarDialog.btnSelectYear
        ));
        System.out.println("wait btnselectyear done");
        */
/*
//TODO: mechanism for scrolling calendar dialog
        calendarDialog.getBtnSelectYear().click();
        System.out.println("btnselectyear click");
        calendarDialog.scrollToChoosenYear();
 */
        calendarDialog.inputDOB();
        registerPage.inputEmail("rizkytest@gmail.com");
        driver.hideKeyboard();
        registerPage.inputPhoneNumber("087883849227");
//        registerPage.inputPhoneNumber("081119608545");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();
//        registerPage.getBtnRegister().click();

        // otp automation
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

        registerPage.getBtnRegister().click();

        driver.openNotifications();

        // waiting for the otp notification to show up
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@resource-id='android:id/text' and contains(@text, 'verification code')]")
        ));

        String otpText = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text' and contains(@text, 'verification code')]")).getText();
        String otpCode = otpText.replaceAll("[^0-9]", "");

        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(otpCode);

        // create PIN
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        String pin = "123456";
        typeSomething(pin);


        // re input pin
        System.out.println("re input pin");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.EditText")).click();
        //driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("123456");

        Actions action = new Actions(driver);
        action.sendKeys("123456").perform();

        // assertion home page is showed

    }

    @Test
    public void registerSameNameTest() {

    }

    @Test
    public void registerBlankNameTest() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");



        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputEmail("rizkytest@gmail.com");
        driver.hideKeyboard();
        registerPage.inputPhoneNumber("087883849227");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();


        registerPage.getBtnRegister().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPage.errorMsgInputFullName));

        assertTrue(registerPage.getErrorMsgInputFullName().isDisplayed());
    }

    @Test
    public void registerBlankEmailTest() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");


        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputPhoneNumber("087883849227");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();


        registerPage.getBtnRegister().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPage.errorMsgInputEmail));

        assertTrue(registerPage.getErrorMsgInputEmail().isDisplayed());
    }

    @Test
    public void registerBlankNumberTest() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");


        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputEmail("rizkytest@gmail.com");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();


        registerPage.getBtnRegister().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPage.dialogInputPhoneNumber));

        assertTrue(registerPage.getDialogInputPhoneNumber().isDisplayed());
    }

    @Test
    public void registerNumberLessThan8DigitTest() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");


        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputEmail("rizkytest@gmail.com");
        driver.hideKeyboard();
        registerPage.inputPhoneNumber("08");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();


        registerPage.getBtnRegister().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPage.errorMsgPhoneNumberCorrectly));

        assertTrue(registerPage.getErrorMsgPhoneNumberCorrectly().isDisplayed());
    }

    @Test
    public void registerNumberMoreThan16DigitTest() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");


        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputEmail("rizkytest@gmail.com");
        driver.hideKeyboard();
        registerPage.inputPhoneNumber("087883849227232323232323");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();


        registerPage.getBtnRegister().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPage.errorMsgPhoneNumberCorrectly));

        assertTrue(registerPage.getErrorMsgPhoneNumberCorrectly().isDisplayed());
    }

    @Test
    public void registerEmailWithoutAtTest() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");


        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputEmail("rizkytestgmail.com");
        driver.hideKeyboard();
        registerPage.inputPhoneNumber("087883849227");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();


        registerPage.getBtnRegister().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPage.errorMsgEmailFormat));

        assertTrue(registerPage.getErrorMsgEmailFormat().isDisplayed());
    }

    @Test
    public void registerEmailWithoutDomainTest() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");


        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputEmail("rizkytest@gmail");
        driver.hideKeyboard();
        registerPage.inputPhoneNumber("087883849227");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();


        registerPage.getBtnRegister().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPage.errorMsgEmailFormat));

        assertTrue(registerPage.getErrorMsgEmailFormat().isDisplayed());
    }

    @Test
    public void registerWithoutCheckTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");


        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputEmail("rizkytest@gmail.com");
        driver.hideKeyboard();
        registerPage.inputPhoneNumber("087883849227");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");


        registerPage.getBtnRegister().click();
        Thread.sleep(5000);
        assertTrue(registerPage.getBtnRegister().isDisplayed());
    }

    @Test
    public void registerWithDuplicatePhoneTest() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");


        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputEmail("rizkytest@gmail.com");
        driver.hideKeyboard();
        registerPage.inputPhoneNumber("87883849227");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();


        registerPage.getBtnRegister().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPage.dialogPhoneOrEmailAlreadyRegistered));

        assertTrue(registerPage.getDialogPhoneOrEmailALreadyRegistered().isDisplayed());
    }

    @Test
    public void registerWithDuplicateEmailTest() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");


        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputEmail("test@gmail.com");
        driver.hideKeyboard();
        registerPage.inputPhoneNumber("087883849227");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();


        registerPage.getBtnRegister().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPage.dialogPhoneOrEmailAlreadyRegistered));

        assertTrue(registerPage.getDialogPhoneOrEmailALreadyRegistered().isDisplayed());
    }

    @Test
    public void regsiterWithFalseOtp() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");

        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputEmail("rizkytest@gmail.com");
        driver.hideKeyboard();
        registerPage.inputPhoneNumber("08788384922789");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();


        registerPage.getBtnRegister().click();

        // input false otp
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("000000");

        // assertion false otp is showed
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPage.dialogOtpFailed));
        assertTrue(registerPage.getDialogOtpFailed().isDisplayed());
    }

    @Test
    public void registerWithFalsePinConfirmation() {
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        RegisterPage.CalendarDialog calendarDialog = registerPage.new CalendarDialog(17, "August", 1945);

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister)
        );
        System.out.println("done waiting login page");

        loginPage.clickBtnRegister();

        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.visibilityOfElementLocated(registerPage.onBoardingBanner)
        );
        System.out.println("done waiting register page");

        registerPage.inputFullName("Rizky Test");
        driver.hideKeyboard();

        registerPage.getBtnDOB().click();
        System.out.println("btndob click");

        calendarDialog.inputDOB();
        registerPage.inputEmail("rizkytest@gmail.com");
        driver.hideKeyboard();
        registerPage.inputPhoneNumber("087883849227");
        driver.hideKeyboard();
        registerPage.chooseGender("Female");
        registerPage.checkTermAgreement();

        // otp automation
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

        registerPage.getBtnRegister().click();

        driver.openNotifications();

        // waiting for the otp notification to show up
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.TextView[@resource-id='android:id/text' and contains(@text, 'verification code')]")
        ));

        String otpText = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text' and contains(@text, 'verification code')]")).getText();
        String otpCode = otpText.replaceAll("[^0-9]", "");

        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(otpCode);

        // create PIN
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        String pin = "123456";
        typeSomething(pin);


        // re input pin
        System.out.println("re input pin");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.EditText")).click();

        Actions action = new Actions(driver);
        action.sendKeys("000000").perform();

        // assertion dialog confirmation pin is showed
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(registerPage.dialogNewPinNotMatch));
        assertTrue(registerPage.getDialogNewPinNotMatch().isDisplayed());
    }



    public static void openApp() throws MalformedURLException {
        driver = AppiumDriverUtil.getAndroidDriverQA();

        System.out.println("App Started");
    }
}
