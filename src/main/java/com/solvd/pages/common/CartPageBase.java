package com.solvd.pages.common;

import org.openqa.selenium.WebDriver;

public abstract class CartPageBase extends AbstractPageWithHeaderMenu {

    protected CartPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract int getNumberOfCartItems();

    public abstract String getTotalPrice();

    public abstract void placeOrder();

    public abstract void fillOrderDetails(String name, String country, String city, String card, String month, String year);

    public abstract boolean isPurchaseSuccessful();

    public abstract void closeSuccessAlert();

    public abstract boolean isItemInCart(String productName);

    public abstract void removeAllItemsFromCart();
    public abstract void waitForPageToLoad();
}
