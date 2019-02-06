package com.capgemini.steps;

import com.capgemini.ourWebdriver.BrowserFactory;
import com.capgemini.pages.InlogPagina;
import cucumber.api.PendingException;
import com.capgemini.ourWebdriver.OurFirefoxDriver;
import com.capgemini.ourWebdriver.OurWebDriver;
import com.github.javafaker.Faker;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import cucumber.api.java.nl.Als;
import cucumber.api.java.nl.Dan;
import cucumber.api.java.nl.En;
import cucumber.api.java.nl.Gegeven;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class SSDDigiD {
//    private Properties elements;
//    private Properties config;
    private WebDriver browser;
//    private WebDriverWait wait;
    private String testContext = "";
    private HttpsURLConnection urlCon;
//    private HttpUtil httpUtil = new HttpUtil();
//    private CookieUtil cookieUtil = new CookieUtil();
//    private ZoekenPage zoekenPage = new ZoekenPage();
//    private LoginPage loginPage = new LoginPage();
//    private HoofdPage hoofdPage = new HoofdPage();
//    private FormulierPage formulierPage = new FormulierPage();
//    private BasePage basePage = new BasePage();
    private Cookie cookie;
    private InlogPagina inlogPagina = new InlogPagina();


//    public SSDDigiD() {
//        this.driver = StepHooks.driver;
//        this.elements = StepHooks.driver;
//        this.config = StepHooks.config;
//        this.wait = StepHooks.wait;
//    }

    public SSDDigiD() throws MalformedURLException {
        browser = BrowserFactory.getWebDriver();
        }

    @Als("^een hacker kijkt naar de broncode$")
    public void eenHackerKijktNaarDeBroncode() {
        testContext = browser.getPageSource();
    }

    @Dan("^ziet een hacker geen ongeregistreerde commentaarregels$")
    public void zietEenHackerGeenOngeregistreerdeCommentaarregels() {
        Boolean testresult = true;
        //TODO: get the regex for comments from the config file
        Pattern commentSyntax = Pattern.compile("(?s)<!--.*?-->");
        Matcher m = commentSyntax.matcher(testContext);
        //For increased speed the whitelist is loaded here once to be used for comparison with the matcher later
        //TODO: get the whitelist location from the config file
        File whitelistFile = new File("properties\\whitelists\\CommentaryWhitelist");
        String whitelist = "";
        Scanner scanner;
        try {
            scanner = new Scanner(whitelistFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                whitelist = whitelist.concat(line) + System.getProperty("line.separator");
            }
        } catch (FileNotFoundException e) {
            // TODO: add proper logging here if required
            System.out.println("Whitelist file niet gevonden.");
        }
        while (m.find()) {
            if (whitelist.contains(m.group(0))) {
                // TODO: add proper logging here if required
                System.out.println(m.group(0) + " is een whitelisted comment.");
            } else {
                // TODO: add proper logging here if required
                testresult = false;
                System.out.println(m.group(0) + " is geen whitelisted comment.");
                //break;
            }
        }
        // Jurn: Deze try/catch is impliciet, je kan beter de assertion melding uitbreiden;
        // Assert.assertTrue("De pagina bevat wél ongeregistreerde comments", testresult);
        try {
            Assert.assertTrue(testresult);
        } catch (AssertionError e) {
            System.out.println("De pagina bevat wél ongeregistreerde comments");
            throw e;

        }


    }


//    @Gegeven("^een externe gebruiker opent de \"([^\"]*)\"$")
//    public void eenexternegebruikerOpent(String Pagina) throws Throwable {
//        String websiteUrl = elements.getProperty("url." + Pagina);
//        browser.get(websiteUrl);
//    }


    @Als("^een hacker kijkt naar de inhoud van de cookies van een externe gebruiker$")
    public void eenHackerKijktNaarDeInhoudVanDeCookiesVanEenExterneGebruiker() throws Throwable {
        Cookie cookie = browser.manage().getCookieNamed("cookie_name");
        testContext = cookie.toString();
//        webDriver.manage().deleteCookie(cookie);
//        webDriver.manage().addCookie(
//                new Cookie.Builder(cookie.getName(), cookie.getValue() + "abc")
//                        .domain(cookie.getDomain())
//                        .expiresOn(cookie.getExpiry())
//                        .path(cookie.getPath())
//                        .isSecure(cookie.isSecure())
//                        .build()
//        );
    }

    @Dan("^ziet een hacker geen ongeregistreerde waarden in de HTML-headers$")
    public void zietEenHackerGeenOngeregistreerdeWaardenInDeHTMLHeaders() throws Throwable {


        Boolean testresult = true;
        //TODO: get the regex for comments from the config file
        Pattern commentSyntax = Pattern.compile("(?s)<(meta|param|object|input).*?>");
        Matcher m = commentSyntax.matcher(testContext);
        //For increased speed the whitelist is loaded here once to be used for comparison with the matcher later
        //TODO: get the whitelist location from the config file
        File whitelistFile = new File("properties/whitelists/HTMLHeadersWhitelist");
        String whitelist = "";
        Scanner scanner;
        try {
            scanner = new Scanner(whitelistFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                whitelist = whitelist.concat(line) + System.getProperty("line.separator");
            }
        } catch (FileNotFoundException e) {
            // TODO: add proper logging here if required
            System.out.println("Whitelist file niet gevonden.");
        }
        while (m.find()) {
            if (whitelist.contains(m.group(0))) {
                // TODO: add proper logging here if required
                //System.out.println(m.group(0)+ " is een whitelisted header.");
            } else {
                // TODO: add proper logging here if required
                testresult = false;
                //System.out.println(m.group(0)+ " is geen whitelisted header.");
                //break;
            }
        }
        Assert.assertTrue(testresult);
    }

    @Dan("^ziet een hacker geen ongeregistreerde informatie in de headers$")
    public void zietEenHackerGeenOngeregistreerdeInformatieInDeHeaders() throws Throwable {
        Boolean testresult = true;
        //TODO: get the regex for HTTP-headers from the config file
        Pattern commentSyntax = Pattern.compile("Header Value:.*? ");
        Matcher m = commentSyntax.matcher(testContext);
        //For increased speed the whitelist is loaded here once to be used for comparison with the matcher later
        //TODO: get the whitelist location from the config file
        File whitelistFile = new File("properties\\whitelists\\HTTPHeadersWhitelist");
        String whitelist = "";
        Scanner scanner;
        try {
            scanner = new Scanner(whitelistFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                whitelist = whitelist.concat(line) + System.getProperty("line.separator");
            }
        } catch (FileNotFoundException e) {
            // TODO: add proper logging here if required
            System.out.println("Whitelist file niet gevonden.");
        }
        while (m.find()) {
            if (m.group(0).contains("GMT")) {
                continue;
            }
            if (whitelist.contains(m.group(0))) {
                // TODO: add proper logging here if required
                //System.out.println(m.group(0)+ " is een whitelisted HTTP-header waarde.");
            } else {
                // TODO: add proper logging here if required
                testresult = false;
                //System.out.println(m.group(0)+ " is geen whitelisted HTTP-header waarde.");
                //break;
            }
        }
        Assert.assertTrue(testresult);
    }

    @Dan("^ziet een hacker geen ongeregistreerde informatie in de server header$")
    public void zietEenHackerGeenOngeregistreerdeInformatieInDeServerHeader() throws Throwable {
        Boolean testresult = true;
        //TODO: get the regex for HTTP-headers from the config file
        //TODO: maybe get these values from an excel sheet/database instead
        Pattern commentSyntax = Pattern.compile("Header Name:(server|SERVER).*? Header Value:.*? ");
        Matcher m = commentSyntax.matcher(testContext);
        //For increased speed the whitelist is loaded here once to be used for comparison with the matcher later
        //TODO: get the whitelist location from the config file
        File whitelistFile = new File("properties\\whitelists\\HTTPHeadersWhitelist");
        String whitelist = "";
        Scanner scanner;
        try {
            scanner = new Scanner(whitelistFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                whitelist = whitelist.concat(line) + System.getProperty("line.separator");
            }
        } catch (FileNotFoundException e) {
            // TODO: add proper logging here if required
            System.out.println("Whitelist file niet gevonden.");
        }
        while (m.find()) {
            if (whitelist.contains(m.group(0))) {
                // TODO: add proper logging here if required
                //System.out.println(m.group(0)+ " is een whitelisted HTTP-header waarde.");
            } else {
                // TODO: add proper logging here if required
                testresult = false;
                //System.out.println(m.group(0)+ " is geen whitelisted HTTP-header waarde.");
                //break;
            }
        }
        Assert.assertTrue(testresult);
    }

    @En("^een hacker selecteerd 'ik heb werk gevonden'$")
    public void eenHackerSelecteerdIkHebWerkGevonden() throws Throwable {
        browser.findElement(By.cssSelector("#wijzigingen_werkGevonden")).click();
    }

    @Dan("^krijgt een hacker de tekst \"([^\"]*)\" te zien$")
    public void krijgteenHackerDeTekstTeZien(String arg0) throws Throwable {

    }

    @Dan("^krijgt een hacker de foutmelding te zien$")
    public void krijgteenHackerDeFoutmeldingTeZien() throws Throwable {
        assertTrue(browser.findElement(By.cssSelector("#wnltErrors")).getText().contains("Pas de volgende velden aan:"));
    }

    @Dan("^krijgt een hacker de standaard melding te zien$")
    public void krijgteenHackerDeStandaardmeldingTeZien() throws Throwable {
        assertTrue(browser.findElement(By.cssSelector("#ct_eenvoudig_artikel_werkmap_spring_portlet")).getText().contains("Ik heb werk gevonden"));

    }


    @En("^een hacker klikt op de knop \"([^\"]*)\"$")
    public void eenHackerKliktOpDeKnop(String arg0) throws Throwable {
        browser.findElement(By.cssSelector("[type='submit'][value='" + arg0 + "']")).click();
    }


    @Dan("^ziet een hacker geen ongeregistreerde headers in de HTTP-response$")
    public void zietEenHackerGeenOngeregistreerdeHeadersInDeHTTPResponse() throws Throwable {
        Boolean testresult = true;
        Pattern commentSyntax = Pattern.compile("Header Name:.*? ");
        Matcher m = commentSyntax.matcher(testContext);
        //For increased speed the whitelist is loaded here once to be used for comparison with the matcher later
        //TODO: get the whitelist location from the config file
        File whitelistFile = new File("properties\\whitelists\\HTTPHeadersWhitelist");
        String whitelist = "";
        Scanner scanner;
        try {
            scanner = new Scanner(whitelistFile);

            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                whitelist = whitelist.concat(line) + System.getProperty("line.separator");
            }
        } catch (FileNotFoundException e) {
            // TODO: add proper logging here if required
            System.out.println("Whitelist file niet gevonden.");
        }
        while (m.find()) {
            if (whitelist.contains(m.group(0)))
            {
                // TODO: add proper logging here if required
                // System.out.println(m.group(0)+ " is een whitelisted HTTP-header.");
            }
            else
            {
                // TODO: add proper logging here if required
                testresult = false;
                //System.out.println(m.group(0)+ " is geen whitelisted HTTP-header.");
                //break;
            }
        }
        Assert.assertTrue(testresult);
    }


