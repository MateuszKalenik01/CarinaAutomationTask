package com.solvd.pages;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class CartPage extends AbstractPage {

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
        setPageURL("path/to/cart/page"); // Set the URL to the cart page
    }

    public List<ExtendedWebElement> getCartItems() {
        return cartItems;
    }

    public void deleteCartItem(WebElement item) {
        int index = cartItems.indexOf(item);
        WebElement deleteButton = deleteButtons.get(index);
        deleteButton.click();
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
        nameField.type(name);
        countryField.type(country);
        cityField.type(city);
        cardField.type(card);
        monthField.type(month);
        yearField.type(year);
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

    public boolean isItemInCart(String productName) {
        return getItemNamesList().stream()
                .map(WebElement::getText)
                .anyMatch(itemName -> itemName.equals(productName));
    }

    public void removeAllItemsFromCart() {
        while (!getCartItems().isEmpty()) {
            WebElement item = getCartItems().get(0);
            deleteCartItem(item);
            if (!getCartItems().isEmpty()) {
                waitForCartToReload(getCartItems().size());
            }
        }
    }
}
//$ mvn test -DsuiteXmlFile=testng.xml

/*@BeforeTest
    @Override
    public WebDriver getDriver() {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability("platformName", "ANDROID");
        dc.setCapability("appium:automationName", "UiAutomator2");
        dc.setCapability("appium:deviceName", "Pixel_3");
        dc.setCapability("appium:deviceType", "phone");
        dc.setCapability("appium:udid", "emulator-5554");
        dc.setCapability("browserName", "chrome");
        dc.setCapability("chromedri*/