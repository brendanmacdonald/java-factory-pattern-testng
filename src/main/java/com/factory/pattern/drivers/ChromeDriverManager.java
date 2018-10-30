package com.factory.pattern.drivers;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chromeService;

    @Override
    public void startService() {
        if (null == chromeService) {
            try {
                chromeService = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File("/home/brendan/Downloads/chromedriver"))
                        .usingAnyFreePort()
                        .build();
                chromeService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != chromeService && chromeService.isRunning())
            chromeService.stop();
    }

    @Override
    public void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
//        DesiredCapabilities caps = DesiredCapabilities.chrome();
//        caps.setCapability(ChromeOptions.CAPABILITY, options);
//        options.merge(caps);
        driver = new ChromeDriver(chromeService, options);
    }

}
