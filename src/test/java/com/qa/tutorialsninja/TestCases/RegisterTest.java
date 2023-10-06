package com.qa.tutorialsninja.TestCases;

import com.qa.tutorialsninja.Pages.AccountSuccessPage;
import com.qa.tutorialsninja.Pages.LandingPage;
import com.qa.tutorialsninja.Pages.RegisterPage;
import com.qa.tutorialsninja.TestBase.TestBase;
import com.qa.tutorialsninja.Utilities.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegisterTest extends TestBase {

    public WebDriver driver;
    public RegisterPage registerpage;
    public LandingPage landingpage;
    public AccountSuccessPage accountsuccesspage;

    public RegisterTest() throws Exception{
        super();
    }


    @BeforeMethod
    public void registerSetup(){
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
        landingpage = new LandingPage(driver);
        registerpage = landingpage.selectRegisterOption();
    }

    @Test(priority = 1)
    public void registerAccountWithMandatoryDetails(){
        accountsuccesspage = registerpage.registerPageMandatoryDetails(dataprop.getProperty("firstname"),dataprop.getProperty("lastname"),
                                                  Util.generateEmailWithDateTimeStamp(), dataprop.getProperty("telephone"),
                                                  prop.getProperty("validPassword"), (prop.getProperty("validPassword")));
        Assert.assertTrue(accountsuccesspage.validateDisplayStatusOfAccountCreationSuccessMessage());
    }

    @Test(priority = 2)
    public void registerAccountWithAllDetails(){
        accountsuccesspage = registerpage.registerPageMandatoryDetails(dataprop.getProperty("firstname"),dataprop.getProperty("lastname"),
                                                                 Util.generateEmailWithDateTimeStamp(), dataprop.getProperty("telephone"),
                                                                 prop.getProperty("validPassword"), (prop.getProperty("validPassword")));
        Assert.assertTrue(accountsuccesspage.validateDisplayStatusOfAccountCreationSuccessMessage());
    }

    @Test(priority = 3)
    public void registerAccountWithExistingEmail(){
        registerpage.registerPageMandatoryDetails(dataprop.getProperty("firstname"),dataprop.getProperty("lastname"),
                                                  prop.getProperty("validEmail"), dataprop.getProperty("telephone"),
                                                  prop.getProperty("validPassword"), (prop.getProperty("validPassword")));
        Assert.assertTrue(registerpage.retrieveDuplicateEmailWarningMessage().contains(dataprop.getProperty("emailExistWarningMessage")));
    }

    @Test(priority = 4)
    public void registerAccountWithNoDetails() {
        registerpage.clickOnContinueButton();
        Assert.assertTrue(registerpage.retrieveAllWarningMessagesStatus(
                dataprop.getProperty("privacyPolicyWarningMessage"), dataprop.getProperty("firstNameWarningMessage"),
                dataprop.getProperty("lastNameWarningMessage"), dataprop.getProperty("emailWarningMessage"),
                dataprop.getProperty("telephoneWarningMessage"), dataprop.getProperty("passwordWarningMessage")));
    }

    @AfterMethod
    public void registerTeardown(){
        driver.quit();
    }

}
