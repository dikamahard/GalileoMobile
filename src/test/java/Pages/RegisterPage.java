package Pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegisterPage {

    private final AndroidDriver driver;
    public By etFullName = By.xpath("//android.view.View[contains(@content-desc, 'Welcome') and contains(@content-desc, 'Join Us.')]/android.widget.EditText[1]");
    public By btnDOB = By.xpath("//android.widget.ImageView");
    public By etEmail = By.xpath("//android.view.View[contains(@content-desc, 'Welcome') and contains(@content-desc, 'Join Us.')]/android.widget.EditText[2]");
    public By etPhoneNumber = By.xpath("//android.view.View[contains(@content-desc, 'Welcome') and contains(@content-desc, 'Join Us.')]/android.widget.EditText[3]");
    public By btnGender = By.xpath("//android.widget.Button[contains(@content-desc, 'Gender')]");
    public By checkTermAgreement = By.xpath("//android.widget.CheckBox");
    public By btnRegister = By.xpath("//android.widget.Button[@content-desc='Register']");
    public By onBoardingBanner = By.xpath("//android.view.View[contains(@content-desc, 'Welcome') and contains(@content-desc, 'Join Us.')]");

    public RegisterPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public WebElement getOnBoardingBanner() {
        return driver.findElement(onBoardingBanner);
    }
    public WebElement getEtFullName() {
        return driver.findElement(this.etFullName);
    }

    public WebElement getBtnDOB() {
        return driver.findElement(this.btnDOB);
    }

    public WebElement getEtEmail() {
        return driver.findElement(this.etEmail);
    }

    public WebElement getEtPhoneNumber() {
        return  driver.findElement(this.etPhoneNumber);
    }

    public WebElement getBtnGender() {
        return driver.findElement(this.btnGender);
    }

    public WebElement getCheckTermAgreement() {
        return driver.findElement(this.checkTermAgreement);
    }

    public WebElement getBtnRegister() {
        return driver.findElement(this.btnRegister);
    }

    public void inputFullName(String fullName) {
        this.getEtFullName().click();
        this.getEtFullName().sendKeys(fullName);
    }

    public void inputEmail(String email) {
        this.getEtEmail().click();
        this.getEtEmail().sendKeys(email);
    }

    public void inputPhoneNumber(String phoneNum) {
        System.out.println(this.getEtPhoneNumber().isDisplayed());
        this.getEtPhoneNumber().click();
        System.out.println("first click");
        try {
            if (this.getEtPhoneNumber().isDisplayed()) {
                System.out.println("displayed");
                this.getEtPhoneNumber().sendKeys(phoneNum);
            }else {
                System.out.println("not displayed");
                driver.navigate().back();
                System.out.println("back click");
                this.getEtPhoneNumber().sendKeys(phoneNum);
            }
        }catch (Exception e) {
            System.out.println(e);
            System.out.println("not displayed");
            driver.navigate().back();
            System.out.println("back click");
            this.getEtPhoneNumber().sendKeys(phoneNum);
        }finally {
            driver.hideKeyboard();
        }

    }

    public void chooseGender(String gender) {
        this.getBtnGender().click();
        driver.findElement(By.xpath("//android.view.View[@content-desc='"+gender+"']")).click();

    }

    public void checkTermAgreement() {
        this.getCheckTermAgreement().click();
    }

    public class CalendarDialog {

        int date;
        String month;
        int year;

        public By btnSelectYear = By.xpath("//android.widget.Button[@content-desc='Select year']");
        public By btnOk = By.xpath("//android.widget.Button[@content-desc='OK']");
        public By btnCancel = By.xpath("//android.widget.Button[@content-desc='CANCEL']");
        public By btnPencil = By.xpath("//android.view.View[contains(@content-desc, 'SELECT DATE')]/android.widget.Button[1]");
        public By btnPencil11 = By.xpath("//android.widget.Button[contains(@content-desc, 'Select date')]/android.widget.Button[1]");
        public CalendarDialog(int date, String month, int year) {
            this.date = date;
            this.month = month;
            this.year = year;
        }

//        public WebElement getChoosenYear() {
//            return driver.findElement(By.xpath("//android.widget.Button[@content-desc='"+this.year+"']"));
//        }

        public WebElement getBtnSelectYear() {
            return driver.findElement(btnSelectYear);
        }

        public WebElement getBtnOk() {
            return driver.findElement(btnOk);
        }

        public WebElement getBtnPencil() {
            return driver.findElement(btnPencil);
        }

        public WebElement getBtnPencil11() {
            return driver.findElement(btnPencil11);
        }

        public void scrollToChoosenYear() {
            driver.findElement(AppiumBy.androidUIAutomator(        "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"" + this.year + "\"))"
            )).click();
        }

        public void inputDOB() { //mm/dd/yyyy format
            String mm = "";

            switch (this.month){
                case "January":
                case "jan":
                    mm = "01";
                    break;

                case "Febuary":
                    mm = "02";
                    break;

                case "March":
                    mm = "03";
                    break;

                case "April":
                    mm = "04";
                    break;

                case "May":
                    mm = "05";
                    break;

                case "June":
                    mm = "06";
                    break;

                case "July":
                    mm = "07";
                    break;

                case "August":
                    mm = "08";
                    break;

                case "September":
                    mm = "09";
                    break;

                case "October":
                    mm = "10";
                    break;

                case "November":
                    mm = "11";
                    break;

                case "December":
                    mm = "12";
                    break;
            }

//            this.getBtnPencil().click();
            this.getBtnPencil11().click();
            driver.findElement(By.xpath("//android.widget.EditText")).click();
            driver.findElement(By.xpath("//android.widget.EditText")).clear();
            driver.findElement(By.xpath("//android.widget.EditText")).sendKeys(mm +"/"+ this.date +"/"+ this.year);
            this.getBtnOk().click();
        }






    }
}
