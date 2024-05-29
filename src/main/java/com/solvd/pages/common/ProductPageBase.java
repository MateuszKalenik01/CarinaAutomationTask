package com.solvd.pages.common;

import org.openqa.selenium.WebDriver;

public abstract class ProductPageBase extends AbstractPageWithHeaderMenu {

    protected ProductPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract String getProductName();

    public abstract void addToCart();
}
