package com.solvd.service;

import com.solvd.pages.HomePage;
import com.solvd.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class LoginService {

    private final HomePage homePage;
    private final WebDriver webDriver;
    private final String USERNAME = ConfigReader.getProperty("username");
    private final String PASSWORD = ConfigReader.getProperty("password");

    public LoginService(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(ConfigReader.getProperty("url"));
        this.homePage = new HomePage(webDriver);
    }
    public void successfulLogin() {
        homePage.logIn(USERNAME, PASSWORD);
    }
}
