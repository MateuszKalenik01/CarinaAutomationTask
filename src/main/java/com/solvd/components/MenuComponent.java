package com.solvd.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.solvd.pages.AbstractPage;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MenuComponent extends AbstractPage {

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
        super(driver);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void clickSignUpButton() {
        click(signUpButton);
    }

    public void clickLogoutButton() {
        click(logoutButton);
    }

    public void clickCartButton() {
        click(cartButton);
    }

    public void clickHomeButton() {
        click(homeButton);
    }

    public void clickContactButton() {
        click(contactButton);
    }

    public void clickAboutUsButton() {
        click(aboutUsButton);
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

