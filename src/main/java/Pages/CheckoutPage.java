package Pages;

import Bots.Bot;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    WebDriver driver;
    Faker faker = new Faker();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    public CheckoutPage(WebDriver driver){this.driver=driver;}


    //Locators
    By header                   = By.id("nav-checkout-title-header-text");
    By addNewDeliveryAddress    = By.className("a-button-text");
    By fullName                 = By.id("address-ui-widgets-enterAddressFullName");
    By mobileNumber             = By.id("address-ui-widgets-enterAddressPhoneNumber");
    By streetName               = By.id("address-ui-widgets-enterAddressLine1");
    By buildingNum              = By.id("address-ui-widgets-enter-building-name-or-number");
    By addressCity              = By.id("address-ui-widgets-enterAddressCity");
    By district                 = By.id("address-ui-widgets-enterAddressDistrictOrCounty");
    By changeAddress            = By.xpath("//a[contains(text(), 'Change')]");
    By editAddress              = By.id("edit-address-desktop-tango-sasp-0");
    By popupHeader              = By.xpath("//h2[text()='Edit your shipping address']");
    By useThisAddress           = By.xpath("//input[@data-csa-c-slot-id='address-ui-widgets-continue-edit-address-btn-bottom']");
    By cashPayment              = By.id("pp-aVMi8m-81");


    //Methods
    public void isHeaderDisplayed(){
        Bot.isVisible(driver,header);
    }

    public void clickingAddDeliveryAddress(){
        Bot.clicking(driver,addNewDeliveryAddress);
    }

    public void setFullName(){
        String fakeName = faker.name().fullName();
        Bot.enterText(driver,fullName,fakeName);
    }

    public void setMobileNumber(){
        String randomDigits = faker.number().digits(8);
        String fakeMobileNumber = "10" + randomDigits;
        Bot.enterText(driver,mobileNumber,fakeMobileNumber);
    }

    public void setStreetName(){
        String fakeStreetName = faker.address().streetName();
        Bot.enterText(driver,streetName,fakeStreetName);
    }

    public void setBuildingNum(){
        String buildingNumber = faker.address().buildingNumber();
        Bot.enterText(driver,buildingNum,buildingNumber);
    }

    public void setAddressCity(){
        Bot.enterText(driver,addressCity,"15th of May City");
    }

    public void setDistrict(){
        Bot.enterText(driver,district,"15 May City");
    }

    public void clickingChangeAddress(){
        Bot.clicking(driver,changeAddress);
    }

    public void clickingEditAddress(){
        Bot.clicking(driver,editAddress);
    }

    public void isPopupHeaderdisplayed(){
        Bot.isVisible(driver,popupHeader);
    }

    public void clickingUseThisAddress(){
        Bot.clicking(driver,useThisAddress);
    }

    public void clearName(){
        Bot.clearText(driver,fullName);
    }

    public void clearMobileNumber(){
        Bot.clearText(driver,mobileNumber);
    }

    public void clearStreetName(){
        Bot.clearText(driver,streetName);
    }

    public void clearBuildingNum(){
        Bot.clearText(driver,buildingNum);
    }

    public void clearCity(){
        Bot.clearText(driver,addressCity);
    }

    public void clearDistrict(){
        Bot.clearText(driver,district);
    }

    public void enableAndSelectCashPayment() {
        WebElement cashPaymentOption = driver.findElement(cashPayment);
        js.executeScript("arguments[0].scrollIntoView(true);", cashPaymentOption);
        try {
            if (!cashPaymentOption.isEnabled()) {
                js.executeScript("arguments[0].removeAttribute('disabled');", cashPaymentOption);
                System.out.println("Cash payment option enabled via JavaScript.");
            }

            cashPaymentOption.click();
            System.out.println("Cash payment option selected.");

        } catch (Exception e) {
            System.out.println("Failed to enable or select Cash payment option: " + e.getMessage());
        }
    }


}
