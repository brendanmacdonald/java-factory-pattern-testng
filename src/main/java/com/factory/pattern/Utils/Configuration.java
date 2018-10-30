package com.factory.pattern.Utils;

import com.factory.pattern.drivers.DriverType;

public class Configuration {

    // Command line parameters.
    private DriverType browser;

    public DriverType getBrowser() {
        return browser;
    }

    public void setBrowser(DriverType browser) {
        this.browser = browser;
    }

}