//    @Gegeven("^een hacker zet een HTTP-connectie op met \"([^\"]*)\"$")
//    public void eenHackerZetEenHTTPConnectieOpMet(String arg0) throws Throwable {
//        arg0 = arg0.replace(" ", "_");
//        String websiteUrl = elements.getProperty("url." + arg0);
//        URL url = new URL(websiteUrl);
//        urlCon = httpUtil.openConnection(url);
//    }

//    @Als("^een hacker kijkt naar de HTTP-headers$")
//    public void eenHackerKijktNaarDeHTTPHeaders() throws Throwable {
//        testContext = httpUtil.getStringHeaders(urlCon);
//    }

    @Als("^een hacker een javascript alert toevoegt aan de URL$")
    public void eenHackerEenJavascriptAlertToevoegtAanDeURL() throws Throwable {
        browser.get(browser.getCurrentUrl().concat("<script>alert(‘test’)</script>"));
    }

    @Dan("^ziet een hacker geen javascript alert$")
    public void zietEenHackerGeenJavascriptAlert() throws Throwable {
        Boolean testresult = false;
        try{browser.switchTo().alert().getText();}
        catch(NoAlertPresentException e){
            testresult = true;
        }
        assertTrue(testresult);
    }



//    @Dan("^komt een hacker niet op de \"([^\"]*)\" pagina$")
//    public void komtEenHackerNietOpDePagina(String arg0) throws Throwable {
//        arg0 = arg0.replace(" ", "_");
//        Boolean resultBool = elements.getProperty("url." + arg0).equals(browser.getCurrentUrl());
//        assertFalse(resultBool);
//    }

