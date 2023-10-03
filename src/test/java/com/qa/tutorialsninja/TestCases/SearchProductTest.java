package com.qa.tutorialsninja.TestCases;

import com.qa.tutorialsninja.Pages.LandingPage;
import com.qa.tutorialsninja.Pages.SearchPage;
import com.qa.tutorialsninja.TestBase.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

public class SearchProductTest extends TestBase {
    public WebDriver driver;

    public SearchProductTest() throws Exception{
        super();
    }

    @BeforeMethod
    public void searchProductSetup(){
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
    }

    @Test(priority=1)
    public void verifySearchWithValidProduct(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.enterProductDetailsInSearchTextBoxField(dataprop.getProperty("validProduct"));
        //driver.findElement(By.name("search")).sendKeys(dataprop.getProperty("validProduct"));
        landingPage.clickOnSearchButton();
        //driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        SearchPage searchpage = new SearchPage(driver);
        Assert.assertTrue(searchpage.displayStatusOfValidProduct());
    }

    @Test(priority=2)
    public void verifySearchWithInvalidProduct(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.enterProductDetailsInSearchTextBoxField(dataprop.getProperty("invalidProduct"));
        //driver.findElement(By.name("search")).sendKeys(dataprop.getProperty("invalidProduct"));
        landingPage.clickOnSearchButton();
        //driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        SearchPage searchpage = new SearchPage(driver);
        Assert.assertTrue(searchpage.displayNoProductAvailableText());
    }

    @Test(priority=3)
    public void verifySearchWithNoProduct(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnSearchButton();
        //driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
        SearchPage searchpage = new SearchPage(driver);
        Assert.assertTrue(searchpage.displayNoProductAvailableText());
    }

    @AfterMethod
    public void searchProductTeardown(){
        driver.quit();
    }
}
