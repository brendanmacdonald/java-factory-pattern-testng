package com.factory.pattern.tests;

import com.factory.pattern.Utils.Configuration;
import com.factory.pattern.Utils.ConfigurationHelper;
import com.factory.pattern.drivers.DriverManager;
import com.factory.pattern.drivers.DriverManagerFactory;
import com.factory.pattern.drivers.DriverType;
import com.factory.pattern.pages.LoginPage;
import com.factory.pattern.pages.SecurePage;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class SecureTest {

    private static DriverManager driverManager;
    private static WebDriver driver;
    private LoginPage lp;
    private SecurePage sp;

    @BeforeTest
    @Parameters("browser")
    public void beforeTest(DriverType browser) {
        driverManager = DriverManagerFactory.getManager(browser);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getDriver();
        lp = new LoginPage(driver, "tomsmith", "SuperSecretPassword!");
        sp = new SecurePage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitDriver();
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
