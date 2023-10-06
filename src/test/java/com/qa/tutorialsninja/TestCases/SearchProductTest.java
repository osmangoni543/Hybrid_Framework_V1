package com.qa.tutorialsninja.TestCases;

import com.qa.tutorialsninja.Pages.LandingPage;
import com.qa.tutorialsninja.Pages.SearchPage;
import com.qa.tutorialsninja.TestBase.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class SearchProductTest extends TestBase {
    public WebDriver driver;
    public LandingPage landingpage;
    public SearchPage searchpage;

    public SearchProductTest() throws Exception{
        super();
    }

    @BeforeMethod
    public void searchProductSetup(){
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
    }

    @Test(priority=1)
    public void verifySearchWithValidProduct(){
        landingpage = new LandingPage(driver);
        searchpage = landingpage.navigateToSearchPage(dataprop.getProperty("validProduct"));
        Assert.assertTrue(searchpage.displayStatusOfValidProduct());
    }

    @Test(priority=2)
    public void verifySearchWithInvalidProduct(){
        landingpage = new LandingPage(driver);
        searchpage = landingpage.navigateToSearchPage(dataprop.getProperty("invalidProduct"));
        Assert.assertTrue(searchpage.displayNoProductAvailableText());
    }

    @Test(priority=3)
    public void verifySearchWithNoProduct(){
        landingpage = new LandingPage(driver);
        searchpage = landingpage.clickOnSearchButton();
        Assert.assertTrue(searchpage.displayNoProductAvailableText());
    }

    @AfterMethod
    public void searchProductTeardown(){
        driver.quit();
    }
}
