package com.solvd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class HomePage extends AbstractPage {



    @FindBy(id = "sign-username")
    private WebElement signUpUsernameField;

    @FindBy(id = "sign-password")
    private WebElement signUpPasswordField;

    @FindBy(css = "button[onclick='register()']")
    private WebElement signUpSubmitButton;

    @FindBy(css = "#tbodyid .card .card-title a")
    private List<WebElement> productList;

    @FindBy(id = "nameofuser")
    private WebElement welcomeMessage;
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "div.modal.show")
    private WebElement modal;

    @FindBy(css = "button[data-dismiss='modal']")
    private WebElement modalCloseButton;
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
    public boolean isUserLoggedIn(String username) {
        wait.until(ExpectedConditions.visibilityOf(welcomeMessage));
        return getText(welcomeMessage).contains("Welcome " + username);
    }
    private void closeModalIfPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(modal));
            click(modalCloseButton);
            wait.until(ExpectedConditions.invisibilityOf(modal));
        } catch (Exception e) {

        }
    }

    public void addRandomProductToCart() {
        Random rand = new Random();
        int attempts = 0;
        while (attempts < 3) {
            try {

                wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("#tbodyid .card .card-title a")));

                productList = driver.findElements(By.cssSelector("#tbodyid .card .card-title a"));
                if (productList.isEmpty()) {
                    throw new IllegalStateException("Product list is empty, cannot add random product to cart.");
                }
                int randomIndex = rand.nextInt(productList.size());
                WebElement randomProduct = productList.get(randomIndex);
                closeModalIfPresent();
                randomProduct.click();
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 3) {
                    throw e;
                }
            }
        }
    }

}
