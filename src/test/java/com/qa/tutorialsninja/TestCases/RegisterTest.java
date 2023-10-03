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

    public RegisterTest() throws Exception{
        super();
    }


    @BeforeMethod
    public void registerSetup(){
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
        LandingPage landingpage = new LandingPage(driver);
        landingpage.clickOnMyAccount();
        landingpage.selectRegisterOption();
        //driver.findElement(By.linkText("My Account")).click();
        //driver.findElement(By.linkText("Register")).click();
    }

    @Test(priority = 1)
    public void registerAccountWithMandatoryDetails(){
        RegisterPage registerpage = new RegisterPage(driver);
        registerpage.enterFisrtName(dataprop.getProperty("firstname"));
        //driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
        registerpage.enterLastName(dataprop.getProperty("lastname"));
        //driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
        registerpage.enterEmail(Util.generateEmailWithDateTimeStamp());
        //driver.findElement(By.id("input-email")).sendKeys(Util.generateEmailWithDateTimeStamp());
        registerpage.enterTelephone(dataprop.getProperty("telephone"));
        //driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
        registerpage.enterPassword(prop.getProperty("validPassword"));
        //driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
        registerpage.enterPasswordConfirmation(prop.getProperty("validPassword"));
        //driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
        registerpage.clickPrivacyPolicyCheckBox();
        //driver.findElement(By.xpath("//input[@name = 'agree' and @value = '1']")).click();
        registerpage.clickOnContinueButton();
        //driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
        Assert.assertTrue(accountsuccesspage.validateDisplayStatusOfAccountCreationSuccessMessage());
    }

    @Test(priority = 2)
    public void registerAccountWithAllDetails(){
        RegisterPage registerpage = new RegisterPage(driver);
        registerpage.enterFisrtName(dataprop.getProperty("firstname"));
        //driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
        registerpage.enterLastName(dataprop.getProperty("lastname"));
        //driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
        registerpage.enterEmail(Util.generateEmailWithDateTimeStamp());
        //driver.findElement(By.id("input-email")).sendKeys(Util.generateEmailWithDateTimeStamp());
        registerpage.enterTelephone(dataprop.getProperty("telephone"));
        //driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
        registerpage.enterPassword(prop.getProperty("validPassword"));
        //driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
        registerpage.enterPasswordConfirmation(prop.getProperty("validPassword"));
       // driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
        registerpage.clickOnNewsletterSelectionButton();
        //driver.findElement(By.xpath("//input[@name= 'newsletter' and @value = '1']")).click();
        registerpage.clickPrivacyPolicyCheckBox();
        //driver.findElement(By.xpath("//input[@name = 'agree' and @value = '1']")).click();
        registerpage.clickOnContinueButton();
        //driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        AccountSuccessPage accountsuccesspage = new AccountSuccessPage(driver);
        Assert.assertTrue(accountsuccesspage.validateDisplayStatusOfAccountCreationSuccessMessage());
        //Assert.assertTrue(driver.findElement(By.cssSelector("div#content>:nth-child(2)")).isDisplayed());
    }

    @Test(priority = 3)
    public void registerAccountWithExistingEmail(){
        RegisterPage registerpage = new RegisterPage(driver);
        registerpage.enterFisrtName(dataprop.getProperty("firstname"));
        //driver.findElement(By.id("input-firstname")).sendKeys(dataprop.getProperty("firstname"));
        registerpage.enterLastName(dataprop.getProperty("lastname"));
        //driver.findElement(By.id("input-lastname")).sendKeys(dataprop.getProperty("lastname"));
        registerpage.enterEmail(prop.getProperty("validEmail"));
        //driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
        registerpage.enterTelephone(dataprop.getProperty("telephone"));
        //driver.findElement(By.id("input-telephone")).sendKeys(dataprop.getProperty("telephone"));
        registerpage.enterPassword(prop.getProperty("validPassword"));
        //driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
        registerpage.enterPasswordConfirmation(prop.getProperty("validPassword"));
        //driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
        registerpage.clickOnNewsletterSelectionButton();
        //driver.findElement(By.xpath("//input[@name= 'newsletter' and @value = '1']")).click();
        registerpage.clickPrivacyPolicyCheckBox();
        //driver.findElement(By.xpath("//input[@name = 'agree' and @value = '1']")).click();
        registerpage.clickOnContinueButton();
        //driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
        String actualWarningMessage = registerpage.retrieveDuplicateEmailWarningMessage();
        //String actualWarningMessage = driver.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
        String expectedWarningMessage = dataprop.getProperty("emailExistWarningMessage");
        Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage));
    }

    @Test(priority = 4)
    public void registerAccountWithNoDetails() {
        RegisterPage registerpage = new RegisterPage(driver);
        registerpage.clickOnContinueButton();
        //driver.findElement(By.cssSelector("input.btn.btn-primary")).click();

        String actualPrivacyPolicyMessage = registerpage.retrievePrivacyPolicyWarningMessage();
        //String actualPrivacyPolicyMessage = driver.findElement(By.xpath("//div[@class = 'alert alert-danger alert-dismissible']")).getText();
        String expectedPrivacyPolicyMessage = dataprop.getProperty("privacyPolicyWarningMessage");
        Assert.assertTrue(actualPrivacyPolicyMessage.contains(expectedPrivacyPolicyMessage));

        String actualFirstnameWarningMessage = registerpage.retrieveFirstNameWarningMessage();
        //String actualFirstnameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-firstname']/following-sibling::div[1]")).getText();
        String expectedFirstnameWarningMessage = dataprop.getProperty("firstNameWarningMessage");
        Assert.assertTrue(actualFirstnameWarningMessage.contains(expectedFirstnameWarningMessage));

        String actualLastnameWarningMessage = registerpage.retrieveLastNameWarningMessage();
        //String actualLastnameWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-lastname']/following-sibling::div[1]")).getText();
        String expectedLastnameWarningMessage = dataprop.getProperty("lastNameWarningMessage");
        Assert.assertTrue(actualLastnameWarningMessage.contains(expectedLastnameWarningMessage));

        String actualEmailWarningMessage = registerpage.retrieveEmailWarningMessage();
        //String actualEmailWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-email']/following-sibling::div[1]")).getText();
        String expectedEmailWarningMessage = dataprop.getProperty("emailWarningMessage");
        Assert.assertTrue(actualEmailWarningMessage.contains(expectedEmailWarningMessage));

        String actualTelephoneWarningMessage = registerpage.retrieveTelephoneWarningMessage();
        //String actualTelephoneWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-telephone']/following-sibling::div[1]")).getText();
        String expectedTelephoneWarningMessage = dataprop.getProperty("telephoneWarningMessage");
        Assert.assertTrue(actualTelephoneWarningMessage.contains(expectedTelephoneWarningMessage));

        String actualPasswordWarningMessage = registerpage.retrievePasswordWarningMessage();
        //String actualPasswordWarningMessage = driver.findElement(By.xpath("//input[@id = 'input-password']/following-sibling::div[1]")).getText();
        String expectedPasswordWarningMessage = dataprop.getProperty("passwordWarningMessage");
        Assert.assertTrue(actualPasswordWarningMessage.contains(expectedPasswordWarningMessage));
    }

    @AfterMethod
    public void registerTeardown(){
        driver.quit();
    }

}
