package com.solvd.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MenuComponent {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "login2")
    private WebElement loginButton;

    @FindBy(id = "logout2")
    private WebElement logoutButton;

    @FindBy(id = "nameofuser")
    private WebElement welcomeMessage;

    @FindBy(id = "cartur")
    private WebElement cartButton;

    @FindBy(id = "signin2")
    private WebElement signUpButton;

    @FindBy(css = ".nav-link[href='index.html']")
    private WebElement homeButton;

    @FindBy(css = ".nav-link[data-target='#exampleModal']")
    private WebElement contactButton;

    @FindBy(css = ".nav-link[data-target='#videoModal']")
    private WebElement aboutUsButton;

    public MenuComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
    }

    public void clickSignUpButton() {
        wait.until(ExpectedConditions.visibilityOf(signUpButton));
        signUpButton.click();
    }

    public void clickLogoutButton() {
        wait.until(ExpectedConditions.visibilityOf(logoutButton));
        logoutButton.click();
    }

    public void clickCartButton() {
        wait.until(ExpectedConditions.visibilityOf(cartButton));
        cartButton.click();
    }

    public void clickHomeButton() {
        wait.until(ExpectedConditions.visibilityOf(homeButton));
        homeButton.click();
    }

    public void clickContactButton() {
        wait.until(ExpectedConditions.visibilityOf(contactButton));
        contactButton.click();
    }

    public void clickAboutUsButton() {
        wait.until(ExpectedConditions.visibilityOf(aboutUsButton));
        aboutUsButton.click();
    }

    public boolean isUserLoggedIn(String username) {
        wait.until(ExpectedConditions.visibilityOf(welcomeMessage));
        return welcomeMessage.getText().contains("Welcome " + username);
    }

    public boolean isUserLoggedOut() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        wait.until(ExpectedConditions.visibilityOf(signUpButton));
        return loginButton.isDisplayed() && signUpButton.isDisplayed();
    }
}
