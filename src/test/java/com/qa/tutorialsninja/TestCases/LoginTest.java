package com.qa.tutorialsninja.TestCases;

import com.qa.tutorialsninja.Pages.AccountPage;
import com.qa.tutorialsninja.Pages.LandingPage;
import com.qa.tutorialsninja.Pages.LoginPage;
import com.qa.tutorialsninja.TestBase.TestBase;
import com.qa.tutorialsninja.TestData.ExcelCodeData;
import com.qa.tutorialsninja.Utilities.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {

    public WebDriver driver;
    public LandingPage landingpage;
    public LoginPage loginpage;
    public AccountPage accountpage;

    public LoginTest() throws Exception{
        super();
    }

    @BeforeMethod
    public void loginSetup(){
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
        landingpage = new LandingPage(driver);
        landingpage.clickOnMyAccount();
        loginpage = landingpage.selectLoginOption();
        // driver.findElement(By.linkText("My Account")).click();
        // driver.findElement(By.linkText("Login")).click();
    }

    @Test(priority = 1, dataProvider = "TN", dataProviderClass = ExcelCodeData.class)
    public void verifyLoginWithValidCredentials(String excelEmail, String excelPassword){
        //LoginPage loginpage = new LoginPage(driver);
        loginpage.enterEmail(excelEmail);
        //driver.findElement(By.id("input-email")).sendKeys(excelEmail);
        loginpage.enterPassword(excelPassword);
        //driver.findElement(By.id("input-password")).sendKeys(excelPassword);
        accountpage = loginpage.clickOnLoginButton();
        //driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        //AccountPage accountpage = new AccountPage(driver);
        Assert.assertTrue(accountpage.displayStatusOfEditAccountButton());
        //Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
    }

    @Test(priority = 2)
    public void verifyLoginWithValidEmailInvalidPassword(){
        //LoginPage loginpage = new LoginPage(driver);
        loginpage.enterEmail(prop.getProperty("validEmail"));
        //driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
        loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
        //driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
        loginpage.clickOnLoginButton();
        //driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        String actualWarningMessage = loginpage.retrieveInvalidLoginWarningMessage();
        //String actualWarningMessage = driver.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
        String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailValidPassword(){
        //LoginPage loginpage = new LoginPage(driver);
        loginpage.enterEmail(Util.generateEmailWithDateTimeStamp());
        //driver.findElement(By.id("input-email")).sendKeys(Util.generateEmailWithDateTimeStamp());
        loginpage.enterPassword(prop.getProperty("validPassword"));
        //driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
        loginpage.clickOnLoginButton();
        //driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        String actualWarningMessage = loginpage.retrieveInvalidLoginWarningMessage();
        //String actualWarningMessage = driver.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
        String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
    }

    @Test(priority = 4)
    public void verifyLoginWithInvalidCredentials() {
        //LoginPage loginpage = new LoginPage(driver);
        loginpage.enterEmail(Util.generateEmailWithDateTimeStamp());
        //driver.findElement(By.id("input-email")).sendKeys(Util.generateEmailWithDateTimeStamp());
        loginpage.enterPassword(dataprop.getProperty("invalidPassword"));
        //driver.findElement(By.id("input-password")).sendKeys(dataprop.getProperty("invalidPassword"));
        loginpage.clickOnLoginButton();
        //driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        String actualWarningMessage = loginpage.retrieveInvalidLoginWarningMessage();
        //String actualWarningMessage = driver.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
        String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
    }

    @Test(priority = 5)
    public void verifyLoginWithNoCredentials(){
        //LoginPage loginpage = new LoginPage(driver);
        loginpage.clickOnLoginButton();
        //driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        String actualWarningMessage = loginpage.retrieveInvalidLoginWarningMessage();
        //String actualWarningMessage = driver.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
        String expectedWarningMessage = dataprop.getProperty("loginWarningMessage");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
    }

    @AfterMethod
    public void LoginTeardown(){
        driver.quit();
    }

}
