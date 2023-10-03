package com.qa.tutorialsninja.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    public WebDriver driver;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.caption>:nth-child(1)>a")
    private WebElement validProduct;

    @FindBy(xpath = "//input[@id = 'button-search']/following-sibling::p[1]")
    private WebElement noProductAvailableDisplayText;

    public boolean displayStatusOfValidProduct(){
        boolean displaystatusofvalidproduct = validProduct.isDisplayed();
        return displaystatusofvalidproduct;
    }

    public boolean displayNoProductAvailableText(){
        boolean dispaynoproductavailabletext = noProductAvailableDisplayText.isDisplayed();
        return dispaynoproductavailabletext;
    }

}
