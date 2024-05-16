package com.solvd.pages;

import com.solvd.components.MenuComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CartPage extends AbstractPage {
    private MenuComponent menu;

    @FindBy(css = "#tbodyid tr")
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

    public CartPage(WebDriver driver) {
        super(driver);
        this.menu = new MenuComponent(driver);
    }

    public MenuComponent getMenu() {
        return menu;
    }

    public List<WebElement> getCartItems() {
        cartItems = driver.findElements(By.cssSelector("#tbodyid tr"));
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return cartItems;
    }

    public void deleteCartItem(WebElement item) {
        WebElement deleteButton = item.findElement(By.cssSelector("td a[onclick*='deleteItem']"));
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton));
        deleteButton.click();
        wait.until(ExpectedConditions.stalenessOf(item));
    }

    public int getNumberOfCartItems() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#tbodyid tr")));
        return getCartItems().size();
    }

    public String  getTotalPrice() {
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

    public void waitForCartToReload(int expectedNumberOfItems) {
        if (expectedNumberOfItems == 0) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#tbodyid tr")));
        } else {
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#tbodyid tr"), expectedNumberOfItems));
        }
    }
}
