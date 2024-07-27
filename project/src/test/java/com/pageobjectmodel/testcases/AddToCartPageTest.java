package com.pageobjectmodel.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pageobjectmodel.base.BaseClass;
import com.pageobjectmodel.dataprovider.DataProviders;
import com.pageobjectmodel.pageobjects.AddToCartPage;
import com.pageobjectmodel.pageobjects.IndexPage;
import com.pageobjectmodel.pageobjects.SearchResultPage;
import com.pageobjectmodel.utility.Log;

public class AddToCartPageTest extends BaseClass {

    private IndexPage index;
    private SearchResultPage searchResultPage;
    private AddToCartPage addToCartPage;

    @Parameters("browser")
    @BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void setup(@Optional("firefox") String browser) {
        launchApp(browser);
    }

    @Test(groups = { "Regression", "Sanity" }, dataProvider = "getProduct", dataProviderClass = DataProviders.class)
    public void addToCartTest(String productName, String qty, String size) throws Throwable {
        Log.startTestCase("addToCartTest");
        index = new IndexPage();
        searchResultPage = index.searchProduct(productName);
        addToCartPage = searchResultPage.clickOnProduct();
        addToCartPage.enterQuantity(qty);
        addToCartPage.selectSize(size);
        addToCartPage.clickOnAddToCart();
        boolean result = addToCartPage.validateAddtoCart();
        Assert.assertTrue(result);
        Log.endTestCase("addToCartTest");

    }

    @AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void tearDown() {
        driver.get().quit();
    }

}
