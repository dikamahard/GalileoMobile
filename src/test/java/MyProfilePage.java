import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyProfilePage {

    private final AndroidDriver driver;


    By btnVehicle = By.xpath("//android.widget.ImageView[@content-desc='Vehicle']");

    public MyProfilePage(AndroidDriver driver) {
        this.driver = driver;

    }

    public WebElement btnVehicle() {
        return driver.findElement(btnVehicle);
    }



}
