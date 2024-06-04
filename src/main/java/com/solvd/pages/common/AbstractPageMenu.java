package com.solvd.pages.common;

import com.solvd.components.HeaderMenu;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Getter
public abstract class AbstractPageMenu extends AbstractPage {

    @FindBy(id = "navbarExample")
    private HeaderMenu headerMenu;

    public AbstractPageMenu(WebDriver driver) {
        super(driver);
        this.headerMenu = new HeaderMenu(driver);
    }

}
