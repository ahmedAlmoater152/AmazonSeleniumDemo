package Pages;

import Bots.Bot;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    public CartPage(WebDriver driver){this.driver=driver;}

    //Locators
    By shoppinCartHeader            = By.id("sc-active-items-header");
    By selectedItemsNumber          = By.id("sc-subtotal-label-activecart");
    By totalItemsPrice              = By.id("sc-subtotal-amount-activecart");
    By proceedToBuy                 = By.xpath("//input[@name='proceedToRetailCheckout']");

    //Methods
    public void isShoppingCartHeaderDisplayed(){
        Bot.isVisible(driver,shoppinCartHeader);
    }

    public int getTotalSelectedItemsNumber(){
        String itemsText = driver.findElement(selectedItemsNumber).getText();
        int itemCount = Integer.parseInt(itemsText.replaceAll("[^0-9]", ""));
        return itemCount;
    }

    public int getTotalSelectedItemsTotalPrice(){
        String priceText = driver.findElement(totalItemsPrice).getText();
        String numericPrice = priceText.replaceAll("[^0-9]", "");
        int totalPrice = Integer.parseInt(numericPrice);
        return totalPrice = totalPrice / 100;
    }

    public void clickingProceedToBuy(){
        Bot.clicking(driver,proceedToBuy);
    }

    public void clearCart(WebDriver driver) throws InterruptedException {By deleteButtonsLocator = By.xpath("//input[@value='Delete']");

        while (true) {
            List<WebElement> deleteButtons = driver.findElements(deleteButtonsLocator);

            if (deleteButtons.isEmpty()) {
                System.out.println("Cart is empty — no more products to delete.");
                break;
            }

            WebElement deleteBtn = deleteButtons.get(0);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteBtn);

            try {
                wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
                deleteBtn.click();
            } catch (ElementClickInterceptedException e) {
                System.out.println("Element click intercepted. Retrying with JS click...");
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);
            }

            wait.until(ExpectedConditions.stalenessOf(deleteBtn));

            Thread.sleep(1000); // optional delay
        }
    }


}
