package Pages;

import Bots.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver){this.driver=driver;}

    //Locators
    By searchBar                     = By.xpath("//input[@id='twotabsearchtextbox']");
    By submitSearchButton            = By.xpath("//input[@id = 'nav-search-submit-button']");
    By searchCategoryDropdown        = By.xpath("//select[@id='searchDropdownBox']");
    By logindirectButton             = By.xpath("//div[@id='nav-link-accountList']");
    By hamburgerMenu                 = By.id("nav-hamburger-menu");
    By singinOptioninHamburgerMenu   = By.xpath("//li/a[contains(text(),'Sign in')]");


    //Methods
    public void clickingDirectLogin(){
        Bot.clicking(driver,logindirectButton);
    }

    public void clickingSigninOptioninHamburgerMenu(){
        Bot.clicking(driver,hamburgerMenu);
        Bot.isVisible(driver,singinOptioninHamburgerMenu);
        Bot.clicking(driver,singinOptioninHamburgerMenu);
    }

}
