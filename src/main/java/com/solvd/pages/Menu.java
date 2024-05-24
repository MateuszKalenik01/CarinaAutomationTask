package com.solvd.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Menu extends AbstractUIObject {

    @FindBy(id = "loginusername")
    private ExtendedWebElement usernameField;

    @FindBy(id = "loginpassword")
    private ExtendedWebElement passwordField;

    @FindBy(css = "button[onclick='logIn()']")
    private ExtendedWebElement loginSubmitButton;

    @FindBy(id = "login2")
    private ExtendedWebElement loginButton;

    @FindBy(id = "logout2")
    private ExtendedWebElement logoutButton;

    @FindBy(id = "nameofuser")
    private ExtendedWebElement welcomeMessage;

    @FindBy(id = "cartur")
    private ExtendedWebElement cartButton;

    @FindBy(id = "signin2")
    private ExtendedWebElement signUpButton;

    @FindBy(css = ".nav-link[href='index.html']")
    private ExtendedWebElement homeButton;

    @FindBy(css = ".nav-link[data-target='#exampleModal']")
    private ExtendedWebElement contactButton;

    @FindBy(css = ".nav-link[data-target='#videoModal']")
    private ExtendedWebElement aboutUsButton;

    private static final Logger LOGGER = LoggerFactory.getLogger(Menu.class);

    public Menu(WebDriver driver) {
        super(driver);
    }

    private boolean isDisplayed(ExtendedWebElement element) {
        boolean displayed = element.isElementPresent();
        LOGGER.info("Element: {} is displayed: {}", element.toString(), displayed);
        return displayed;
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void clickSignUpButton() {
        signUpButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public void clickCartButton() {
        cartButton.click();
    }

    public void enterUsername(String username) {
        usernameField.type(username);
    }

    public void enterPassword(String password) {
        passwordField.type(password);
    }

    public void submitLogin() {
        loginSubmitButton.click();
    }

    public void clickHomeButton() {
        homeButton.click();
    }

    public void clickContactButton() {
        contactButton.click();
    }

    public void clickAboutUsButton() {
        aboutUsButton.click();
    }

    public boolean isUserLoggedIn(String username) {
        return welcomeMessage.getText().contains("Welcome " + username);
    }

    public boolean isUserLoggedOut() {
        return isDisplayed(loginButton) && isDisplayed(signUpButton);
    }

    public void logIn(String username, String password) {
        clickLoginButton();
        enterUsername(username);
        enterPassword(password);
        submitLogin();
    }
}
