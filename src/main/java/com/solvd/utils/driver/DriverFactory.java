package com.solvd.utils.driver;
import com.solvd.exceptions.BrowserNotSupportedException;
import com.solvd.util.driver.ChromeDriverManager;
import com.solvd.util.driver.EdgeDriverManager;
import com.solvd.util.driver.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);

    public static WebDriver createDriver(String browser, String url) {
        DriverManager driverManager;

        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driverManager = new ChromeDriverManager();
                    break;
                case "firefox":
                    driverManager = new FirefoxDriverManager();
                    break;
                case "edge":
                    driverManager = new EdgeDriverManager();
                    break;
                default:
                    LOGGER.error("Unsupported browser: " + browser);
                    throw new BrowserNotSupportedException("Unsupported browser: " + browser);
            }
            WebDriver driver = driverManager.createDriver(url);
            LOGGER.info("{} driver created successfully.", browser);
            return driver;
        } catch (IllegalArgumentException | WebDriverException e) {
            LOGGER.error("Failed to create driver for browser: " + browser, e);
            throw new BrowserNotSupportedException("Failed to create driver for browser: " + browser, e);
        }
    }
}
