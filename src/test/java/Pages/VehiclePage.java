package Pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class VehiclePage {
    private final AndroidDriver driver;

    public By btnAddVehicle = By.xpath("//android.widget.Button[@content-desc='Add Vehicle']");

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

        public By btnAddVehicle = By.xpath("//android.widget.Button[@content-desc='Add Vehicle']");
        public By etVehicleModel = By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[1]");
        public By etVehiclePlate = By.xpath("//android.widget.FrameLayout[@resource-id='android:id/content']/android.widget.FrameLayout/android.widget.FrameLayout/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText[2]");
        public By errorMsgModel = By.xpath("(//android.view.View[@content-desc='Please write your vehicle model'])[1]");
        public By errorMsgPlate = By.xpath("(//android.view.View[@content-desc='Please write your vehicle model'])[2]");
        public By checkbox = By.xpath("//android.widget.CheckBox");
        public By validationAgreement = By.xpath("//android.view.View[@content-desc='Vehicle Please check our terms & agreement']");
        public By btnValidationOk = By.xpath("//android.widget.Button[@content-desc='OK']");
        public By validationSucces = By.xpath("//android.view.View[@content-desc='Update Success']");
        public By validationInvalidPin = By.xpath("//android.view.View[@content-desc='Authentication\n55 - Invalid PIN']");
        public By validationVehicleAlreadyRegistered = By.xpath("//android.view.View[@content-desc='Vehicle\nVehicle already registered']");
        public By validationMemberVehicleAlreadyRegistered = By.xpath("//android.view.View[@content-desc='Authentication\n75102 - Member vehicle already registered']");

        public WebElement getBtnAddVehicle() {
            return driver.findElement(btnAddVehicle);
        }

        public WebElement getEtVehicleModel() {
            return driver.findElement(etVehicleModel);
        }

        public WebElement getEtVehiclePlate() {
            return driver.findElement(etVehiclePlate);
        }

        public WebElement getErrorMsgModel() {
            return driver.findElement(errorMsgModel);
        }

        public WebElement getErrorMsgPlate() {
            return driver.findElement(errorMsgPlate);
        }

        public WebElement getCheckbox() {
            return driver.findElement(checkbox);
        }

        public WebElement getValidationAgreement() {
            return driver.findElement(validationAgreement);
        }

        public WebElement getValidationInvalidPin() {
            return driver.findElement(validationInvalidPin);
        }

        public WebElement getValidationVehicleAlreadyRegsitered() {
            return driver.findElement(this.validationVehicleAlreadyRegistered);
        }

        public WebElement getValidationMemberVehicleALreadyRegistered() {
            return driver.findElement(this.validationMemberVehicleAlreadyRegistered);
        }
        public WebElement getBtnOk() {
            return driver.findElement(btnValidationOk);
        }
        public WebElement getValidationSucces() {
            return  driver.findElement(validationSucces);
        }


    }
}
