package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class VerifyProductDetailsTest {
    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        // URL of the Selenium server
        URL seleniumServerUrl = new URL("http://localhost:4444/wd/hub");

        // Desired capabilities for Chrome
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");

        // Initialize the RemoteWebDriver
        driver = new RemoteWebDriver(seleniumServerUrl, capabilities);
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void userLoginVerification() {
        String username = "domat98217";
        String password = "domat98217";

        homePage.clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();

        Assert.assertTrue(homePage.isUserLoggedIn(username), "User is not logged in successfully.");
    }

    @Test
    public void verifyProductDetails() {
        // Perform login first
        String username = "domat98217";
        String password = "domat98217";
        homePage.clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.isUserLoggedIn(username), "User is not logged in successfully.");

        homePage.clickOnCategory();
        homePage.clickOnFirstProduct();
        Assert.assertTrue(productPage.isProductDetailsDisplayed(), "Product details are not displayed as expected.");
    }

    @Test
    public void addToCart() {
        // Perform login first
        String username = "domat98217";
        String password = "domat98217";
        homePage.clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.isUserLoggedIn(username), "User is not logged in successfully.");

        homePage.clickOnCategory();
        homePage.clickOnFirstProduct();
        productPage.addToCart();
        // Additional verification can be added here if needed
    }

    @Test
    public void completePurchase() {
        // Perform login first
        String username = "domat98217";
        String password = "domat98217";
        homePage.clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.isUserLoggedIn(username), "User is not logged in successfully.");

        homePage.clickOnCategory();
        homePage.clickOnFirstProduct();
        productPage.addToCart();

        homePage.goToCart();

        int numberOfItems = cartPage.getNumberOfCartItems();
        Assert.assertTrue(numberOfItems >= 1, "Number of items in the cart is not correct. Actual: " + numberOfItems);

        int totalPrice = cartPage.getTotalPrice();
        Assert.assertTrue(totalPrice>= 50, "Total price is not correct. Actual: " + totalPrice);


        cartPage.placeOrder();
        cartPage.fillOrderDetails("Test User", "Test Country", "Test City", "1234567890123456", "12", "2024");
        cartPage.purchaseOrder();

        Assert.assertTrue(cartPage.isPurchaseSuccessful(), "Purchase was not successful.");
        cartPage.closeSuccessAlert();
    }

    @Test
    public void userLogoutVerification() {
        // Perform login first
        String username = "domat98217";
        String password = "domat98217";
        homePage.clickLoginButton();
        homePage.enterUsername(username);
        homePage.enterPassword(password);
        homePage.submitLogin();
        Assert.assertTrue(homePage.isUserLoggedIn(username), "User is not logged in successfully.");

        homePage.logout();
        Assert.assertTrue(homePage.isUserLoggedOut(), "User is not logged out successfully.");
    }

    @Test
    public void verifyNewUserRegistration() {
        // Generate a unique username
        String uniqueUsername = "domat321535" + UUID.randomUUID().toString().substring(0, 8);
        String password = "password123";

        homePage.clickSignUpButton();
        homePage.enterSignUpUsername(uniqueUsername);
        homePage.enterSignUpPassword(password);
        homePage.submitSignUp();

        Assert.assertTrue(homePage.isSignUpSuccessful(), "Sign up was not successful.");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
