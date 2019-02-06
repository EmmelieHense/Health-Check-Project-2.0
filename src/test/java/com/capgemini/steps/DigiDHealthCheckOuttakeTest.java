package com.capgemini.steps;

import com.capgemini.ourWebdriver.BrowserFactory;
import com.capgemini.ourWebdriver.OurFirefoxDriver;
import com.capgemini.ourWebdriver.OurWebDriver;
import com.github.javafaker.Faker;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.JOptionPane;
import java.lang.String;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;

public class DigiDHealthCheckOuttakeTest {
    private final OurWebDriver browser;

    public DigiDHealthCheckOuttakeTest() throws MalformedURLException {
        browser = BrowserFactory.getWebDriver();
//        ChromeOptions options = new ChromeOptions();
//        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
  }

    private void getscreenshot() throws Exception
    {
        File scrFile = ((TakesScreenshot)browser).getScreenshotAs(OutputType.FILE);
        //The below method will save the screen shot in d drive with name "screenshot.png"
        String timestamp = Long.toString(System.currentTimeMillis());
        FileUtils.copyFile(scrFile, new File("C:\\test_automation\\Screenshots\\"+timestamp +".png"));
    }

    @Given("^ik ga naar MijnDigiD op omgeving \"([^\"]*)\"$")
    public void MijnDigiDopomgeving(String omgeving) throws Throwable {
        if (omgeving.equals("t1"))browser.navigate().to("https://t1.digid.nl");
        else {
            if (omgeving.equals("t2"))browser.navigate().to("https://t2.digid.nl");

            else {
                if (omgeving.equals("o1")) {
                    browser.navigate().to("https://o1.digid.nl");

                } else {
                    if (omgeving.equals("a3")){
                        browser.navigate().to("https://a3.digid.nl");
                    }
                }
            }
        }
        browser.manage().window().maximize();

    }


    @And("^ik navigeer naar \"([^\"]*)\"$")
    public void ikNavigeerNaar(String linkName) throws Throwable {
        browser.findElement(By.linkText(linkName)).click();

    }

//    @And("^ik navigeer naar link \"([^\"]*)\"$")
//    public void ikNavigeerNaarLink(String linkName) throws Throwable {
//        browser.findElement(By.linkText(linkName)).click();
//
//    }

    @And("^ik vul mijn bsn \"([^\"]*)\" in$")
    public void ikVulMijnBsnIn(String bsnNummer) throws Throwable {
        browser.findElement(By.id("registration_burgerservicenummer")).sendKeys(bsnNummer);
    }

    @And("^ik vul mijn geboortedatum \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\" in$")
    public void ikVulMijnGeboortedatumIn(String geboorteDag, String geboorteMaand, String geboorteJaar) throws Throwable {
        browser.findElement(By.id("registration_geboortedatum_dag")).sendKeys(geboorteDag);
        browser.findElement(By.id("registration_geboortedatum_maand")).sendKeys(geboorteMaand);
        browser.findElement(By.id("registration_geboortedatum_jaar")).sendKeys(geboorteJaar);
    }

    @And("^ik vul mijn adres \"([^\"]*)\",\"([^\"]*)\" in$")
    public void ikVulMijnAdresIn(String postcode, String huisnummer) throws Throwable {
        browser.findElement(By.id("registration_postcode")).sendKeys(postcode);
        browser.findElement(By.id("registration_huisnummer")).sendKeys(huisnummer);
        getscreenshot();
    }

    @And("^ik klik op de knop Volgende$")
    public void alsIkOpDeKnopKlik() throws Throwable {
        browser.findElement(By.id("submit-button")).click();
        Thread.sleep(4000);
    }

    @Then("^kom ik op een scherm met de h1-titel \"([^\"]*)\"$")
    public void komIkOpEenSchermMetDeh1Titel(String h1Title) throws Throwable {
        WebElement actualh1Title = browser.findElement(By.cssSelector("#main_content > h1"));
        String expectedH1Title = h1Title;
        Assert.assertEquals(actualh1Title.getText(), expectedH1Title);
    }

