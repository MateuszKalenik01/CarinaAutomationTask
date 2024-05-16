package tests;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.solvd.pages.*;
import java.util.List;
import java.util.UUID;

public class WebTest extends AbstractTest {

    @Test
    public void validUserLoginVerification() {
        HomePage homePage = new HomePage(getDriver());
        String username = "domat321535";
        String password = "password123";
        homePage.getMenu().clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.getMenu().isUserLoggedIn(username), "User is not logged in successfully.");
    }

    @Test
    public void verifyProductDetails() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        String username = "domat321535";
        String password = "password123";
        homePage.getMenu().clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.getMenu().isUserLoggedIn(username), "User is not logged in successfully.");
        homePage.clickOnFirstProduct();
        Assert.assertTrue(productPage.isProductDetailsDisplayed(), "Product details are not displayed as expected.");
    }

    @Test
    public void addToCart() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        String username = "domat321535";
        String password = "password123";
        homePage.getMenu().clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.getMenu().isUserLoggedIn(username), "User is not logged in successfully.");
        homePage.clickOnFirstProduct();
        productPage.addToCart();
    }

    @Test
    public void completePurchase() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        String username = "domat321537";
        String password = "password123";
        homePage.getMenu().clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.getMenu().isUserLoggedIn(username), "User is not logged in successfully.");
        homePage.clickOnFirstProduct();
        productPage.addToCart();
        homePage.getMenu().clickCartButton();
        int numberOfItems = cartPage.getNumberOfCartItems();
        Assert.assertEquals(numberOfItems, 1, "Number of items in the cart is not correct. Actual: " + numberOfItems);
        String totalPrice = cartPage.getTotalPrice();
        Assert.assertEquals(totalPrice, "360", "Total price is not correct. Actual: " + totalPrice);
        cartPage.placeOrder();
        cartPage.fillOrderDetails("Test User", "Test Country", "Test City", "1234567890123456", "12", "2024");
        cartPage.purchaseOrder();
        Assert.assertTrue(cartPage.isPurchaseSuccessful(), "Purchase was not successful.");
        cartPage.closeSuccessAlert();
    }

    @Test
    public void userLogoutVerification() {
        HomePage homePage = new HomePage(getDriver());
        String username = "domat321535";
        String password = "password123";
        homePage.getMenu().clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.getMenu().isUserLoggedIn(username), "User is not logged in successfully.");
        homePage.getMenu().clickLogoutButton();
        Assert.assertTrue(homePage.getMenu().isUserLoggedOut(), "User is not logged out successfully.");
    }

    @Test
    public void validNewUserRegistration() {
        HomePage homePage = new HomePage(getDriver());
        String uniqueUsername = "domat321535" + UUID.randomUUID().toString().substring(0, 8);
        String password = "password123";
        homePage.getMenu().clickSignUpButton();
        homePage.enterSignUpUsername(uniqueUsername);
        homePage.enterSignUpPassword(password);
        homePage.submitSignUp();
        Assert.assertTrue(homePage.isSignUpSuccessful(), "Sign up was not successful.");
    }

    @Test
    public void addAndRemoveItemsFromCart() {
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        CartPage cartPage = new CartPage(getDriver());
        String username = "domat321537";
        String password = "password123";

        homePage.getMenu().clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.getMenu().isUserLoggedIn(username), "User is not logged in successfully.");

        homePage.clickOnFirstProduct();
        productPage.addToCart();
        homePage.getMenu().clickHomeButton();

        homePage.clickOnFirstProduct();
        productPage.addToCart();
        homePage.getMenu().clickHomeButton();

        homePage.getMenu().clickCartButton();

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