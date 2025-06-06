package Tests;

import Pages.*;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    String baseURL = "https://www.amazon.eg/-/en/";
    HomePage homePage;
    SignInPage signIn;
    MainNavigationMenu main;
    FiltersBar filtersBar;
    VideoGamesPage videoGamesPage;
    WebDriverWait wait;


    @BeforeMethod
    public void setUp(){
        driver           = new ChromeDriver();
        homePage         = new HomePage(driver);
        signIn           = new SignInPage(driver);
        main             = new MainNavigationMenu(driver);
        filtersBar       = new FiltersBar(driver);
        videoGamesPage   = new VideoGamesPage(driver);
        wait             = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @Epic("Login")
    @Feature("Valid Login")
    @Test(testName = "valid login with valid password and valid password" )
    public void validLogin(){
        homePage.clickingDirectLogin();
        signIn.setMobileOrEmailTextBox("1212812127");
        signIn.selectEgyptCode();
        signIn.clickingContinue();
        signIn.setPasswordTextBox("Ahmed1234");
        signIn.clickingSignIn();
        homePage.hoverAndClickLogout();
    }

    @AfterMethod
    public void tearDown(){driver.quit();}

}
