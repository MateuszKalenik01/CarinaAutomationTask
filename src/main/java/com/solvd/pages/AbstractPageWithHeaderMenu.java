package com.solvd.pages;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public abstract class AbstractPageWithHeaderMenu extends AbstractPage {

    @FindBy(id = "navbarExample")
    protected HeaderMenu headerMenu;

    public AbstractPageWithHeaderMenu(WebDriver driver) {
        super(driver);
        this.headerMenu = new HeaderMenu(driver);
    }

}
