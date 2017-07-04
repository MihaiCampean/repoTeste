package com.company;

import org.testng.annotations.*;
import com.hp.lft.sdk.*;
import com.hp.lft.report.*;
import com.hp.lft.sdk.web.*;
import com.hp.lft.verifications.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase {
    public static final String USERNAME = "johnhpe1";
    public static final String PASSWORD = "HPEsw123";
    public static String appURL = "http://advantageonlineshopping.com/";

    protected static Browser browser;

    @BeforeClass
    public void beforeClass() throws Exception {
    }

    @AfterClass
    public void afterClass() throws Exception {

    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
    }

    @AfterMethod
    public void afterMethod() throws Exception {
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("johnhpe1 My account My Orders Sign out ").build()).click();
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("LABEL").innerText("Sign out").build()).click();

    }

    @DataProvider(parallel = true)
    public Object[][] RuleazaBrowser(){

        BrowserType[][] browser = {
                {BrowserType.FIREFOX},
                {BrowserType.CHROME}

        };

        return browser;
    }
    public boolean waitUntilElementExists(WebElement webElem) throws GeneralLeanFtException
    {
        return WaitUntilTestObjectState.waitUntil(webElem,new WaitUntilTestObjectState.WaitUntilEvaluator<WebElement>(){
            public boolean evaluate(WebElement we){
                try{
                    return we.exists() && we.isVisible();
                }
                catch(Exception e){
                    return false;
                }
            }
        });
    }

    @Test(dataProvider = "RuleazaBrowser")
    public void RunningBrowser(BrowserType browserType) throws Exception {
        // Lanseaza browserul
        browser = BrowserFactory.launch(browserType);

        // Navigheaza catre site-ul web
        browser.navigate(appURL);
        browser.sync();

        //Apasa buton Boxe
        waitUntilElementExists(browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("DIV").innerText("SPEAKERS Shop Now ").build()));
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("DIV").innerText("SPEAKERS Shop Now ").build()).click();
        waitUntilElementExists(browser.describe(WebElement.class, new WebElementDescription.Builder()
                        .tagName("A").innerText("Bose Soundlink Bluetooth Speaker III").build()));
        //Selecteaza boxa "Bose"
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("A").innerText("Bose Soundlink Bluetooth Speaker III").build()).click();
        //Adauga boxa in cosul de cumparaturi
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit").tagName("BUTTON").name("ADD TO CART").build()).click();
        //apasa buton utilizator
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("My account My Orders Sign out ").build()).click();
        //logIn
        browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("text").tagName("INPUT").name("username").build()).setValue(USERNAME);
        browser.describe(EditField.class, new EditFieldDescription.Builder()
                .type("password").tagName("INPUT").name("password").build()).setValue(PASSWORD);
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("button").tagName("BUTTON").name("SIGN IN").build()).click();
        //revin la pagina principala
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("A").innerText("HOME").build()).click();
        waitUntilElementExists(browser.describe(Link.class, new LinkDescription.Builder()
                        .tagName("DIV").innerText("TABLETS Shop Now ").build()));
        //Apasa buton Tablete
        browser.describe(Link.class, new LinkDescription.Builder()
                .tagName("DIV").innerText("TABLETS Shop Now ").build()).click();
        //Selecteaza tableta "HP PRO"
        browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("A").innerText("HP Pro Tablet 608 G1").build()).click();
        //schimba culoarea tabletei
//        browser.describe(WebElement.class, new WebElementDescription.Builder()
//                .className("productColor ng-scope").tagName("SPAN").innerText("").build()).click();
        //Adauga tableta in cosul de cumparaturi
        browser.sync();
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit").tagName("BUTTON").name("ADD TO CART").build()).click();
        //accesare cos cumparaturi
        browser.describe(Link.class, new LinkDescription.Builder()
                .role("link").accessibilityName("").tagName("A").index(1).build()).click();
        //check-out
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit").tagName("BUTTON").className("roboto-medium ng-binding").build()).click();
        Verify.areEqual("Shabazi 19", browser.describe(WebElement.class, new WebElementDescription.Builder()
                .accessibilityName("").className("ng-binding").tagName("LABEL").innerText("Shabazi 19").index(0).build()).getInnerText());
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("submit").tagName("BUTTON").name("NEXT").build()).click();
        browser.describe(Button.class, new ButtonDescription.Builder()
                .buttonType("button").role("button").accessibilityName("").tagName("BUTTON").name("PAY NOW").index(0).build()).click();
        Verify.areEqual("Thank you for buying with Advantage", browser.describe(WebElement.class, new WebElementDescription.Builder()
                .tagName("SPAN").innerText("Thank you for buying with Advantage").build()).getInnerText());



    }

}