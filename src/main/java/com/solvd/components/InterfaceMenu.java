package com.solvd.components;

import com.solvd.pages.common.CartPageBase;
import com.zebrunner.carina.utils.factory.ICustomTypePageFactory;

public interface InterfaceMenu extends ICustomTypePageFactory {

    void clickHomeButton();

    CartPageBase clickCartButton();

    void clickLoginButton();

    void clickLogoutButton();

    void logIn(String username, String password);

    boolean isUserLoggedIn();

    boolean isUserLoggedOut();

    void clickSignUpButton();

    void enterSignUpUsername(String username);

    void enterSignUpPassword(String password);

    void submitSignUp();

    boolean isSignUpSuccessful();
}

