package com.solvd;

import com.solvd.utils.ConfigReader;
import com.solvd.utils.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTest.class);
    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();


    @BeforeMethod
    @Parameters({"browser"})
    public void setup(String browser, ITestContext context) {
        String hubUrl = ConfigReader.getProperty("HUB_URL");
        WebDriver driver = DriverFactory.createDriver(browser, hubUrl);
        driverPool.set(driver);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.demoblaze.com/");

        context.setAttribute("driver", driver);
    }
    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driverPool.remove();
        }
    }

    public WebDriver getDriver() {
        return driverPool.get();
    }
}