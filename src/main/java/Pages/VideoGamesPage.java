package Pages;

import Bots.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VideoGamesPage {
    WebDriver driver;
    public VideoGamesPage(WebDriver driver){this.driver=driver;}

    //Locators
    By sortByPrice          = By.id("a-autoid-0-announce");
    By lowToHighOption      = By.xpath("//a[text()='Price: Low to High']");
    By highToLowOption      = By.xpath("//a[text()='Price: High to Low']");

    //Methods
    public void clickingSoreByPrice(){
        Bot.clicking(driver,sortByPrice);
    }

    public void clickingLowToHigh(){
        Bot.clicking(driver,lowToHighOption);
    }

    public void clickingHighToLow(){
        Bot.clicking(driver,highToLowOption);
    }
}
