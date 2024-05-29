package com.solvd.components;

import com.solvd.pages.common.CartPageBase;
import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public abstract class HeaderMenuBase extends AbstractUIObject implements ICustomTypePageFactory {

    protected HeaderMenuBase(WebDriver driver) {
        super(driver);
    }

    public abstract void clickHomeButton();

    public abstract CartPageBase clickCartButton();

    public abstract void clickLoginButton();

    public abstract void clickLogoutButton();

    public abstract void logIn(String username, String password);

    public abstract boolean isUserLoggedIn();

    public abstract boolean isUserLoggedOut();

    public abstract void clickSignUpButton();

    public abstract void enterSignUpUsername(String username);

    public abstract void enterSignUpPassword(String password);

    public abstract void submitSignUp();

    public abstract boolean isSignUpSuccessful();
}
