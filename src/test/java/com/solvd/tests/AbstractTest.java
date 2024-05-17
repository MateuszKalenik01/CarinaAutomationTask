package com.solvd.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected String username = "domat321537";
    protected String password = "password123";

    @BeforeMethod
    public void setUp() {
        driver.set(new ChromeDriver());
        getDriver().get("https://www.demoblaze.com/");
    }

    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driver.remove();
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
