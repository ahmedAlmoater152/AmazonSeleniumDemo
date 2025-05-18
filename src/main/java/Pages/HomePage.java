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
    By languageNavigator             = By.id("icp-nav-flyout");
    By englishLanguage               = By.xpath("(//a[@lang='en-AE'])[1]");
    By logOutFromAccount             = By.id("nav-item-signout");



    //Methods
    public void clickingDirectLogin(){
        Bot.clicking(driver,logindirectButton);
    }

    public void clickingSigninOptioninHamburgerMenu(){
        Bot.clicking(driver,hamburgerMenu);
        Bot.isVisible(driver,singinOptioninHamburgerMenu);
        Bot.clicking(driver,singinOptioninHamburgerMenu);
    }

    public void changeLanguageToEnglish(){
        Bot.isVisible(driver,languageNavigator);
        Bot.hoverAndClick(driver,languageNavigator,englishLanguage);
    }

    public void hoverAndClickLogout(){
        Bot.scrollDown(driver,logindirectButton);
        Bot.isVisible(driver,logindirectButton);
        Bot.hoverAndClick(driver,logindirectButton,logOutFromAccount);
    }

}
