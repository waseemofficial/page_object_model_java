package com.pageobjectmodel.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.pageobjectmodel.base.BaseClass;
import com.pageobjectmodel.dataprovider.DataProviders;
import com.pageobjectmodel.pageobjects.HomePage;
import com.pageobjectmodel.pageobjects.IndexPage;
import com.pageobjectmodel.pageobjects.LoginPage;
import com.pageobjectmodel.utility.Log;

public class HomePageTest extends BaseClass {

    private IndexPage indexPage;
    private LoginPage loginPage;
    private HomePage homePage;

    @Parameters("firefox")
    @BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void setup(@Optional("firefox") String browser) throws Throwable {
        launchApp(browser);
    }

    @Test(groups = "Smoke", dataProvider = "credentials", dataProviderClass = DataProviders.class)
    public void wishListTest(String uname, String pswd) throws Throwable {
        Log.startTestCase("wishListTest");
        indexPage = new IndexPage();
        loginPage = indexPage.clickOnSignIn();
        homePage = loginPage.login(uname, pswd, homePage);
        boolean result = homePage.validateMyWishList();
        Assert.assertTrue(result);
        Log.endTestCase("wishListTest");
    }

    @Test(groups = "Smoke", dataProvider = "credentials", dataProviderClass = DataProviders.class)
    public void orderHistoryandDetailsTest(String uname, String pswd) throws Throwable {
        Log.startTestCase("orderHistoryandDetailsTest");
        indexPage = new IndexPage();
        loginPage = indexPage.clickOnSignIn();
        homePage = loginPage.login(uname, pswd, homePage);
        boolean result = homePage.validateOrderHistory();
        Assert.assertTrue(result);
        Log.endTestCase("orderHistoryandDetailsTest");
    }

    @AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void tearDown() {
        driver.get().quit();
    }

}
