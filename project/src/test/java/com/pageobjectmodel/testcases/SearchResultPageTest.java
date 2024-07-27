package com.pageobjectmodel.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pageobjectmodel.base.BaseClass;
import com.pageobjectmodel.dataprovider.DataProviders;
import com.pageobjectmodel.pageobjects.IndexPage;
import com.pageobjectmodel.pageobjects.SearchResultPage;
import com.pageobjectmodel.utility.Log;

public class SearchResultPageTest extends BaseClass {

    private IndexPage index;
    private SearchResultPage searchResultPage;

    @Parameters("browser")
    @BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void setup(@Optional("firefox") String browser) {
        launchApp(browser);
    }

    @Test(groups = "Smoke", dataProvider = "searchProduct", dataProviderClass = DataProviders.class)
    public void productAvailabilityTest(String productName) throws Throwable {
        Log.startTestCase("productAvailabilityTest");
        index = new IndexPage();
        searchResultPage = index.searchProduct(productName);
        boolean result = searchResultPage.isProductAvailable();
        Assert.assertTrue(result);
        Log.endTestCase("productAvailabilityTest");
    }

    @AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void tearDown() {
        getDriver().quit();
    }
}
