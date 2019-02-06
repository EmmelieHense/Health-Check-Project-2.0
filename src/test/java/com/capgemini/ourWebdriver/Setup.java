package com.capgemini.ourWebdriver;

import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class Setup {

    private WebDriver browser;

    @Before
    public void beforeScenario() throws MalformedURLException {
        browser = BrowserFactory.getWebDriver();

    }
}
