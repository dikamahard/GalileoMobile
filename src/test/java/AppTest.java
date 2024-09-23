import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    static AndroidDriver driver;
//    HomePage homePage;
//    MyProfilePage myProfilePage;
//    VehiclePage vehiclePage;

    public static void main(String[] args) throws MalformedURLException {

//        openApp();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        inputPIn();
//        driver.findElement(By.xpath("//android.widget.Button[@resource-id='android:id/button2']")).click();
//
//        driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[2]")).click();
//
//        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Account Settings']")).click();
//
//        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='Vehicle']")).click();




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
        homePage.btnProfile().click();
        homePage.btnAccountSetting().click();
        myProfilePage.btnVehicle().click();
        vehiclePage.btnAddVehicle().click();

        VehiclePage.AddVehiclePage addVehiclePage = vehiclePage.new AddVehiclePage();
        addVehiclePage.etVehicleModel().click();
        addVehiclePage.etVehicleModel().sendKeys("Avanza");
//        addVehiclePage.etVehiclePlate().click();
//        addVehiclePage.etVehiclePlate().sendKeys("B123A");
        addVehiclePage.checkbox().click();
        addVehiclePage.btnAddVehicle().click();

        System.out.println(addVehiclePage.validation1().getAttribute("content-desc"));
        String expectedEmpty = "Please write your vehicle model";
        assertEquals(expectedEmpty,addVehiclePage.validation1().getAttribute("content-desc"));

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
        homePage.btnProfile().click();
        homePage.btnAccountSetting().click();
        myProfilePage.btnVehicle().click();
        vehiclePage.btnAddVehicle().click();

        VehiclePage.AddVehiclePage addVehiclePage = vehiclePage.new AddVehiclePage();
        addVehiclePage.etVehicleModel().click();
        addVehiclePage.etVehicleModel().sendKeys(vehicleModel);
        addVehiclePage.etVehiclePlate().click();
        addVehiclePage.etVehiclePlate().sendKeys(vehiclePlate);
        addVehiclePage.checkbox().click();
        addVehiclePage.btnAddVehicle().click();
        inputPIn();

        String expectedSucces = "Update Success";
        System.out.println(addVehiclePage.validationSucces().getAttribute("content-desc"));
        addVehiclePage.btnValidationOk().click();

        String expectedAdded = "Avanza\nB123ABC";
        System.out.println(vehiclePage.addedVehicle(vehicleModel+"\n"+vehiclePlate).getAttribute("content-desc"));
        assertEquals(expectedAdded, vehiclePage.addedVehicle(vehicleModel + "\n" + vehiclePlate).getAttribute("content-desc"));

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
