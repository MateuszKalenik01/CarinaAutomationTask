package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "login2")
    private WebElement loginButton;

    @FindBy(id = "loginusername")
    private WebElement usernameField;

    @FindBy(id = "loginpassword")
    private WebElement passwordField;

    @FindBy(css = "button[onclick='logIn()']")
    private WebElement loginSubmitButton;

    @FindBy(id = "nameofuser")
    private WebElement welcomeMessage;

    @FindBy(id = "cartur")
    private WebElement cartButton;

    @FindBy(id = "logout2")
    private WebElement logoutButton;

    @FindBy(id = "signin2")
    private WebElement signUpButton;

    @FindBy(css = ".list-group a")
    private WebElement categories;

    @FindBy(css = "#tbodyid .card .card-title a")
    private WebElement firstProduct;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
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

    public boolean isUserLoggedIn(String username) {
        wait.until(ExpectedConditions.visibilityOf(welcomeMessage));
        return welcomeMessage.getText().contains("Welcome " + username);
    }

    public void clickOnCategory() {
        wait.until(ExpectedConditions.visibilityOf(categories));
        categories.click();
    }

    public void clickOnFirstProduct() {
        wait.until(ExpectedConditions.visibilityOf(firstProduct));
        firstProduct.click();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.visibilityOf(cartButton));
        cartButton.click();
    }

    public void logout() {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(300)); // Increased wait time for logout
        longWait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        logoutButton.click();
    }

    public boolean isUserLoggedOut() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        wait.until(ExpectedConditions.visibilityOf(signUpButton));
        return loginButton.isDisplayed() && signUpButton.isDisplayed();
    }
}




/*Create simple java test project with selenium and testNG as dependencies.
Goal: learn PageObject and PageFactory pattern, improve knowledge of maven, practice selenium and github usage
Details:
4. Use PageObject and PageFactory patterns for that
5. Use css selectors for your web elements (https://www.w3schools.com/cssref/css_selectors.asp)
6. Start standalone selenium server with chromedriver and debug implemented test on it
*/