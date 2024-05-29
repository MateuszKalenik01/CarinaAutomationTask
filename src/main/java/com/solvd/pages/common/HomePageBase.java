package com.solvd.pages.common;

import org.openqa.selenium.WebDriver;

public abstract class HomePageBase extends AbstractPageWithHeaderMenu {

    protected HomePageBase(WebDriver driver) {
        super(driver);
    }

    public abstract ProductPageBase showProductDetails();
}
