package com.solvd.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class HomePage extends AbstractPage {

    @FindBy(id = "login")
    private ExtendedWebElement loginButton;

    @FindBy(id = "username")
    private ExtendedWebElement usernameField;

    @FindBy(id = "password")
    private ExtendedWebElement passwordField;

    @FindBy(id = "submit")
    private ExtendedWebElement submitButton;

    @FindBy(css = "#tbodyid .card .card-title a")
    private List<ExtendedWebElement> productList;

    @Getter
    private HeaderMenu headerMenu;

    public HomePage(WebDriver driver) {
        super(driver);
        headerMenu = new HeaderMenu(driver);
    }

    public ProductPage showProductDetails() {
        waitForPageToLoad();
        ExtendedWebElement randomProduct = selectRandomProduct();
        randomProduct.click();
        return new ProductPage(driver);
    }
    private void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> {
            List<ExtendedWebElement> products = productList;
            return products != null && !products.isEmpty() && products.get(0).isElementPresent();
        });
    }
    private ExtendedWebElement selectRandomProduct() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(productList.size());
        return productList.get(randomIndex);
    }
}
