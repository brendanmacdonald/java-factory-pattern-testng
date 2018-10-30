package com.factory.pattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "flash")
    public WebElement flash;

    /**
     * Returns the text in the flash element,
     * @return the flash text.
     */
    public String getFlashText() {
        return this.flash.getText();
    }
}
