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
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

@Epic("Login Epic")
@Feature("Login Feature")
@Link("https://jira.org")
public class LoginTest {

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
        assertEquals("The Internet1", lp.getPageTitle());
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

    private static Properties environment;

    @AfterClass
    public void TearDownSuite() {
        environment = new Properties();
        environment.put("OS name", System.getProperty("os.name"));
        environment.put("OS version", System.getProperty("os.version"));

        saveEnvironment();
        saveCategories();
    }

    private void saveEnvironment() {
        File resultsFolder = new File("./target/allure-results");
        if (!resultsFolder.exists()) {
            resultsFolder.mkdirs();
        }
        try (OutputStream out = new FileOutputStream("./target/allure-results/environment.properties")){
            environment.store(out, "Allure environment properties");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void saveCategories() {
        File source = new File("./categories.json");
        File dest = new File("./target/allure-results");
        try {
            FileUtils.copyFileToDirectory(source, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
