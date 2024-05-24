package com.solvd.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends AbstractPage {

    @FindBy(css = ".name")
    private ExtendedWebElement productName;

    @FindBy(css = ".price-container")
    private ExtendedWebElement productPrice;

    @FindBy(css = ".description .tab-pane p")
    private ExtendedWebElement productDescription;

    @FindBy(css = ".btn-success")
    private ExtendedWebElement addToCartButton;

    @FindBy(css = ".btn-primary[data-target='#orderModal']")
    private ExtendedWebElement placeOrderButton;

    @FindBy(id = "name")
    private ExtendedWebElement nameField;

    @FindBy(id = "country")
    private ExtendedWebElement countryField;

    @FindBy(id = "city")
    private ExtendedWebElement cityField;

    @FindBy(id = "card")
    private ExtendedWebElement cardField;

    @FindBy(id = "month")
    private ExtendedWebElement monthField;

    @FindBy(id = "year")
    private ExtendedWebElement yearField;

    @FindBy(css = "button[onclick='purchaseOrder()']")
    private ExtendedWebElement purchaseButton;

    @FindBy(css = ".sweet-alert .sa-success")
    private ExtendedWebElement successMessage;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductDetailsDisplayed() {
        return productName.isDisplayed() && productPrice.isDisplayed() && productDescription.isDisplayed() && addToCartButton.isDisplayed();
    }

    public void addToCart() {
        addToCartButton.click();
    }

    public String getProductName() {
        return productName.getText();
    }
}
