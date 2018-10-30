package com.factory.pattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class SecurePage extends BasePage{

    public SecurePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "radius")
    @CacheLookup
    private WebElement logoutBtn;

    public SecurePage logout(){
        logoutBtn.click();
        return this;
    }
}
