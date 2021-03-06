package com.capgemini.ourWebdriver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

/**
 * Created by dlammers on 2/27/2017.
 */
public class OurFirefoxDriver extends FirefoxDriver implements OurWebDriver {

    private static OurFirefoxDriver browser;

    private OurFirefoxDriver() {

    }

    static OurFirefoxDriver getBrowser() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver-windows-64bit.exe");
        if (browser == null) {
            browser = new OurFirefoxDriver();
        } else if (browser.getSessionId() == null) {
            browser = new OurFirefoxDriver();
        }
        return browser;
    }

    public WebElement waitForElement(final String selector) {
        final WebDriverWait wait = new WebDriverWait(browser, IMPLICIT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selector)));
    }

    public WebElement waitForElement(final By by) {
        final WebDriverWait wait = new WebDriverWait(browser, IMPLICIT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForVisible(final String selector) {
        final WebDriverWait wait = new WebDriverWait(browser, IMPLICIT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
    }

    public WebElement waitForVisible(final By by) {
        final WebDriverWait wait = new WebDriverWait(browser, IMPLICIT_WAIT_TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void scrollToElement(final WebElement element) {
        ((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForAjax() {
        final WebDriverWait webDriverWait = new WebDriverWait(browser, IMPLICIT_WAIT_TIMEOUT);
        webDriverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver driver) {
                final String scriptToExecute =
                        "return jQuery.active == 0;";
                final Boolean ajaxDone = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(scriptToExecute).toString());
                return ajaxDone ? true : null;
            }
        });
    }

    public void takeScreenShot() throws IOException {
        File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
        String timestamp = Long.toString(System.currentTimeMillis());
        FileUtils.copyFile(scrFile, new File("C:\\test_automation\\Screenshots\\"+timestamp +".png"));
    }


}
