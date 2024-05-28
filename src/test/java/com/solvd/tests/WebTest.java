package com.solvd.tests;


import com.solvd.model.User;
import com.solvd.pages.CartPage;
import com.solvd.pages.HomePage;
import com.solvd.pages.ProductPage;
import com.solvd.service.UserService;
import com.zebrunner.carina.core.AbstractTest;
import com.zebrunner.carina.utils.R;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.UUID;

public class WebTest extends AbstractTest {
    @Parameters({"browser"})
    @BeforeClass
    public void setUp(String browser) {
        R.CONFIG.put("capabilities.browserName", browser);
    }

    @AfterClass
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    @Test(testName = "TC002", threadPoolSize = 1, invocationCount = 1)
    public void validUserLoginVerification() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.getHeaderMenu().logIn(R.TESTDATA.get("user.username"), R.TESTDATA.get("user.password"));
        Assert.assertTrue(homePage.getHeaderMenu().isUserLoggedIn(), "The user is not logged in successfully. Welcome message is not displayed.");
    }

    @Test(testName = "TC001", threadPoolSize = 1, invocationCount = 1)
    public void addToCart() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ProductPage productPage = homePage.showProductDetails();
        String productName = productPage.getProductName();
        productPage.addToCart();
        CartPage cartPage = productPage.getHeaderMenu().clickCartButton();
        cartPage.waitForPageToLoad();
        Assert.assertTrue(cartPage.isItemInCart(productName), "The item is not found in the cart.");
    }

    @Test(testName = "TC003", threadPoolSize = 1, invocationCount = 1)
    public void completePurchase() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ProductPage productPage = homePage.showProductDetails();
        productPage.addToCart();
        CartPage cartPage = productPage.getHeaderMenu().clickCartButton();
        cartPage.placeOrder();
        UserService userService = new UserService();
        User user = userService.createDefaultUser();
        cartPage.fillOrderDetails(user.getName(), user.getCountry(), user.getCity(), user.getCreditCardNumber(), user.getMonth(), user.getYear());
        Assert.assertTrue(cartPage.isPurchaseSuccessful(), "Purchase was not completed successfully.");
    }

    @Test(testName = "TC004", threadPoolSize = 1, invocationCount = 1)
    public void userLogoutVerification() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        homePage.getHeaderMenu().logIn(R.TESTDATA.get("user.username"), R.TESTDATA.get("user.password"));
        homePage.getHeaderMenu().clickLogoutButton();
        Assert.assertTrue(homePage.getHeaderMenu().isUserLoggedOut(), "User is not logged out successfully.");
    }

    @Test(testName = "TC005", threadPoolSize = 1, invocationCount = 1)
    public void validNewUserRegistration() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        String uniqueUsername = R.TESTDATA.get("user.username") + UUID.randomUUID().toString().substring(0, 8);
        homePage.getHeaderMenu().clickSignUpButton();
        homePage.getHeaderMenu().enterSignUpUsername(uniqueUsername);
        homePage.getHeaderMenu().enterSignUpPassword(R.TESTDATA.get("user.password"));
        homePage.getHeaderMenu().submitSignUp();
        Assert.assertTrue(homePage.getHeaderMenu().isSignUpSuccessful(), "Sign up was not successful.");
    }

    @Test(testName = "TC006", threadPoolSize = 1, invocationCount = 1)
    public void addAndRemoveItemsFromCart() {
        HomePage homePage = new HomePage(getDriver());
        homePage.open();
        ProductPage productPage = homePage.showProductDetails();
        productPage.addToCart();
        homePage.getHeaderMenu().clickHomeButton();

        productPage = homePage.showProductDetails();
        productPage.addToCart();
        homePage.getHeaderMenu().clickHomeButton();

        CartPage cartPage = homePage.getHeaderMenu().clickCartButton();
        cartPage.waitForPageToLoad();
        int initialNumberOfItems = cartPage.getNumberOfCartItems();
        Assert.assertTrue(initialNumberOfItems != 0, "Items have not been added correctly. Actual: " + initialNumberOfItems);

        cartPage.removeAllItemsFromCart();

        int itemsRemaining = cartPage.getNumberOfCartItems();
        Assert.assertEquals(itemsRemaining, 0, "Not all items were removed from the cart. Actual items left: " + itemsRemaining);
    }
}

