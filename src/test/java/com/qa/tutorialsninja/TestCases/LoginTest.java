package com.qa.tutorialsninja.TestCases;

import com.qa.tutorialsninja.Pages.AccountPage;
import com.qa.tutorialsninja.Pages.LandingPage;
import com.qa.tutorialsninja.Pages.LoginPage;
import com.qa.tutorialsninja.TestBase.TestBase;
import com.qa.tutorialsninja.TestData.ExcelCodeData;
import com.qa.tutorialsninja.Utilities.Util;
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
        loginpage = landingpage.navigateToLoginPage();
    }

    @Test(priority = 1, dataProvider = "TN", dataProviderClass = ExcelCodeData.class)
    public void verifyLoginWithValidCredentials(String excelEmail, String excelPassword){
        accountpage = loginpage.navigateToAccountPage(excelEmail, excelPassword);
        Assert.assertTrue(accountpage.displayStatusOfEditAccountButton());
    }

    @Test(priority = 2)
    public void verifyLoginWithValidEmailInvalidPassword(){
        loginpage.navigateToAccountPage(prop.getProperty("validEmail"), dataprop.getProperty("invalidPassword"));
        Assert.assertTrue(loginpage.retrieveInvalidLoginWarningMessage().contains(dataprop.getProperty("loginWarningMessage")));
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailValidPassword(){
        loginpage.navigateToAccountPage(Util.generateEmailWithDateTimeStamp(), prop.getProperty("validPassword"));
        Assert.assertTrue(loginpage.retrieveInvalidLoginWarningMessage().contains(dataprop.getProperty("loginWarningMessage")));
    }

    @Test(priority = 4)
    public void verifyLoginWithInvalidCredentials() {
        loginpage.navigateToAccountPage(Util.generateEmailWithDateTimeStamp(), dataprop.getProperty("invalidPassword"));
        Assert.assertTrue(loginpage.retrieveInvalidLoginWarningMessage().contains(dataprop.getProperty("loginWarningMessage")));
    }

    @Test(priority = 5)
    public void verifyLoginWithNoCredentials(){
        loginpage.clickOnLoginButton();
        Assert.assertTrue(loginpage.retrieveInvalidLoginWarningMessage().contains(dataprop.getProperty("loginWarningMessage")));
    }

    @AfterMethod
    public void LoginTeardown(){
        driver.quit();
    }

}
