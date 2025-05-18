package Pages;

import Bots.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainNavigationMenu {
    WebDriver driver;
    public MainNavigationMenu(WebDriver driver){this.driver=driver;}

    //Locators
    By allMenu                       = By.xpath("//a[@id='nav-hamburger-menu']");
    By seeAllOption                  = By.xpath("//a/div[text()='See all']");
    By videoGamesOption              = By.xpath("//a/div[text()='Video Games']");
    By allVideoGamesOption           = By.xpath("//a[text()='All Video Games']");
    By navToYourAmazonTab            = By.xpath("//a[@id='nav-your-amazon']");
    By keepShoppingTab               = By.xpath("//a[text()='Keep Shopping For']");
    By electronicsTab                = By.xpath("//a[text()='Electronics']");
    By mobileTab                     = By.xpath("//a[text()='Mobile Phones']");
    By homeTab                       = By.xpath("//a[text()='Home']");
    By fashionTab                    = By.xpath("//a[text()='Fashion']");
    By cartNav                       = By.id("nav-cart");

    //Methods
    public void clickingAllmenu(){
        Bot.clicking(driver,allMenu);
    }


    public void clickingSeeAll(){
        Bot.clicking(driver,seeAllOption);
    }


    public void clickingVideoGamesOptions(){
        Bot.clicking(driver,videoGamesOption);
    }

    public void clickingAllVideoGames(){
        Bot.clicking(driver,allVideoGamesOption);
    }

    public void jsClickingAllVideoGames(){
        Bot.jsClick(driver,allVideoGamesOption);
    }

    public void scrollingDownToVideoGamesOption(){
        Bot.scrollDown(driver,videoGamesOption);
    }

    public void goToCart(){
        Bot.clicking(driver,cartNav);
    }

    public void goBack(){
        Bot.goBack();
    }

}
