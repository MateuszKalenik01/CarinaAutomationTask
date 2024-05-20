package com.solvd.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends AbstractPage {

    @FindBy(css = ".name")
    private WebElement productName;

    @FindBy(css = ".price-container")
    private WebElement productPrice;

    @FindBy(css = ".description .tab-pane p")
    private WebElement productDescription;

    @FindBy(css = ".btn-success")
    private WebElement addToCartButton;

    @FindBy(css = ".btn-primary[data-target='#orderModal']")
    private WebElement placeOrderButton;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement cardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(css = "button[onclick='purchaseOrder()']")
    private WebElement purchaseButton;

    @FindBy(css = ".sweet-alert .sa-success")
    private WebElement successMessage;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public boolean isProductDetailsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(productName));
        wait.until(ExpectedConditions.visibilityOf(productPrice));
        wait.until(ExpectedConditions.visibilityOf(productDescription));
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));

        return productName.isDisplayed() && productPrice.isDisplayed() && productDescription.isDisplayed() && addToCartButton.isDisplayed();
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCartButton.click();
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    public String getProductName() {
        wait.until(ExpectedConditions.visibilityOf(productName));
        return productName.getText();
    }

    public void placeOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderButton.click();
    }

    public void fillOrderDetails(String name, String country, String city, String card, String month, String year) {
        wait.until(ExpectedConditions.visibilityOf(nameField)).sendKeys(name);
        countryField.sendKeys(country);
        cityField.sendKeys(city);
        cardField.sendKeys(card);
        monthField.sendKeys(month);
        yearField.sendKeys(year);
    }

    public void purchaseOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
        purchaseButton.click();
    }

    public boolean isPurchaseSuccessful() {
        wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successMessage.isDisplayed();
    }
}
