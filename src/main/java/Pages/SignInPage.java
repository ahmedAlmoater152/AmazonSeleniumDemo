package Pages;

import Bots.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    WebDriver driver;

    public SignInPage(WebDriver driver){this.driver=driver;}

    //Locators
    By mobileOrEmailTextBox      = By.id("ap_email_login");
    By continueButton            = By.xpath("//input[@type='submit']");
    By passwordTextBox           = By.id("ap_password");
    By signInButton              = By.id("signInSubmit");

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
}
