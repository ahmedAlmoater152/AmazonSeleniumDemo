package Bots;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class Bot {
    private static WebDriver driver;

    static FluentWait<WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(10))
            .pollingEvery(Duration.ofMillis(250))
            .ignoring(NoSuchElementException.class)
            .ignoring(NotFoundException.class)
            .ignoring(ElementNotInteractableException.class)
            .ignoring(AssertionError.class)
            .ignoring(StaleElementReferenceException.class);

    public static void clicking(WebDriver driver, By locator){
        wait.until(f ->{
            driver.findElement(locator).click();
            return true;
        } );
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
            return true; // Element became visible within 10 seconds
        } catch (TimeoutException e) {
            return false; // Element not visible within 10 seconds
        }
    }

}
