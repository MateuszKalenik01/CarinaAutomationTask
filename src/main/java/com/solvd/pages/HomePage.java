package com.solvd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends AbstractPage {

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
    }

    public void enterUsername(String username) {
        sendKeys(usernameField, username);
    }

    public void enterPassword(String password) {
        sendKeys(passwordField, password);
    }

    public void submitLogin() {
        click(loginSubmitButton);
    }

    public void enterSignUpUsername(String username) {
        sendKeys(signUpUsernameField, username);
    }

    public void enterSignUpPassword(String password) {
        sendKeys(signUpPasswordField, password);
    }

    public void submitSignUp() {
        click(signUpSubmitButton);
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
        click(firstProduct);
    }
}