//    @Dan("^ziet een hacker geen ongeregistreerde cookies$")
//    public void zietEenHackerGeenOngeregistreerdeCookies() throws Throwable {
//        assertTrue(cookieUtil.verifyContextWithCookieWhitelist(testContext, config.getProperty("CookieWhitelist")));
//    }

//    @Als("^een hacker kijkt naar de cookies van de gebruiker$")
//    public void eenHackerKijktNaarDeCookiesVanDeGebruiker() throws Throwable {
//        testContext = cookieUtil.getAllCookieNames(driver);
//    }

//    @Als("^een hacker de sessie cookie gebruikt om een nieuwe sessie op te zetten$")
//    public void eenHackerDeSessieCookieGebruiktOmEenNieuweSessieOpTeZetten() throws Throwable {
//        testContext = cookieUtil.getCookieValueByName(driver, elements.getProperty("cookie.sessiecookie"));
//
//    }
//
//    @Als("^een hacker het formulierveld vult met \"([^\"]*)\"$")
//    public void eenHackerHetFormulierveldVultMet(String arg0) throws Throwable {
//        FormulierPage.setFormulierveldWaarde(driver, arg0);
//    }

    @Als("^een hacker de GET-request verandert naar een OPTIONS-request$")
    public void eenHackerDeGETRequestVerandertNaarEenOPTIONSRequest() throws Throwable {
        urlCon.setRequestMethod("OPTIONS");
    }

