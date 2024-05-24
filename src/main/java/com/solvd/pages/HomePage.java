package com.solvd.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Random;

public class HomePage extends AbstractPage {

    @FindBy(id = "sign-username")
    private ExtendedWebElement signUpUsernameField;

    @FindBy(id = "sign-password")
    private ExtendedWebElement signUpPasswordField;

    @FindBy(css = "button[onclick='register()']")
    private ExtendedWebElement signUpSubmitButton;

    @Getter
    @FindBy(css = "#tbodyid .card .card-title a")
    private List<ExtendedWebElement> productList;

    @FindBy(id = "nameofuser")
    private ExtendedWebElement welcomeMessage;

    @FindBy(css = "div.modal.show")
    private ExtendedWebElement modal;

    @FindBy(css = "button[data-dismiss='modal']")
    private ExtendedWebElement modalCloseButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void enterSignUpUsername(String username) {
        signUpUsernameField.type(username);
    }

    public void enterSignUpPassword(String password) {
        signUpPasswordField.type(password);
    }

    public void submitSignUp() {
        signUpSubmitButton.click();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public boolean isSignUpSuccessful() {
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return alertText.contains("Sign up successful");
    }

    public boolean isUserLoggedIn(String username) {
        return welcomeMessage.getText().contains("Welcome " + username);
    }

    private void closeModalIfPresent() {
        if (modal.isElementPresent()) {
            modalCloseButton.click();
        }
    }

    public ExtendedWebElement getRandomProduct() {
        Random rand = new Random();
        if (productList.isEmpty()) {
            throw new IllegalStateException("Product list is empty, cannot select a random product.");
        }
        int randomIndex = rand.nextInt(productList.size());
        return productList.get(randomIndex);
    }

    public void showProductDetails() {
        int attempts = 0;
        while (attempts < 3) {
            try {
                ExtendedWebElement product = getRandomProduct();
                closeModalIfPresent();
                product.click();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 3) {
                    throw new IllegalStateException("Failed to add product to cart after multiple attempts.", e);
                }
            }
        }
    }
}
