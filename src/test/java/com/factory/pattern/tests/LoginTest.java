package com.factory.pattern.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.testng.AssertJUnit.assertEquals;

import io.qameta.allure.*;
import com.factory.pattern.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

@Epic("Login Epic")
@Feature("Login Feature")
@Link("https://jira.org")
public class LoginTest extends BaseTest {

    private  WebDriver driver;
    private LoginPage lp;

    @BeforeMethod
    public void beforeMethod() {
        driver = getDriver();
        lp = new LoginPage(driver);
    }

    @Test
    public void theInternetTest() {
        lp.openLoginPage();
        assertEquals("The Internet", lp.getPageTitle());
    }

    @Test
    @Description("Test Description: Login test with the correct username and correct password.")
    @Issue("JIRA-1234")
    @Story("Some Story id")
    @Severity(SeverityLevel.NORMAL)
    public void formAuthenticationTest() {
        lp.openLoginPage();
        lp.fillAndSubmit("tomsmith", "SuperSecretPassword!");

        assertThat(lp.getFlashText(), containsString("You logged into a secure area!"));
    }
}