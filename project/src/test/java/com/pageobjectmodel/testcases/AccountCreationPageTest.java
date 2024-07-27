package com.pageobjectmodel.testcases;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pageobjectmodel.base.BaseClass;
import com.pageobjectmodel.dataprovider.DataProviders;
import com.pageobjectmodel.pageobjects.AccountCreationPage;
import com.pageobjectmodel.pageobjects.HomePage;
import com.pageobjectmodel.pageobjects.IndexPage;
import com.pageobjectmodel.pageobjects.LoginPage;
import com.pageobjectmodel.utility.Log;

public class AccountCreationPageTest extends BaseClass {

    private IndexPage indexPage;
    private LoginPage loginPage;
    private AccountCreationPage acountCreationPage;
    private HomePage homePage;
    private WebDriverWait wait;

    @Parameters("browser")
    @BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void setup(@Optional("firefox") String browser) {
        launchApp(browser);
    }

    @Test(groups = "Sanity", dataProvider = "email", dataProviderClass = DataProviders.class)
    public void verifyCreateAccountPageTest(String email) throws Throwable {
        Log.startTestCase("verifyCreateAccountPageTest");
        indexPage = new IndexPage();
        loginPage = indexPage.clickOnSignIn();
        acountCreationPage = loginPage.createNewAccount(email);
        boolean result = acountCreationPage.validateAcountCreatePage();
        Assert.assertTrue(result);
        Log.endTestCase("verifyCreateAccountPageTest");
    }

    @Test(groups = "Regression", dataProvider = "newAcountDetailsData", dataProviderClass = DataProviders.class)
    public void createAccountTest(HashMap<String, String> hashMapValue) throws Throwable {
        Log.startTestCase("createAccountTest");
        indexPage = new IndexPage();
        loginPage = indexPage.clickOnSignIn();
        acountCreationPage = loginPage.createNewAccount(hashMapValue.get("Email"));
        // wait.until(
        // ExpectedConditions.visibilityOfElementLocated(By.id("account-creation_form")));
        Thread.sleep(2000);
        acountCreationPage.createAccount(
                hashMapValue.get("Gender"),
                hashMapValue.get("FirstName"),
                hashMapValue.get("LastName"),
                hashMapValue.get("SetPassword"),
                hashMapValue.get("Day"),
                hashMapValue.get("Month"),
                hashMapValue.get("Year"),
                hashMapValue.get("Company"),
                hashMapValue.get("Address"),
                hashMapValue.get("City"),
                hashMapValue.get("State"),
                hashMapValue.get("Zipcode"),
                hashMapValue.get("Country"),
                hashMapValue.get("MobilePhone"));
        homePage = acountCreationPage.validateRegistration();

        Assert.assertEquals("http://www.automationpractice.pl/index.php?controller=my-account", homePage.getCurrURL());
        Log.endTestCase("createAccountTest");
    }

    @AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void tearDown() {
        getDriver().quit();
    }
}
