package com.factory.pattern.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.testng.AssertJUnit.assertEquals;

import com.factory.pattern.drivers.DriverManager;
import com.factory.pattern.drivers.DriverManagerFactory;
import com.factory.pattern.drivers.DriverType;
//import com.factory.pattern.utils.ScreenShotOnFailure;
import io.qameta.allure.*;
import com.factory.pattern.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

@Epic("Login Epic")
@Feature("Login Feature")
@Link("https://jira.org")
public class LoginTest extends BaseTest {

    private  DriverManager driverManager;
    private  WebDriver driver;
    private LoginPage lp;

//    @Rule
//    public ScreenShotOnFailure failure = new ScreenShotOnFailure(driver);

    @BeforeTest
    @Parameters("browser")
    public void beforeTest(DriverType browser) {
        driverManager = DriverManagerFactory.getManager(browser);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = driverManager.getDriver();
        lp = new LoginPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitDriver();
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