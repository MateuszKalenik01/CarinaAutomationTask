package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

import java.net.MalformedURLException;
import java.net.URL;

public class VerifyProductDetailsTest {
    WebDriver driver;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeClass
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

    @Test(dependsOnMethods = "userLoginVerification")
    public void verifyProductDetails() {
        homePage.clickOnCategory();
        homePage.clickOnFirstProduct();
        Assert.assertTrue(productPage.isProductDetailsDisplayed(), "Product details are not displayed as expected.");
    }

    @Test(dependsOnMethods = "verifyProductDetails")
    public void addToCart() {
        productPage.addToCart();
        // Additional verification can be added here if needed
    }

    @Test(dependsOnMethods = {"userLoginVerification", "addToCart"})
    public void completePurchase() {
        homePage.goToCart();

        Assert.assertEquals(cartPage.getNumberOfCartItems(), 1, "Number of items in the cart is not correct.");
        Assert.assertEquals(cartPage.getTotalPrice(), "360", "Total price is not correct.");

        cartPage.placeOrder();
        cartPage.fillOrderDetails("Test User", "Test Country", "Test City", "1234567890123456", "12", "2024");
        cartPage.purchaseOrder();

        Assert.assertTrue(cartPage.isPurchaseSuccessful(), "Purchase was not successful.");
        cartPage.closeSuccessAlert();
    }

    @Test(dependsOnMethods = {"userLoginVerification", "verifyProductDetails", "addToCart", "completePurchase"})
    public void userLogoutVerification() {
        homePage.logout();
        Assert.assertTrue(homePage.isUserLoggedOut(), "User is not logged out successfully.");
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
