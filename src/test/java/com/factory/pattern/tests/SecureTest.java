package com.factory.pattern.tests;

import com.factory.pattern.pages.LoginPage;
import com.factory.pattern.pages.SecurePage;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class SecureTest extends BaseTest {

    private static WebDriver driver;
    private LoginPage lp;
    private SecurePage sp;

    @BeforeMethod
    public void beforeMethod() {
        driver = getDriver();
        lp = new LoginPage(driver, "tomsmith", "SuperSecretPassword!");
        sp = new SecurePage(driver);
    }

    @Test
    @Description("Test Description: verify a user can logout.")
    public void verifyLogoutTest() {
        lp.openLoginPage();
        lp.fillAndSubmit();
        sp.logout();

        assertThat(lp.getFlashText(), containsString("You logged out of the secure area!"));
    }
}