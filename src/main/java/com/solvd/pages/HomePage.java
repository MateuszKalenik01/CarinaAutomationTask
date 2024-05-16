package com.solvd.pages;

import com.solvd.components.MenuComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPage {
    private MenuComponent menu;

    @FindBy(id = "loginusername")
    private WebElement usernameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(css = "button[onclick='logIn()']")
    private WebElement loginSubmitButton;

    @FindBy(id = "sign-username")
    private WebElement signUpUsernameField;

    @FindBy(id = "sign-password")
    private WebElement signUpPasswordField;

    @FindBy(css = "button[onclick='register()']")
    private WebElement signUpSubmitButton;

    @FindBy(css = "#tbodyid .card .card-title a")
    private WebElement firstProduct;

    public HomePage(WebDriver driver) {
        super(driver);
        this.menu = new MenuComponent(driver);
    }

    public MenuComponent getMenu() {
        return menu;
    }

    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }

    public void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginSubmitButton));
        loginSubmitButton.click();
    }

    public void enterSignUpUsername(String username) {
        wait.until(ExpectedConditions.visibilityOf(signUpUsernameField));
        signUpUsernameField.sendKeys(username);
    }

    public void enterSignUpPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(signUpPasswordField));
        signUpPasswordField.sendKeys(password);
    }

    public void submitSignUp() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpSubmitButton));
        signUpSubmitButton.click();
    }

    public void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public boolean isSignUpSuccessful() {
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return alertText.contains("Sign up successful");
    }

    public void clickOnFirstProduct() {
        wait.until(ExpectedConditions.visibilityOf(firstProduct));
        firstProduct.click();
    }
}