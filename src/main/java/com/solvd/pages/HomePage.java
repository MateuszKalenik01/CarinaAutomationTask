package com.solvd.pages;

import com.solvd.pages.common.HomePageBase;
import com.solvd.pages.common.ProductPageBase;
import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(css = "#tbodyid .card .card-title a")
    private List<ExtendedWebElement> productList;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductPageBase showProductDetails() {
        waitForPageToLoad();
        ExtendedWebElement randomProduct = selectRandomProduct();
        randomProduct.click();
        return initPage(driver, ProductPageBase.class);
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
