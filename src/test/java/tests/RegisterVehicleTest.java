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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterVehicleTest {

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
    public void addVehicleTest() throws InterruptedException {
        VehiclePage vehiclePage = new VehiclePage(driver);
        VehiclePage.AddVehiclePage addVehiclePage = vehiclePage.new AddVehiclePage();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(myProfilePage.btnVehicle));
        myProfilePage.getBtnVehicle().click();

        vehiclePage.btnAddVehicle().click();

        addVehiclePage.getEtVehicleModel().click();
        addVehiclePage.getEtVehicleModel().sendKeys("Toyota Test");
        addVehiclePage.getEtVehiclePlate().click();
        addVehiclePage.getEtVehiclePlate().sendKeys("B123XYZ");
        addVehiclePage.getCheckbox().click();
        addVehiclePage.getBtnAddVehicle().click();

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText")));

        inputPIn();

    }

    @Test
    public void addVehicleWithBlankModel() {
        VehiclePage vehiclePage = new VehiclePage(driver);
        VehiclePage.AddVehiclePage addVehiclePage = vehiclePage.new AddVehiclePage();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(myProfilePage.btnVehicle));
        myProfilePage.getBtnVehicle().click();

        vehiclePage.btnAddVehicle().click();

        addVehiclePage.getEtVehiclePlate().click();
        addVehiclePage.getEtVehiclePlate().sendKeys("B123XYZ");
        addVehiclePage.getCheckbox().click();
        addVehiclePage.getBtnAddVehicle().click();

        assertTrue(addVehiclePage.getErrorMsgModel().isDisplayed());

    }

    @Test
    public void addVehicleWithBlankPlate() {
        VehiclePage vehiclePage = new VehiclePage(driver);
        VehiclePage.AddVehiclePage addVehiclePage = vehiclePage.new AddVehiclePage();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(myProfilePage.btnVehicle));
        myProfilePage.getBtnVehicle().click();

        vehiclePage.btnAddVehicle().click();

        addVehiclePage.getEtVehicleModel().click();
        addVehiclePage.getEtVehicleModel().sendKeys("Toyota Avanza");
        addVehiclePage.getCheckbox().click();
        addVehiclePage.getBtnAddVehicle().click();

        assertTrue(addVehiclePage.getErrorMsgModel().isDisplayed());
    }

    @Test
    public void addVehicleWithSameModelAndPlate() {
        VehiclePage vehiclePage = new VehiclePage(driver);
        VehiclePage.AddVehiclePage addVehiclePage = vehiclePage.new AddVehiclePage();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(myProfilePage.btnVehicle));
        myProfilePage.getBtnVehicle().click();

        vehiclePage.btnAddVehicle().click();

        addVehiclePage.getEtVehicleModel().click();
        addVehiclePage.getEtVehicleModel().sendKeys("Tes");
        addVehiclePage.getEtVehiclePlate().click();
        addVehiclePage.getEtVehiclePlate().sendKeys("Tes124");
        addVehiclePage.getCheckbox().click();
        addVehiclePage.getBtnAddVehicle().click();

        assertTrue(addVehiclePage.getValidationVehicleAlreadyRegsitered().isDisplayed());
    }

    @Test
      void addVehicleWithSamePlate() {
        VehiclePage vehiclePage = new VehiclePage(driver);
        VehiclePage.AddVehiclePage addVehiclePage = vehiclePage.new AddVehiclePage();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(myProfilePage.btnVehicle));
        myProfilePage.getBtnVehicle().click();

        vehiclePage.btnAddVehicle().click();

        addVehiclePage.getEtVehicleModel().click();
        addVehiclePage.getEtVehicleModel().sendKeys("Tes Model");
        addVehiclePage.getEtVehiclePlate().click();
        addVehiclePage.getEtVehiclePlate().sendKeys("Tes124");
        addVehiclePage.getCheckbox().click();
        addVehiclePage.getBtnAddVehicle().click();

        assertTrue(addVehiclePage.getValidationVehicleAlreadyRegsitered().isDisplayed());
    }

    @Test
    public void addVehicleWithSameDataCaseInsensitive() {
        VehiclePage vehiclePage = new VehiclePage(driver);
        VehiclePage.AddVehiclePage addVehiclePage = vehiclePage.new AddVehiclePage();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);
        Actions action = new Actions(driver);

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(loginPage.loginBanner));

        inputPIn();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(homePage.btnProfile));

        homePage.getBtnProfile().click();
        homePage.getBtnAccountSetting().click();

        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOfElementLocated(myProfilePage.btnVehicle));
        myProfilePage.getBtnVehicle().click();

        vehiclePage.btnAddVehicle().click();

        addVehiclePage.getEtVehicleModel().click();
        addVehiclePage.getEtVehicleModel().sendKeys("TES");
        addVehiclePage.getEtVehiclePlate().click();
        addVehiclePage.getEtVehiclePlate().sendKeys("TES124");
        addVehiclePage.getCheckbox().click();
        addVehiclePage.getBtnAddVehicle().click();

        inputPIn();

        assertTrue(addVehiclePage.getValidationMemberVehicleALreadyRegistered().isDisplayed());

    }

    @Test
    public void addVehicleWithFalsePin() {

    }

    @Test
    public void addVehicleWithFalsePin3Times() {

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

    public static void inputFalsePIn() {
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
        driver.pressKey(new KeyEvent(AndroidKey.DIGIT_5));
        System.out.println("key 5 pressed");
    }
}
