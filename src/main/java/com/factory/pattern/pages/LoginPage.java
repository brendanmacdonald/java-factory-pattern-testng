package com.factory.pattern.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import java.util.Map;

public class LoginPage extends BasePage {

    private String user;
    private String pass;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage(WebDriver driver, String username, String password) {
        super(driver);
        this.user = username;
        this.pass = password;
    }

    public void openLoginPage() {
        driver.navigate().to("https://the-internet.herokuapp.com/login");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    @FindBy(id = "username")
    @CacheLookup
    private WebElement username;

    @FindBy(id = "password")
    @CacheLookup
    private WebElement password;

    @FindBy(className = "radius")
    @CacheLookup
    private WebElement loginBtn;


    public LoginPage setUsername(String username) {
        this.username.sendKeys(username);
        return this;
    }



    public LoginPage setPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public LoginPage submit() {
        loginBtn.click();
        return this;  // When you navigate you should return the a page object for the next page.
    }

    /**
     * Fill every fields in the page.
     *
     * @return the LoginPage class instance.
     */
    public LoginPage fill(String username, String password) {
        setUsername(username);
        setPassword(password);
        return this;
    }

    /**
     * Fill every fields in the page.
     *
     * @return the LoginPage class instance.
     */
    @Step
    public LoginPage fill() {
        setUsername(this.user);
        setPassword(this.pass);
        return this;
    }

    /**
     * Fill every fields in the page and submit it to target page.
     *
     * @return the LoginPage class instance.
     */
    @Step
    public LoginPage fillAndSubmit(String username, String password) {
        fill(username, password);
        return submit();
    }

    /**
     * Fill every fields in the page and submit it to target page.
     *
     * @return the LoginPage class instance.
     */
    @Step
    public LoginPage fillAndSubmit() {
        fill();
        return submit();
    }
}
