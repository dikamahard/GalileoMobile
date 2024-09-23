import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VehiclePage {

    private final AndroidDriver driver;

    By btnAddVehicle = By.xpath("//android.widget.Button[@content-desc='Add Vehicle']");

    public VehiclePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement btnAddVehicle() {
        return driver.findElement(btnAddVehicle);
    }

    public WebElement addedVehicle(String vehicleData) {
        return driver.findElement(By.xpath("//android.widget.ImageView[@content-desc='"+vehicleData+"']"));
    }

    public class AddVehiclePage {
        By btnAddVehicle = By.xpath("//android.widget.Button[@content-desc='Add Vehicle']");
        By etVehicleModel = By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[1]");
        By etVehiclePlate = By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[2]");
        By validation1 = By.xpath("(//android.view.View[@content-desc='Please write your vehicle model'])[1]");
        By validation2 = By.xpath("(//android.view.View[@content-desc='Please write your vehicle model'])[2]");
        By checkbox = By.xpath("//android.widget.CheckBox");
        By validationAgreement = By.xpath("//android.view.View[@content-desc='Vehicle Please check our terms & agreement']");
        By btnValidationOk = By.xpath("//android.widget.Button[@content-desc='OK']");
        By validationSucces = By.xpath("//android.view.View[@content-desc='Update Success']");
        public WebElement btnAddVehicle() {
            return driver.findElement(btnAddVehicle);
        }

        public WebElement etVehicleModel() {
            return driver.findElement(etVehicleModel);
        }

        public WebElement etVehiclePlate() {
            return driver.findElement(etVehiclePlate);
        }

        public WebElement validation1() {
            return driver.findElement(validation1);
        }

        public WebElement validation3() {
            return driver.findElement(validation2);
        }

        public WebElement checkbox() {
            return driver.findElement(checkbox);
        }

        public WebElement validationAgreement() {
            return driver.findElement(validationAgreement);
        }

        public WebElement btnValidationOk() {
            return driver.findElement(btnValidationOk);
        }
        public WebElement validationSucces() {
            return  driver.findElement(validationSucces);
        }


    }
}
