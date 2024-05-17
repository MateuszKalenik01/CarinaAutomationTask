package com.solvd.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.solvd.pages.*;
import java.util.List;
import java.util.UUID;

public class WebTest extends AbstractTest {

    @Test(testName="TC003")
    public void validUserLoginVerification() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.isUserLoggedIn(username), "User is not logged in successfully.");
    }

    @Test(testName = "TC001")
    public void verifyProductDetails() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        homePage.clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.isUserLoggedIn(username), "User is not logged in successfully.");
        homePage.clickOnFirstProduct();
        Assert.assertTrue(productPage.isProductDetailsDisplayed(), "Product details are not displayed as expected.");
    }

    @Test(testName = "TC002")
    public void addToCart() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        homePage.clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.isUserLoggedIn(username), "User is not logged in successfully.");
        homePage.clickOnFirstProduct();
        productPage.addToCart();
    }

    @Test(testName = "TC004")
    public void completePurchase() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        homePage.clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.isUserLoggedIn(username), "User is not logged in successfully.");
        homePage.clickOnFirstProduct();
        productPage.addToCart();
        homePage.clickCartButton();

        int numberOfItems = cartPage.getNumberOfCartItems();
        Assert.assertTrue(numberOfItems > 0, "Number of items in the cart should be greater than 0. Actual: " + numberOfItems);

        cartPage.placeOrder();
        cartPage.fillOrderDetails("Test User", "Test Country", "Test City", "1234567890123456", "12", "2024");
        cartPage.purchaseOrder();
        Assert.assertTrue(cartPage.isPurchaseSuccessful(), "Purchase was not successful.");
        cartPage.closeSuccessAlert();
    }

    @Test(testName = "TC005")
    public void userLogoutVerification() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.isUserLoggedIn(username), "User is not logged in successfully.");
        homePage.clickLogoutButton();
        Assert.assertTrue(homePage.isUserLoggedOut(), "User is not logged out successfully.");
    }

    @Test(testName = "TCOO6")
    public void validNewUserRegistration() {
        HomePage homePage = new HomePage(getDriver());
        String uniqueUsername = username + UUID.randomUUID().toString().substring(0, 8);
        homePage.clickSignUpButton();
        homePage.enterSignUpUsername(uniqueUsername);
        homePage.enterSignUpPassword(password);
        homePage.submitSignUp();
        Assert.assertTrue(homePage.isSignUpSuccessful(), "Sign up was not successful.");
    }

    @Test(testName = "TC007")
    public void addAndRemoveItemsFromCart() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());

        homePage.clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.isUserLoggedIn(username), "User is not logged in successfully.");

        homePage.clickOnFirstProduct();
        productPage.addToCart();
        homePage.clickHomeButton();

        homePage.clickOnFirstProduct();
        productPage.addToCart();
        homePage.clickHomeButton();

        homePage.clickCartButton();

        int initialNumberOfItems = cartPage.getNumberOfCartItems();
        Assert.assertTrue(initialNumberOfItems >= 2, "Number of items in the cart is not correct. Actual: " + initialNumberOfItems);

        int itemsRemaining = initialNumberOfItems;

        while (itemsRemaining > 0) {
            List<WebElement> items = cartPage.getCartItems();
            if (!items.isEmpty()) {
                WebElement item = items.get(0);
                cartPage.deleteCartItem(item);
                itemsRemaining--;
                cartPage.waitForCartToReload(itemsRemaining);
            }
        }
        Assert.assertEquals(itemsRemaining, 0, "Not all items were removed from the cart. Actual items left: " + itemsRemaining);
    }
}
