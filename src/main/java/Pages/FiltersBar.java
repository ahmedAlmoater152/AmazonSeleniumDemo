package Pages;

import Bots.Bot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FiltersBar {
    WebDriver driver;
    public FiltersBar(WebDriver driver){this.driver=driver;}

    //Locators
    By freeShippingFilter    = By.xpath("(//span[text() = 'Free Shipping'])[2]");
    By newCondition          = By.xpath("//span[text() = 'New']");

    //Methods
    public void selectFreeShippingFilter(){
        Bot.clicking(driver,freeShippingFilter);
    }

    public void selectNewCondition(){
        Bot.clicking(driver,newCondition);
    }

}
