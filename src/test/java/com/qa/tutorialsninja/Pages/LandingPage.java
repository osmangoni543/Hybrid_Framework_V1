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

    public void selectRegisterOption(){
        registerOption.click();
    }

    public void enterProductDetailsInSearchTextBoxField(String productText){
        searchTextBox.sendKeys(productText);
    }

    public void clickOnSearchButton(){
        searchButton.click();
    }

}