    @And("^ik wacht op de GBA controle$")
    public void ikWachtIkTotHetWachtschermVerdwenenIs() throws Throwable {
        browser.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    @When("^ik voer \"([^\"]*)\" als gebruikersnaam in$")
    public void ikVoerAlsGebruikersnaamIn(String userName) throws Throwable {
        browser.findElement(By.id("account_gebruikersnaam")).sendKeys(userName);
        getscreenshot();
    }

    @And("^ik voer \"([^\"]*)\" als wachtwoord in$")
    public void ikVoerAlsWachtwoordIn(String password) throws Throwable {
        browser.findElement(By.id("account_password_authenticator_attributes_password")).sendKeys(password);
    }

    @And("^ik voer \"([^\"]*)\" als wachtwoord-bevestiging in$")
    public void ikVoerAlsWachtwoordBevestigingIn(String passwordConfirmation) throws Throwable {
        browser.findElement(By.id("account_password_authenticator_attributes_password_confirmation")).sendKeys(passwordConfirmation);

    }

    @And("^ik voer \"([^\"]*)\" als telefoonnummer in$")
    public void ikVoerAlsTelefoonnummerIn(String telephoneNumber) throws Throwable {
        browser.findElement(By.id("account_sms_tools_attributes_0_phone_number")).sendKeys(telephoneNumber);
    }

    @And("^ik voer \"([^\"]*)\" als e-mailadres in$")
    public void ikVoerAlsEMailadresIn(String emailAddress) throws Throwable {
        browser.findElement(By.id("account_email_attributes_adres")).sendKeys(emailAddress);
    }

    @Then("^kom ik op een scherm met de titel \"([^\"]*)\"$")
    public void komIkOpEenSchermMetDeTitel(String pageTitle) throws Throwable {
        String actualTitle = browser.getTitle();
        String expectedPageTitle = pageTitle;
        Assert.assertEquals(expectedPageTitle, actualTitle);
        System.out.println("De pagina titel is" +actualTitle);
    }

    @When("^ik open een nieuw tabblad$")
    public void ikNavigeerNaarConfigInEenNieuwTabblad() throws Throwable {
        ((JavascriptExecutor) browser).executeScript("window.open()");
    }

    @And("^ik haal de smscode van de eindgebruiker met bsn \"([^\"]*)\" op uit config op \"([^\"]*)\" en vul deze in het sms-invoerveld in$")
    public void ikHaalDeSmscodeVanDeEindgebruikerMetOpEnVulDezeInHetSmsInvoerveldIn(String bsn, String omgeving) throws Throwable {
        ArrayList<String> tabs = new ArrayList<String>(browser.getWindowHandles());
        browser.switchTo().window(tabs.get(1));
        if (omgeving.equals("t1"))browser.navigate().to("https://config-t1.digid.nl");
        else {
            if (omgeving.equals("t2"))browser.navigate().to("https://config-t2.digid.nl");
            else {
                if (omgeving.equals("o1")) {
                    browser.navigate().to("https://config-o1.digid.nl");
                } else {
                    if (omgeving.equals("a3")){
                        browser.navigate().to("https://config-a3.digid.nl");
                    }
                }
            }
        }
        browser.findElement(By.linkText("Laatste gewijzigde accounts")).click();
        browser.findElement(By.id("bsn")).sendKeys(bsn);
        browser.findElement(By.cssSelector("input[value='" + "Zoek" + "']")).click();
        String smscode_voor_aanvragen = browser.findElement(By.cssSelector("#codes > tbody > tr:nth-child(2) > td:nth-child(7)")).getText();
        browser.switchTo().window(tabs.get(0));
        browser.findElement(By.id("smscode_smscode_field_0")).sendKeys(smscode_voor_aanvragen);
        getscreenshot();
    }

    @And("^ik haal de emailcode van de eindgebruiker met bsn \"([^\"]*)\" op uit config op \"([^\"]*)\" en vul deze in het email-invoerveld in$")
    public void ikHaalDeEmailcodeVanDeEindgebruikerMetOpUitConfigOpTEnVulDezeInHetEmailInvoerveldIn(String bsn, String omgeving ) throws Throwable {
        ArrayList<String> tabs = new ArrayList<String>(browser.getWindowHandles());
        browser.switchTo().window(tabs.get(1));
        if (omgeving.equals("t1"))browser.navigate().to("https://config-t1.digid.nl");
        else {
            if (omgeving.equals("t2"))browser.navigate().to("https://config-t2.digid.nl");
            else {
                if (omgeving.equals("o1")) {
                    browser.navigate().to("https://config-o1.digid.nl");
                } else {
                    if (omgeving.equals("a3")){
                        browser.navigate().to("https://config-a3.digid.nl");
                    }
                }
            }
        }
        browser.findElement(By.linkText("Laatste gewijzigde accounts")).click();
        browser.findElement(By.id("bsn")).sendKeys(bsn);
        browser.findElement(By.cssSelector("input[value='" + "Zoek" + "']")).click();
        String emailcode_voor_aanvragen = browser.findElement(By.cssSelector("#codes > tbody > tr:nth-child(2) > td:nth-child(6)")).getText();
        browser.switchTo().window(tabs.get(0));
        browser.findElement(By.id("email_code_code")).sendKeys(emailcode_voor_aanvragen);
        getscreenshot();
    }

    @And("^moet ik de melding \"([^\"]*)\" zien$")
    public void moetDePaginaDeTekstBevatten(String pageText) throws Throwable {
        List<WebElement> list = browser.findElements(By.xpath("//*[contains(text(),'" + pageText + "')]"));
        Assert.assertTrue("Text not found!", list.size() > 0);
    }

    @And("^ik kies voor het activeren van mijn DigiD$")
    public void ikKiesVoorHetActiverenVanMijnDigiD() throws Throwable {
        browser.findElement(By.id("authentication_activation_type_account")).click();
    }

    @And("^ik voer mijn gebruikersnaam \"([^\"]*)\" in$")
    public void ikVoerMijnGebruikersnaamIn(String username) throws Throwable {
        browser.findElement(By.id("authentication_digid_username")).sendKeys(username);
    }

    @And("^ik voer mijn wachtwoord \"([^\"]*)\" in$")
    public void ikVoerMijnWachtwoordIn(String password) throws Throwable {
        browser.findElement(By.id("authentication_wachtwoord")).sendKeys(password);
    }

    @And("^ik klik op de knop \"([^\"]*)\"$")
    public void ikKlikOpDeKnop(String buttonName) throws Throwable {
        browser.findElement(By.cssSelector("input[value='" + buttonName + "']")).click();
        browser.takeScreenShot();
    }

//    @And("^ik zie een verplicht veld voor \"([^\"]*)\"$")
//    public void ikZieEenVerplichtVeldVoor(String dataRequired) throws Throwable {
//        List<WebElement> list = browser.findElements(By.xpath("//*[contains(text(),'" + dataRequired + "')]"));
//        Assert.assertTrue("Text not found!", list.size() > 0);
//        browser.['data-required']) == not nil
//    }

    @And("^ik haal de activeringscode van de eindgebruiker met bsn \"([^\"]*)\" op uit config op \"([^\"]*)\" en vul deze in het activeringscodeveld in$")
    public void ikHaalDeActiveringscodeVanDeEindgebruikerMetBsnOpUitConfigOpEnVulDezeInHetActiveringscodeveldIn(String bsn, String omgeving) throws Throwable {
            ArrayList<String> tabs = new ArrayList<String>(browser.getWindowHandles());
            browser.switchTo().window(tabs.get(1));
            if (omgeving.equals("t1"))browser.navigate().to("https://config-t1.digid.nl");
            else {
                if (omgeving.equals("t2"))browser.navigate().to("https://config-t2.digid.nl");
                else {
                    if (omgeving.equals("o1")) {
                        browser.navigate().to("https://config-o1.digid.nl");
                    } else {
                        if (omgeving.equals("a3")){
                            browser.navigate().to("https://config-a3.digid.nl");
                        }
                    }
                }
            }
            browser.findElement(By.linkText("Laatste gewijzigde accounts")).click();
            browser.findElement(By.id("bsn")).sendKeys(bsn);
            browser.findElement(By.name("commit")).click();
            String activeringscode = browser.findElement(By.cssSelector("#codes > tbody > tr:nth-child(2) > td:nth-child(8)")).getText();
            browser.switchTo().window(tabs.get(0));
            browser.findElement(By.id("activationcode_activationcode")).sendKeys(activeringscode);
            getscreenshot();
        }

    @And("^ik wacht tot het wachtscherm verdwenen is$")
    public void ikWachtTotHetWachtschermVerdwenenIs() throws Throwable {
            WebDriverWait wait = new WebDriverWait(browser, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[aria-labelledby='ui-id-4']")));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#.message-dialog")));
    }
}