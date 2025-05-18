package Pages;

import Bots.Bot;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<String, Integer> addingProductsWithPriceCondition(WebDriver driver, int maxPrice) throws InterruptedException {
        return Bot.addFirstPageValidProducts(driver,maxPrice);
    }

    public void goToCartpage(){Bot.goToCart(driver);}

}
