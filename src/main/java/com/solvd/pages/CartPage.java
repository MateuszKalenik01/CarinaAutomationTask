package com.solvd.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends AbstractPage {

    @Getter
    @FindBy(css = "#tbodyid tr")
    private List<ExtendedWebElement> cartItems;

    @FindBy(id = "totalp")
    private ExtendedWebElement totalPrice;

    @FindBy(css = ".btn.btn-success[data-target='#orderModal']")
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

    @FindBy(css = ".sweet-alert .sa-confirm-button-container .confirm")
    private ExtendedWebElement confirmButton;

    @FindBy(css = "td:nth-child(2)")
    private List<ExtendedWebElement> itemNames;

    @FindBy(css = "td a[onclick*='deleteItem']")
    private List<ExtendedWebElement> deleteButtons;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfCartItems() {
        return getCartItems().size();
    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }

    public void placeOrder() {
        placeOrderButton.click();
    }

    public void fillOrderDetails(String name, String country, String city, String card, String month, String year) {
        nameField.waitUntil(ExpectedConditions.elementToBeClickable(nameField.getElement()), 10);
        nameField.type(name);
        countryField.type(country);
        cityField.type(city);
        cardField.type(card);
        monthField.type(month);
        yearField.type(year);
        purchaseOrder();
    }

    public void purchaseOrder() {
        purchaseButton.click();
    }

    public boolean isPurchaseSuccessful() {
        return successMessage.isElementPresent();
    }

    public void closeSuccessAlert() {
        confirmButton.click();
    }

    public List<ExtendedWebElement> getItemNamesList() {
        return itemNames;
    }

    public void waitForCartToReload(int expectedNumberOfItems) {
        if (expectedNumberOfItems > 0) {
            waitUntil(driver -> getCartItems().size() == expectedNumberOfItems, 10);
        }
    }

    public void waitForPageToLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> {
            List<ExtendedWebElement> products = cartItems;
            return products != null && !products.isEmpty() && products.get(0).isElementPresent();
        });
    }

    public boolean isItemInCart(String productName) {
        return getItemNamesList().stream()
                .map(ExtendedWebElement::getText)
                .anyMatch(itemName -> itemName.equals(productName));
    }

    public void removeAllItemsFromCart() {
        int numberOfItems = getNumberOfCartItems();
        for (int i = 0; i < numberOfItems; i++) {
            waitUntil(ExpectedConditions.elementToBeClickable(deleteButtons.get(0).getElement()), 10);
            deleteItemFromCartByIndex(0);
            if (getNumberOfCartItems() > 0) {
                waitForCartToReload(getNumberOfCartItems());
            }
        }
        waitForCartToBeEmpty();
    }

    public void deleteItemFromCartByIndex(int index) {
        List<ExtendedWebElement> deleteButtons = getDeleteButtons();
        if (index >= 0 && index < deleteButtons.size()) {
            deleteButtons.get(index).click();
            waitForCartToReload(getNumberOfCartItems() - 1);
        } else {
            throw new IndexOutOfBoundsException("Invalid item index: " + index);
        }
    }

    private List<ExtendedWebElement> getDeleteButtons() {
        return deleteButtons;
    }
    private void waitForCartToBeEmpty() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> getCartItems().isEmpty());
    }
}
