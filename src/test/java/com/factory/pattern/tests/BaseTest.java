package com.factory.pattern.tests;

import com.factory.pattern.drivers.DriverManager;
import com.factory.pattern.drivers.DriverManagerFactory;
import com.factory.pattern.drivers.DriverType;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class BaseTest implements IHookable {

    private static Properties environment;
    private WebDriver driver;
    private DriverManager driverManager;

    @BeforeMethod
    @Parameters("browser")
    public void beforeSuite(DriverType browser) {
        driverManager = DriverManagerFactory.getManager(browser);
        driver = driverManager.getDriver();
    }

    @AfterMethod
    public void afterSuite() {
        driverManager.quitDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {

        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            try {
                takeScreenShot(testResult.getMethod().getMethodName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Attachment(value = "Failure in test {0}", type = "image/png")
    private byte[] takeScreenShot(String methodName) throws IOException {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterSuite
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
        try (OutputStream out = new FileOutputStream("./target/allure-results/environment.properties")) {
            environment.store(out, "Allure environment properties");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveCategories() {
        File source = new File("./categories.json");
        File dest = new File("./target/allure-results");
        try {
            FileUtils.copyFileToDirectory(source, dest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}