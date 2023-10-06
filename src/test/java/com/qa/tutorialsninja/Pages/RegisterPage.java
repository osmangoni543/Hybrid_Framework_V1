package com.qa.tutorialsninja.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {

    public WebDriver driver;

    @FindBy(id = "input-firstname")
    private WebElement firstNameTextBox;

    @FindBy(id = "input-lastname")
    private WebElement lastNameTextBox;

    @FindBy(id = "input-email")
    private WebElement emailTextBox;

    @FindBy(id = "input-telephone")
    private WebElement telephoneTextBox;

    @FindBy(id = "input-password")
    private WebElement passwordTextBox;

    @FindBy(id = "input-confirm")
    private WebElement confirmPasswordTextBox;

    @FindBy(xpath = "//input[@name = 'agree' and @value = '1']")
    private WebElement privacyPolicyButton;

    @FindBy(css = "input.btn.btn-primary")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@name= 'newsletter' and @value = '1']")
    private WebElement newsletterSelectionButton;

    @FindBy(xpath = "//div[@class = 'alert alert-danger alert-dismissible']")
    private WebElement duplicateEmailWarningMessage;

    @FindBy(xpath = "//div[@class = 'alert alert-danger alert-dismissible']")
    private WebElement privacyPolicyWarningMessage;

    @FindBy(xpath = "//input[@id = 'input-firstname']/following-sibling::div[1]")
    private WebElement firstNameWarningMessage;

    @FindBy(xpath = "//input[@id = 'input-lastname']/following-sibling::div[1]")
    private WebElement lastNameWarningMessage;

    @FindBy(xpath = "//input[@id = 'input-email']/following-sibling::div[1]")
    private WebElement emailWarningMessage;

    @FindBy(xpath = "//input[@id = 'input-telephone']/following-sibling::div[1]")
    private WebElement telephoneWarningMessage;

    @FindBy(xpath = "//input[@id = 'input-password']/following-sibling::div[1]")
    private WebElement passwordWarningMessage;





    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }





    public void enterFisrtName(String firstnameText){
        firstNameTextBox.sendKeys(firstnameText);
    }

    public void enterLastName(String lastnameText){
        lastNameTextBox.sendKeys(lastnameText);
    }

    public void enterEmail(String emailText){
        emailTextBox.sendKeys(emailText);
    }

    public void enterTelephone(String telephoneText){
        telephoneTextBox.sendKeys(telephoneText);
    }

    public void enterPassword(String passwordText){
        passwordTextBox.sendKeys(passwordText);
    }

    public void enterPasswordConfirmation(String confirmText){
        confirmPasswordTextBox.sendKeys(confirmText);
    }

    public void clickPrivacyPolicyCheckBox(){
        privacyPolicyButton.click();
    }

    public void clickOnContinueButton(){
        continueButton.click();
    }

    public void clickOnNewsletterSelectionButton(){
        newsletterSelectionButton.click();
    }

    public String retrieveDuplicateEmailWarningMessage(){
        String duplicateEmailText = duplicateEmailWarningMessage.getText();
        return duplicateEmailText;
    }

    public String retrievePrivacyPolicyWarningMessage(){
        String privacypolicywarningmessage = privacyPolicyWarningMessage.getText();
        return privacypolicywarningmessage;
    }

    public String retrieveFirstNameWarningMessage(){
        String firstnamewarningmessage = firstNameWarningMessage.getText();
        return firstnamewarningmessage;
    }

    public String retrieveLastNameWarningMessage(){
        String lastnamewarningmessage = lastNameWarningMessage.getText();
        return lastnamewarningmessage;
    }

    public String retrieveEmailWarningMessage(){
        String emailwarningmessage = emailWarningMessage.getText();
        return emailwarningmessage;
    }

    public String retrieveTelephoneWarningMessage(){
        String telephonewarningmessage = telephoneWarningMessage.getText();
        return telephonewarningmessage;
    }

    public String retrievePasswordWarningMessage(){
        String passwordwarningmessage = passwordWarningMessage.getText();
        return passwordwarningmessage;
    }

    public AccountSuccessPage registerPageMandatoryDetails(String firstnameText, String lastnameText, String emailText, String telephoneText, String passwordText, String confirmText){
        firstNameTextBox.sendKeys(firstnameText);
        lastNameTextBox.sendKeys(lastnameText);
        emailTextBox.sendKeys(emailText);
        telephoneTextBox.sendKeys(telephoneText);
        passwordTextBox.sendKeys(passwordText);
        confirmPasswordTextBox.sendKeys(confirmText);
        privacyPolicyButton.click();
        continueButton.click();
        return new AccountSuccessPage(driver);
    }

    public AccountSuccessPage registerPageAllDetails(String firstnameText, String lastnameText, String emailText, String telephoneText, String passwordText, String confirmText){
        firstNameTextBox.sendKeys(firstnameText);
        lastNameTextBox.sendKeys(lastnameText);
        emailTextBox.sendKeys(emailText);
        telephoneTextBox.sendKeys(telephoneText);
        passwordTextBox.sendKeys(passwordText);
        confirmPasswordTextBox.sendKeys(confirmText);
        newsletterSelectionButton.click();
        privacyPolicyButton.click();
        continueButton.click();
        return new AccountSuccessPage(driver);
    }

    public boolean retrieveAllWarningMessagesStatus(String expectedPrivacyPolicyWarningMessage, String expectedFirstNameWarningMessage,
                                                    String expectedLastNameWarningMessage, String expectedEmailWarningMessage,
                                                    String expectedTelephoneWarningMessage, String expectedPasswordWarningMessage)
    {
        boolean privacyPolicyWarningStatus= privacyPolicyWarningMessage.getText().contains(expectedPrivacyPolicyWarningMessage);
        boolean firstNameWarningStatus = firstNameWarningMessage.getText().contains(expectedFirstNameWarningMessage);
        boolean lastNameWarningStatus= lastNameWarningMessage.getText().contains(expectedLastNameWarningMessage);
        boolean emailWarningStatus= emailWarningMessage.getText().contains(expectedEmailWarningMessage);
        boolean telephoneWarningStatus= telephoneWarningMessage.getText().contains(expectedTelephoneWarningMessage);
        boolean passwordWarningStatus = passwordWarningMessage.getText().contains(expectedPasswordWarningMessage);
        return  privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus &&
                telephoneWarningStatus && passwordWarningStatus;
    }

}
