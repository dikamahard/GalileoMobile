import Pages.*;
import Utils.AppiumDriverUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    static AndroidDriver driver;
//    Page.HomePage homePage;
//    Page.MyProfilePage myProfilePage;
//    Page.VehiclePage vehiclePage;

    @Test
    public void sample() {
        System.out.println("Sample Test");
    }


    @Test
    public void test1() throws MalformedURLException {

        openApp();

        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        VehiclePage vehiclePage = new VehiclePage(driver);

        //driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Welcome, Please Login']"))
        );
        inputPIn();
        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();
        myProfilePage.getBtnVehicle().click();
        vehiclePage.btnAddVehicle().click();

        VehiclePage.AddVehiclePage addVehiclePage = vehiclePage.new AddVehiclePage();
        addVehiclePage.getEtVehicleModel().click();
        addVehiclePage.getEtVehicleModel().sendKeys("Avanza");
//        addVehiclePage.etVehiclePlate().click();
//        addVehiclePage.etVehiclePlate().sendKeys("B123A");
        addVehiclePage.getCheckbox().click();
        addVehiclePage.getBtnAddVehicle().click();

        System.out.println(addVehiclePage.getErrorMsgModel().getAttribute("content-desc"));
        String expectedEmpty = "Please write your vehicle model";
        assertEquals(expectedEmpty,addVehiclePage.getEtVehicleModel().getAttribute("content-desc"));

    }

    @Test
    public void test2() throws MalformedURLException {

        String vehicleModel = "Avanza";
        String vehiclePlate = "B123ABC";
        openApp();

        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        VehiclePage vehiclePage = new VehiclePage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc='Welcome, Please Login']"))
        );
        inputPIn();
        new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile)
        );
        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();
        myProfilePage.getBtnVehicle().click();
        vehiclePage.btnAddVehicle().click();

        VehiclePage.AddVehiclePage addVehiclePage = vehiclePage.new AddVehiclePage();
        addVehiclePage.getEtVehicleModel().click();
        addVehiclePage.getEtVehicleModel().sendKeys(vehicleModel);
        addVehiclePage.getEtVehiclePlate().click();
        addVehiclePage.getEtVehiclePlate().sendKeys(vehiclePlate);
        addVehiclePage.getCheckbox().click();
        addVehiclePage.getBtnAddVehicle().click();
        inputPIn();

        String expectedSucces = "Update Success";
        System.out.println(addVehiclePage.getValidationSucces().getAttribute("content-desc"));
        addVehiclePage.getBtnOk().click();

        String expectedAdded = "Avanza\nB123ABC";
        System.out.println(vehiclePage.addedVehicle(vehicleModel+"\n"+vehiclePlate).getAttribute("content-desc"));
        assertEquals(expectedAdded, vehiclePage.addedVehicle(vehicleModel + "\n" + vehiclePlate).getAttribute("content-desc"));

    }

    @Test
    public void testRegister() throws MalformedURLException {
        openApp();

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

        registerPage.inputFullName("Test FullName");
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
        registerPage.inputEmail("test@gmail.com");
        driver.hideKeyboard();
//        registerPage.inputPhoneNumber("087883849227");
        registerPage.inputPhoneNumber("081119608545");
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
        //driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(pin);
        inputPIn();


        // re input pin
        //driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(pin);
        System.out.println("re input pin");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.EditText")).click();
        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("123456");

        inputPIn();
        Actions action = new Actions(driver);
        action.sendKeys("123456").perform();

        // assertion home page is showed

    }

    @Test
    public void signInTest() throws MalformedURLException {
        openApp();

        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
//        loginPage.getEtPhoneorEmail().sendKeys("081119608545");
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

        // input PIN
        String pin = "123456";
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
        //driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(pin);
        //inputPIn();
        Actions action = new Actions(driver);
        action.sendKeys("123456").perform();

        // assertion home page is showed.
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnHome));
        assertTrue(homePage.getBtnHome().isDisplayed());

    }

    @Test
    public void loginTest() throws MalformedURLException {
        openApp();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));
//        driver.findElement(By.xpath("//android.widget.EditText")).click();
//        driver.findElement(By.xpath("//android.widget.EditText")).sendKeys("123456");

        Actions action = new Actions(driver);
        action.sendKeys("123456").perform();
        //inputPIn();

        // assertion home page is showed.
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnHome));
        assertTrue(homePage.getBtnHome().isDisplayed());

        driver.terminateApp("com.pvs.project.galileo");
        driver.quit();
    }

    @Test
    public void loginWithBlankDataTest() throws MalformedURLException {
        openApp();

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.btnRegister));

        loginPage.clickBtnSignIn();

        assertTrue(loginPage.getErrorMsgBlankData().isDisplayed());

        driver.terminateApp("com.pvs.project.galileo");
        driver.quit();
    }

    @Test
    public void loginWithUnregisteredEmailTest() throws MalformedURLException {

        openApp();

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("lala@gmail.com");
        loginPage.clickBtnSignIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.dialogMemberNotFound));

        assertTrue(loginPage.getDialogMemberNotFound().isDisplayed());

        driver.terminateApp("com.pvs.project.galileo");
        driver.quit();

    }

    @Test
    public void loginWithUnregisteredNumberTest() throws MalformedURLException {

        openApp();

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("08132686752");
        loginPage.clickBtnSignIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.dialogMemberNotFound));

        assertTrue(loginPage.getDialogMemberNotFound().isDisplayed());

        driver.terminateApp("com.pvs.project.galileo");
        driver.quit();
    }

    @Test
    public void loginWithNumberLessThan8DigitTest() throws MalformedURLException {
        openApp();

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("9737298");
        loginPage.clickBtnSignIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.errorMsgWrongFormatNumber));

        assertTrue(loginPage.getErrorMsgWrongFormatNumber().isDisplayed());

        driver.terminateApp("com.pvs.project.galileo");
        driver.quit();
    }

    @Test
    public void loginWithNumberMoreThan20Digit() throws MalformedURLException {
        openApp();

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("08126371628316");
        loginPage.clickBtnSignIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.dialogMemberNotFound));

        assertTrue(loginPage.getErrorMsgWrongFormatNumber().isDisplayed());

        driver.terminateApp("com.pvs.project.galileo");
        driver.quit();
    }

    @Test
    public void loginWithEmailWithoutAdd() throws MalformedURLException {
        openApp();

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("ichaputrisya.gmail.com");
        loginPage.clickBtnSignIn();

        assertTrue(loginPage.getErrorMsgWrongFormatEmail().isDisplayed());

        driver.terminateApp("com.pvs.project.galileo");
        driver.quit();
    }

    @Test
    public void loginWithEmailWithoutDomain() throws MalformedURLException {
        openApp();

        LoginPage loginPage = new LoginPage(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.onBoardingBanner));

        loginPage.getEtPhoneorEmail().click();
        loginPage.getEtPhoneorEmail().sendKeys("ichaputrisya@gmail");
        loginPage.clickBtnSignIn();

        assertTrue(loginPage.getErrorMsgWrongFormatEmail().isDisplayed());

        driver.terminateApp("com.pvs.project.galileo");
        driver.quit();
    }

    @Test
    public void testRunner() throws MalformedURLException {
        loginWithBlankDataTest();
        loginWithUnregisteredEmailTest();
        loginWithUnregisteredNumberTest();
        loginWithNumberLessThan8DigitTest();
        loginWithNumberMoreThan20Digit();
        loginWithEmailWithoutAdd();
        loginWithEmailWithoutDomain();
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

}

