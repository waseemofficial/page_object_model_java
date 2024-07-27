package com.pageobjectmodel.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pageobjectmodel.base.BaseClass;
import com.pageobjectmodel.dataprovider.DataProviders;
import com.pageobjectmodel.pageobjects.HomePage;
import com.pageobjectmodel.pageobjects.IndexPage;
import com.pageobjectmodel.pageobjects.LoginPage;
import com.pageobjectmodel.utility.Log;

public class LoginPageTest extends BaseClass {

    private IndexPage indexPage;
    private LoginPage loginPage;
    private HomePage homePage;

    @Parameters("browser")
    @BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void setup(@Optional("firefox") String browser) {
        launchApp(browser);
    }

    @Test(groups = { "Smoke", "Sanity" }, dataProvider = "credentials", dataProviderClass = DataProviders.class)
    public void loginTest(String uname, String pswd) throws Throwable {
        Log.startTestCase("loginTest");
        indexPage = new IndexPage();
        Log.info("user is going to click on SignIn");
        loginPage = indexPage.clickOnSignIn();
        Log.info("Enter Username and Password");
        // homePage=loginPage.login(prop.getProperty("username"),
        // prop.getProperty("password"));
        homePage = loginPage.login(uname, pswd, homePage);
        String actualURL = homePage.getCurrURL();
        String expectedURL = "http://www.automationpractice.pl/index.php?controller=authentication";
        Log.info("Verifying if user is able to login");
        Assert.assertEquals(actualURL, expectedURL);
        Log.info("Login is Sucess");
        Log.endTestCase("loginTest");
    }

    @AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void tearDown() {
        getDriver().quit();
    }
}
