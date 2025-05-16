package Tests;

import Pages.HomePage;
import Pages.SignInPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    String baseURL = "https://www.amazon.eg/?language=en_AE";
    HomePage homePage;
    SignInPage signIn;

    @BeforeMethod
    public void setUp(){
        driver   = new ChromeDriver();
        homePage = new HomePage(driver);
        signIn   = new SignInPage(driver);
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @Epic("Login")
    @Feature("Valid Login")
    @Test(testName = "valid login with valid password and valid password" )
    public void validLogin(){
        homePage.clickingDirectLogin();
        signIn.setMobileOrEmailTextBox("1212812127");
        signIn.clickingContinue();
        signIn.setPasswordTextBox("Ahmed1234");
        signIn.clickingSignIn();
    }

    @AfterMethod
    public void tearDown(){driver.quit();}

}
