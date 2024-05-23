package com.solvd.util.driver;

import com.solvd.utils.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class EdgeDriverManager implements DriverManager {
    private static final Logger logger = LoggerFactory.getLogger(EdgeDriverManager.class);

    @Override
    public WebDriver createDriver(String url) {
        EdgeOptions options = new EdgeOptions();
        try {
            return new RemoteWebDriver(new URL(url), options);
        } catch (MalformedURLException e) {
            logger.error("Failed to create WebDriver", e);
            throw new RuntimeException("Failed to create WebDriver", e);
        }
    }
}
