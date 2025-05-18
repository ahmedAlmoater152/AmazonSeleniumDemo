package Tests;

import Bots.Bot;
import Pages.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class VideoGamesWithFiltersAndSortByPriceTest {

    private WebDriver driver;
    String baseURL = "https://www.amazon.eg/-/en/";
    HomePage homePage;
    SignInPage signIn;
    MainNavigationMenu main;
    FiltersBar filtersBar;
    VideoGamesPage videoGamesPage;
    WebDriverWait wait;
    CartPage cart;
    CheckoutPage checkout;

    @BeforeMethod
    public void setUp(){
        driver           = new ChromeDriver();
        homePage         = new HomePage(driver);
        signIn           = new SignInPage(driver);
        main             = new MainNavigationMenu(driver);
        filtersBar       = new FiltersBar(driver);
        videoGamesPage   = new VideoGamesPage(driver);
        wait             = new WebDriverWait(driver, Duration.ofSeconds(10));
        cart             = new CartPage(driver);
        checkout         = new CheckoutPage(driver);
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @Epic("VideoGamesShopping")
    @Feature("Video Games shopping With Filters")
    @Test(testName = "Video games with new and free shipping Filters And SortByPrice Test" )
    public void VideoGamesShoppingTest() throws InterruptedException {
        homePage.clickingDirectLogin();
        signIn.setMobileOrEmailTextBox("1212812127");
        signIn.selectEgyptCode();
        signIn.clickingContinue();
        signIn.setPasswordTextBox("Ahmed1234");
        signIn.clickingSignIn();
        main.goToCart();
        cart.clearCart(driver);
        main.clickingAllmenu();
        main.clickingSeeAll();
        main.scrollingDownToVideoGamesOption();
        main.clickingVideoGamesOptions();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
        main.jsClickingAllVideoGames();
        filtersBar.selectFreeShippingFilter();
        filtersBar.selectNewCondition();
        videoGamesPage.clickingSoreByPrice();
        videoGamesPage.clickingHighToLow();
        Map<String, Integer> result = videoGamesPage.addingProductsWithPriceCondition(driver,15000);
        videoGamesPage.goToCartpage();
        int totalAdded = result.get("totalAdded");
        int totalPrice = result.get("totalPrice");
        SoftAssert softAssert = new SoftAssert();
        System.out.println("Verifying total added items matches cart's total selected items...");
        softAssert.assertEquals(cart.getTotalSelectedItemsNumber(), totalAdded,
                "Mismatch in total number of selected items.");
        System.out.println("Verifying total price matches cart's total selected price...");
        softAssert.assertEquals(cart.getTotalSelectedItemsTotalPrice(), totalPrice,
                "Mismatch in total price of selected items.");
        cart.clickingProceedToBuy();
        checkout.isHeaderDisplayed();
        checkout.clickingChangeAddress();
        checkout.clickingEditAddress();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        checkout.isPopupHeaderdisplayed();
        checkout.clearName();
        checkout.clearMobileNumber();
        checkout.clearStreetName();
        checkout.clearBuildingNum();
        checkout.clearCity();
        checkout.setFullName();
        checkout.setMobileNumber();
        checkout.setStreetName();
        checkout.setBuildingNum();
        checkout.setAddressCity();
        checkout.clickingUseThisAddress();
//      homePage.hoverAndClickLogout();
    }

    @AfterMethod
    public void tearDown(){driver.quit();}

}
