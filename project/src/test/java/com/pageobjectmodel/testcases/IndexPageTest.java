package com.pageobjectmodel.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pageobjectmodel.base.BaseClass;
import com.pageobjectmodel.pageobjects.IndexPage;
import com.pageobjectmodel.utility.Log;

public class IndexPageTest extends BaseClass {

    private IndexPage indexPage;

    @Parameters("browser")
    @BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void setup(@Optional("firefox") String browser) {
        launchApp(browser);
        indexPage = new IndexPage();
    }

    @Test(groups = "Smoke")
    public void verifyLogo() throws Throwable {
        Log.startTestCase("verifyLogo");
        // indexPage = new IndexPage();
        // boolean result = indexPage.validateLogo();
        Assert.assertTrue(indexPage.validateLogo());
        Log.endTestCase("verifyLogo");
    }

    @Test(groups = "Smoke")
    public void verifyTitle() {
        Log.startTestCase("verifyTitle");
        String actTitle = indexPage.getMyStoreTitle();
        Assert.assertEquals(actTitle, "My Shop");
        Log.endTestCase("verifyTitle");
    }

    @AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void tearDown() {
        // driver.quit();
        if (getDriver() != null) {
            getDriver().quit();
        }
    }
}
