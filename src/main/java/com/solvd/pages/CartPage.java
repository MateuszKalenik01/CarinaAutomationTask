package com.solvd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends AbstractPage {

    @FindBy(css = "#tbodyid tr.success")
    private List<WebElement> cartItems;

    @FindBy(id = "totalp")
    private WebElement totalPrice;

    @FindBy(css = ".btn.btn-success[data-target='#orderModal']")
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

    @FindBy(css = ".sweet-alert .sa-confirm-button-container .confirm")
    private WebElement confirmButton;

    @FindBy(css = "td:nth-child(2)")
    private List<WebElement> itemNames;

    @FindBy(css = "td a[onclick*='deleteItem']")
    private List<WebElement> deleteButtons;

    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public List<WebElement> getCartItems() {
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return cartItems;
    }

    public void deleteCartItem(WebElement item) {
        int index = cartItems.indexOf(item);
        WebElement deleteButton = deleteButtons.get(index);
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
        wait.until(ExpectedConditions.stalenessOf(item));
    }

    public int getNumberOfCartItems() {
        return getCartItems().size();
    }

    public String getTotalPrice() {
        wait.until(ExpectedConditions.visibilityOf(totalPrice));
        return totalPrice.getText();
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

    public void closeSuccessAlert() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton));
        confirmButton.click();
    }
    public List<WebElement> getItemNamesList() {
        loadWebElementList(itemNames);
        return itemNames;
    }
    public List<WebElement> getCartItemsList() {
        loadWebElementList(cartItems);
        return cartItems;
    }

    public void waitForCartToReload(int expectedNumberOfItems) {
        if (expectedNumberOfItems == 0) {
            wait.until(ExpectedConditions.invisibilityOfAllElements(cartItems));
        } else {
            wait.until(driver -> {
                List<WebElement> items = getCartItemsList();
                return items.size() == expectedNumberOfItems && items.stream().allMatch(WebElement::isDisplayed);
            });
        }
    }
    public boolean isItemInCart(String productName) {
        List<WebElement> itemNamesList = getItemNamesList();
        for (WebElement itemNameElement : itemNamesList) {
            String itemName = itemNameElement.getText();
            if (itemName.equals(productName)) {
                return true;
            }
        }
        return false;
    }

}