//    @Als("^een externe gebruiker verbind met de \"([^\"]*)\" zonder encryptie$")
//    public void eenExterneGebruikerVerbindMetDeZonderEncryptie(String arg0) throws Throwable {
//        String websiteUrl = elements.getProperty("url." + arg0);
//        String unencryptedWebsiteUrl = websiteUrl.replace("https://", "http://");
//        browser.get(unencryptedWebsiteUrl);
//    }

    @Dan("^ziet de externe gebruiker dat zijn verbinding toch encrypted is$")
    public void zietDeExterneGebruikerDatZijnVerbindingTochEncryptedIs() throws Throwable {
        Assert.assertTrue(browser.getCurrentUrl().contains("https://"));
    }

//    @Als("^een hacker een pagina opent voor geauthenticeerde gebruikers$")
//    public void eenHackerEenPaginaOpentVoorGeauthenticeerdeGebruikers() throws Throwable {
//        browser.get(elements.getProperty("url.externegeauthenticeerdepagina"));
//    }

//    @Dan("^komt een hacker niet op een pagina voor geauthenticeerde gebruikers$")
//    public void komteenHackerNietOpEenPaginaVoorGeauthenticeerdeGebruikers() throws Throwable {
//        Assert.assertFalse(browser.getCurrentUrl().contains(elements.getProperty("url.externegeauthenticeerdepagina")));
//    }

//    @En("^een externe gebruiker logt in$")
//    public void eenExterneGebruikerLogtIn() throws Throwable {
//        ZoekenPage.loginExterneGebruiker(driver);
//    }

