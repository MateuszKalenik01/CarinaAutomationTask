package com.solvd.utils.driver;

import org.openqa.selenium.WebDriver;

public interface DriverManager {
    WebDriver createDriver(String url);
}

