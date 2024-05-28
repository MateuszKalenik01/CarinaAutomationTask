package com.solvd.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderMenu extends AbstractUIObject {

    @FindBy(css = ".nav-link[href='index.html']")
    private ExtendedWebElement homeButton;

    @FindBy(id = "cartur")
    private ExtendedWebElement cartButton;

    @FindBy(id = "logout2")
    private ExtendedWebElement logoutButton;

    @FindBy(id = "login2")
    private ExtendedWebElement loginButton;

    @FindBy(id = "loginusername")
    private ExtendedWebElement usernameField;

    @FindBy(id = "loginpassword")
    private ExtendedWebElement passwordField;

    @FindBy(css = "button[onclick='logIn()']")
    private ExtendedWebElement loginSubmitButton;
    @FindBy(id = "signin2")
    private ExtendedWebElement signUpButton;

    @FindBy(id = "sign-username")
    private ExtendedWebElement signUpUsernameField;

    @FindBy(id = "sign-password")
    private ExtendedWebElement signUpPasswordField;

    @FindBy(css = "button[onclick='register()']")
    private ExtendedWebElement signUpSubmitButton;
    @FindBy(css = "div.modal.fade.show")
    private ExtendedWebElement alertModal;
    public HeaderMenu(WebDriver driver) {
        super(driver);
    }

    public void clickHomeButton() {
        homeButton.click();
    }
    public void clickLoginButton(){
        loginButton.click();
    }

    public CartPage clickCartButton() {
        cartButton.click();
        return new CartPage(driver);
    }

    public void clickLogoutButton() {
        logoutButton.waitUntil(ExpectedConditions.elementToBeClickable(logoutButton.getElement()), 10);
        logoutButton.click();
    }

    public void logIn(String username, String password) {
        clickLoginButton();
        usernameField.waitUntil(ExpectedConditions.elementToBeClickable(usernameField.getElement()), 10);
        usernameField.type(username);
        passwordField.type(password);
        loginSubmitButton.click();
    }

    public boolean isUserLoggedIn() {

        return logoutButton.isElementPresent();
    }

    public boolean isUserLoggedOut() {
        return loginButton.isElementPresent();
    }
    public void clickSignUpButton() {
        signUpButton.click();
    }

    public void enterSignUpUsername(String username) {
        signUpUsernameField.waitUntil(ExpectedConditions.elementToBeClickable(signUpUsernameField.getElement()), 10);
        signUpUsernameField.type(username);
    }

    public void enterSignUpPassword(String password) {
        signUpPasswordField.waitUntil(ExpectedConditions.elementToBeClickable(signUpPasswordField.getElement()), 10);
        signUpPasswordField.type(password);
    }

    public void submitSignUp() {
        signUpSubmitButton.click();
    }
    public boolean isSignUpSuccessful() {
        try {
            waitUntil(ExpectedConditions.alertIsPresent(), 30);
            Alert jsAlert = getDriver().switchTo().alert();
            String alertText = jsAlert.getText();
            jsAlert.accept();
            return alertText.contains("Sign up successful.");
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
