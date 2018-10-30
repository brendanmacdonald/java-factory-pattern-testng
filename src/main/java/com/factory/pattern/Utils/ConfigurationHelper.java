package com.factory.pattern.Utils;

import com.factory.pattern.drivers.DriverType;

public class ConfigurationHelper {

    public static Configuration getCommandLineParams() {
        Configuration configuration = new Configuration();

        // Command line parameters.
        String browser = System.getProperty("browser");

        // Set command line parameter values.
        configuration.setBrowser(DriverType.valueOf(browser));

        return configuration;
    }
}
