package com.solvd.tests;

import com.solvd.AbstractTest;
import com.solvd.listeners.TestListener;
import com.solvd.model.User;
import com.solvd.pages.CartPage;
import com.solvd.pages.HomePage;
import com.solvd.pages.ProductPage;
import com.solvd.service.UserService;
import com.solvd.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.UUID;
@Listeners(TestListener.class)
public class WebTest extends AbstractTest {

    @Test(testName = "TC002", threadPoolSize = 1,  invocationCount = 1)
    public void validUserLoginVerification() {
        HomePage homePage = new HomePage(getDriver());
        homePage.logIn(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        String expectedUsername = ConfigReader.getProperty("username");
        Assert.assertTrue(homePage.isUserLoggedIn(expectedUsername), "The user is not logged in successfully. Welcome message is not displayed.");
    }

    @Test(testName = "TC001", threadPoolSize = 1 ,  invocationCount = 1)
    public void addToCart() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        homePage.logIn(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        homePage.showProductDetails();
        String productName = productPage.getProductName();
        productPage.addToCart();
        homePage.clickCartButton();
        Assert.assertTrue(cartPage.isItemInCart(productName), "The item is not found in the cart.");
    }

    @Test(testName = "TC003", threadPoolSize = 1,  invocationCount = 1)
    public void completePurchase() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        UserService userService = new UserService();
        User user = userService.createDefaultUser();

        homePage.logIn(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        homePage.showProductDetails();
        productPage.addToCart();
        homePage.clickCartButton();

        cartPage.placeOrder();
        cartPage.fillOrderDetails(user.getName(), user.getCountry(), user.getCity(), user.getCreditCardNumber(), user.getMonth(), user.getYear());
        cartPage.purchaseOrder();
        Assert.assertTrue(cartPage.isPurchaseSuccessful(), "Purchase was not successful.");
    }

    @Test(testName = "TC004", threadPoolSize = 1,  invocationCount = 1)
    public void userLogoutVerification() {
        HomePage homePage = new HomePage(getDriver());
        homePage.logIn(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));
        homePage.clickLogoutButton();
        Assert.assertTrue(homePage.isUserLoggedOut(), "User is not logged out successfully.");
    }

    @Test(testName = "TC005", threadPoolSize = 1,  invocationCount = 1)
    public void validNewUserRegistration() {
        HomePage homePage = new HomePage(getDriver());
        String uniqueUsername = ConfigReader.getProperty("username") + UUID.randomUUID().toString().substring(0, 8);
        homePage.clickSignUpButton();
        homePage.enterSignUpUsername(uniqueUsername);
        homePage.enterSignUpPassword(ConfigReader.getProperty("password"));
        homePage.submitSignUp();
        Assert.assertTrue(homePage.isSignUpSuccessful(), "Sign up was not successful.");
    }

    @Test(testName = "TC006", threadPoolSize = 1,  invocationCount = 1)

    public void addAndRemoveItemsFromCart() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());

        homePage.logIn(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

        homePage.showProductDetails();
        productPage.addToCart();
        homePage.clickHomeButton();

        homePage.showProductDetails();
        productPage.addToCart();
        homePage.clickHomeButton();

        homePage.clickCartButton();

        int initialNumberOfItems = cartPage.getNumberOfCartItems();
        Assert.assertTrue(initialNumberOfItems != 0, "Items have not been added correctly. Actual: " + initialNumberOfItems);

        cartPage.removeAllItemsFromCart();

        int itemsRemaining = cartPage.getNumberOfCartItems();
        Assert.assertEquals(itemsRemaining, 0, "Not all items were removed from the cart. Actual items left: " + itemsRemaining);
    }
}