//    @Als("^een externe gebruiker voor lange tijd inactief is$")
//    public void eenExterneGebruikerVoorLangeTijdInactiefIs() throws Throwable {
//        wait.wait(1);
//        //wait.wait(Integer.parseInt(config.getProperty("SessieTimeoutInSeconden")));
//    }

//    @Dan("^is een externe gebruiker uitgelogd$")
//    public void isEenExterneGebruikerUitgelogd() throws Throwable {
//        Assert.assertTrue(browser.getCurrentUrl().contains(elements.getProperty("url.externeloginpagina")));
//    }
//
//    @En("^een hacker kopieert de sessie cookie$")
//    public void eenHackerKopieertDeSessieCookie() throws Throwable {
//        cookie = browser.manage().getCookieNamed(elements.getProperty("cookie.sessiecookie"));
//    }

//    @En("^een externe gebruiker logt uit$")
//    public void eenExterneGebruikerLogtUit() throws Throwable {
//        basePage.logout(browser);
//    }

//    @Als("^een hacker met de sessie cookie een pagina opent voor geauthenticeerde gebruikers$")
//    public void eenHackerMetDeSessieCookieEenPaginaOpentVoorGeauthenticeerdeGebruikers() throws Throwable {
//        browser.manage().addCookie(cookie);
//        browser.get(elements.getProperty("url.externegeauthenticeerdepagina"));
//    }

//    @Dan("^ziet een hacker dat de inhoud van de sessie cookie is veranderd$")
//    public void zietEenHackerDatDeInhoudVanDeSessieCookieIsVeranderd() throws Throwable {
//        Assert.assertNotEquals(cookie.getValue(), browser.manage().getCookieNamed(elements.getProperty("cookie.sessiecookie")).getValue());
//    }

//    @Als("^een hacker de sessie kopieert met een ongeldige sessie cookie$")
//    public void eenHackerDeSessieKopieertMetEenOngeldigeSessieCookie() throws Throwable {
//        cookieUtil.setCookieNamed(driver, elements.getProperty("cookie.sessiecookie"), "testvalue");
//    }

    @Dan("^komt een hacker op een scherm met de titel \"([^\"]*)\"$")
    public void komtEenHackerOpEenSchermMetDeTitel(String pageTitle) throws Throwable {
        String actualTitle = browser.getTitle();
        junit.framework.Assert.assertEquals(pageTitle, actualTitle);
        System.out.println("De pagina titel is" +actualTitle);
    }


//    @Gegeven("^een burger opent de \"([^\"]*)\"$")
//    public void eenBurgerOpentDe(String Pagina) throws Throwable {
//        String websiteUrl = elements.getProperty("url." + Pagina);
//        browser.get(websiteUrl);
//
//    }
//
//    @Gegeven("^een burger opent de hoofdpagina$")
//    public void eenBurgerOpentDe() throws Throwable {
//        String websiteUrl = elements.getProperty("url.hoofdpagina");
//
//    }

    @En("^een burger navigeert naar \"([^\"]*)\"$")
    public void eenBurgerNavigeertNaar(String linkName) throws Throwable {
        browser.findElement(By.linkText(linkName)).click();
    }

    @En("^een burger logt in met gebruikersnaam \"([^\"]*)\" en wachtwoord \"([^\"]*)\"$")
    public void eenBurgerLogtInMetGebruikersnaamEnWachtwoord(String gebruikersnaam, String wachtwoord) throws Throwable {
        browser.findElement(By.id("account_gebruikersnaam")).sendKeys();
        browser.findElement(By.id("account_password_authenticator_attributes_password")).sendKeys(wachtwoord);
    }

    @En("^een burger kiest voor 'Ik wil inloggen met gebruikersnaam en wachtwoord'$")
    public void eenBurgerKiestVoorIkWilInloggenMetGebruikersnaamEnWachtwoord() throws Throwable {
        browser.findElement(By.id("authentication_type_account_basis")).click();
    }

    @Gegeven("^een burger opent de inlogpagina$")
    public void eenBurgerOpentDeInlogpagina() throws Throwable {
        browser.navigate().to("https://www.t1.digid.nl");
    }
}

