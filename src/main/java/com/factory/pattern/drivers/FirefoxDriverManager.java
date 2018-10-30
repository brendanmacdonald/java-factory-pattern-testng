package com.factory.pattern.drivers;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;

public class FirefoxDriverManager extends DriverManager {

    private GeckoDriverService geckoService;

    @Override
    public void startService() {
        if (null == geckoService) {
            try {
                geckoService = new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File("/home/brendan/Downloads/geckodriver-v0.23.0-linux64/geckodriver"))
                        .usingAnyFreePort()
                        .build();
                geckoService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != geckoService && geckoService.isRunning())
            geckoService.stop();
    }

    @Override
    public void createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("test-type");
        driver = new FirefoxDriver(geckoService, options);
    }

}
