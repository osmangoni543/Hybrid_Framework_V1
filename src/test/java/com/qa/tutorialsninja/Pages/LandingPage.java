package com.qa.tutorialsninja.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {

    public WebDriver driver;

    @FindBy(linkText = "My Account")
    private WebElement MyAccountDropdown;

    @FindBy(linkText = "Login")
    private WebElement loginOption;

    @FindBy(linkText = "Register")
    private WebElement registerOption;

    @FindBy(name = "search")
    private WebElement searchTextBox;

    @FindBy(css = "button.btn.btn-default.btn-lg")
    private WebElement searchButton;


    public LandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOnMyAccount(){
        MyAccountDropdown.click();
    }

    public LoginPage selectLoginOption(){
        loginOption.click();
        return new LoginPage(driver);
    }

    public RegisterPage selectRegisterOption(){
        MyAccountDropdown.click();
        registerOption.click();
        return new RegisterPage(driver);
    }

    public void enterProductDetailsInSearchTextBoxField(String productText){
        searchTextBox.sendKeys(productText);
    }

    public SearchPage clickOnSearchButton(){
        searchButton.click();
        return new SearchPage(driver);
    }

    public LoginPage navigateToLoginPage(){
        MyAccountDropdown.click();
        loginOption.click();
        return new LoginPage(driver);
    }

    public SearchPage navigateToSearchPage(String productText){
        searchTextBox.sendKeys(productText);
        searchButton.click();
        return new SearchPage(driver);
    }

}
