package Pages;

import Bots.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignInPage {
    WebDriver driver;

    public SignInPage(WebDriver driver){this.driver=driver;}

    //Locators
    By mobileOrEmailTextBox      = By.id("ap_email_login");
    By continueButton            = By.xpath("//input[@type='submit']");
    By passwordTextBox           = By.id("ap_password");
    By signInButton              = By.id("signInSubmit");
    By countryCodedropDown       = By.xpath("//span[@class='a-dropdown-prompt']");
    By egyptCode                 = By.xpath("//li/a[text() = 'Egypt ']");

    //Methods
    public void setMobileOrEmailTextBox(String text){
        Bot.enterText(driver,mobileOrEmailTextBox,text);
    }

    public void clickingContinue(){
        Bot.clicking(driver,continueButton);
    }

    public void setPasswordTextBox(String password){
        Bot.enterText(driver,passwordTextBox,password);
    }

    public void clickingSignIn(){
        Bot.clicking(driver,signInButton);
    }

    public void selectEgyptCode(){
        driver.findElement(countryCodedropDown).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement egyptOption = wait.until(ExpectedConditions.elementToBeClickable(egyptCode));
        egyptOption.click();
    }
}
