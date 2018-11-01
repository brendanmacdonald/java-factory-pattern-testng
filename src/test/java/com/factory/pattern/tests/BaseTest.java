package com.factory.pattern.tests;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class BaseTest {

    private static Properties environment;

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
        try (OutputStream out = new FileOutputStream("./target/allure-results/environment.properties")){
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