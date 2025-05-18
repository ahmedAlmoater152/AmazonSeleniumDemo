package Bots;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

public class Bot {
    private static WebDriver driver;

    static FluentWait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(12))
            .pollingEvery(Duration.ofMillis(250))
            .ignoring(NoSuchElementException.class)
            .ignoring(NotFoundException.class)
            .ignoring(ElementNotInteractableException.class)
            .ignoring(AssertionError.class)
            .ignoring(ElementClickInterceptedException.class)
            .ignoring(StaleElementReferenceException.class);

    public static void clicking(WebDriver driver, By locator){
        wait.until(f ->{
            driver.findElement(locator).click();
            return true;
        } );
    }

    public static void jsClick(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }


    public static void enterText(WebDriver driver, By locator, String text){
        wait.until(f->{
            driver.findElement(locator).sendKeys(text);
            return true;
        });
    }

    public static void isVisible(WebDriver driver, By locator){
        wait.until(f->{
            driver.findElement(locator).isDisplayed();
            return true;
        });
    }

    public static Boolean boolIsVisible(WebDriver driver, By locator){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public static void hoverAndClick(WebDriver driver, By hoverLocator, By clickLocator) {
        wait.until(f -> {
            WebElement hoverElement = driver.findElement(hoverLocator);
            Actions actions = new Actions(driver);
            actions.moveToElement(hoverElement).perform();

            WebElement clickElement = driver.findElement(clickLocator);
            clickElement.click();

            return true;
        });
    }

    public static void scrollDown(WebDriver driver,By tillElement){

        WebElement element = driver.findElement(tillElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);

    }

    public static void selectFromDropDown(WebDriver driver, By dropDownElement, String text){
        wait.until(f->{
            WebElement dropdown = driver.findElement(dropDownElement);
            Select select = new Select(dropdown);
            select.deselectByVisibleText(text);
            return true;
        });
    }

    public static Map<String, Integer> addFirstPageValidProducts(WebDriver driver, int maxPrice) throws InterruptedException {

        Map<String, Integer> result = new HashMap<>();
        int totalAdded = 0;
        int totalPrice = 0;
        boolean productsAdded = false;

        while (!productsAdded) {
            List<WebElement> priceElements = driver.findElements(By.xpath("//div[@data-cy='price-recipe']//span[@class='a-price-whole']"));

            List<WebElement> validProducts = new ArrayList<>();

            for (WebElement priceElement : priceElements) {
                String priceText = priceElement.getText().replace(",", "").trim();

                if (!priceText.matches("\\d+")) continue;

                int price = Integer.parseInt(priceText);

                if (price < maxPrice) {
                    WebElement productContainer = priceElement.findElement(
                            By.xpath("./ancestor::div[contains(@data-component-type,'s-search-result')]"));
                    validProducts.add(productContainer);
                }
            }

            if (validProducts.size() > 0) {
                for (WebElement product : validProducts) {
                    try {

                        WebElement priceElement = product.findElement(By.xpath(".//div[@data-cy='price-recipe']//span[@class='a-price-whole']"));
                        String priceText = priceElement.getText().replace(",", "").trim();
                        int price = Integer.parseInt(priceText);


                        WebElement addToCartButton = product.findElement(By.xpath(".//button[@name='submit.addToCart']"));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
                        addToCartButton.click();

                        // Handle popup if found
                        try {
                            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                            WebDriverWait popupWait = new WebDriverWait(driver, Duration.ofSeconds(5));
                            WebElement popupHeader = popupWait.until(ExpectedConditions.visibilityOfElementLocated(
                                    By.xpath("//h2/span[contains(text(),'Sorry, there was a problem')]")));
                            System.out.println("Popup detected after clicking Add to Cart.");
                            popupHeader.click();
                            WebElement closeBtn = driver.findElement(By.xpath("//button[@aria-label='Close']"));
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);


                            // Try to close it safely
//                            try {
//                                WebElement closeBtn = driver.findElement(By.xpath("//button[@aria-label='Close']"));
////                              popupWait.until(ExpectedConditions.elementToBeClickable(closeBtn)).click();
//                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
//
//                                Thread.sleep(1000); // Allow time for modal to close
//                            }
//                                 catch (Exception closeFail) {
//                                System.out.println("Close button not clickable or missing: " + closeFail.getMessage());
//                            }


                        } catch (TimeoutException popupNotFound) {

                        }

                        totalAdded++;
                        totalPrice += price;

                        Thread.sleep(1000);

                    } catch (Exception e) {
                        System.out.println("Failed to add product: " + e.getMessage());
                    }
                }
                productsAdded = true; // Stop after first valid product page
                break;
            } else {
                // Try to go to next page
                try {
                    WebElement nextButton = driver.findElement(By.xpath("//a[contains(@class,'s-pagination-next')]"));
                    if (nextButton.isDisplayed() && nextButton.isEnabled()) {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", nextButton);
                        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
                        Thread.sleep(2000);
                    } else {
                        System.out.println("Next button is not clickable or visible. Ending search.");
                        break;
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("No next page found. Ending search.");
                    break;
                }

            }
        }

        result.put("totalAdded", totalAdded);
        result.put("totalPrice", totalPrice);
        return result;
    }

    public static void goToCart(WebDriver driver){
        WebElement cartIcon = driver.findElement(By.xpath("//a[@id='nav-cart']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", cartIcon);
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartIcon);

    }

    public static void clearText(WebDriver driver, By locator){
        wait.until(f->{
            driver.findElement(locator).clear();
            return true;
        });
    }

    public static void goBack(){
        driver.navigate().back();
    }

}
