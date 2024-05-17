package com.solvd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPage.class);

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        LOGGER.info("Clicking on element: {}", element.toString());
        element.click();
    }

    protected void sendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        LOGGER.info("Sending keys to element: {} with text: {}", element.toString(), text);
        element.sendKeys(text);
    }

    protected String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        LOGGER.info("Getting text from element: {}. Text: {}", element.toString(), text);
        return text;
    }

    protected boolean isDisplayed(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        boolean displayed = element.isDisplayed();
        LOGGER.info("Element: {} is displayed: {}", element.toString(), displayed);
        return displayed;
    }


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
        return getText(welcomeMessage).contains("Welcome " + username);
    }

    public boolean isUserLoggedOut() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        wait.until(ExpectedConditions.visibilityOf(signUpButton));
        return isDisplayed(loginButton) && isDisplayed(signUpButton);
    }
}
