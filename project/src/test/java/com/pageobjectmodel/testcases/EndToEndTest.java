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
import com.pageobjectmodel.pageobjects.AddressPage;
import com.pageobjectmodel.pageobjects.IndexPage;
import com.pageobjectmodel.pageobjects.LoginPage;
import com.pageobjectmodel.pageobjects.OrderConfirmationPage;
import com.pageobjectmodel.pageobjects.OrderPage;
import com.pageobjectmodel.pageobjects.OrderSummary;
import com.pageobjectmodel.pageobjects.PaymentPage;
import com.pageobjectmodel.pageobjects.SearchResultPage;
import com.pageobjectmodel.pageobjects.ShippingPage;
import com.pageobjectmodel.utility.Log;

public class EndToEndTest extends BaseClass {

    private IndexPage index;
    private SearchResultPage searchResultPage;
    private AddToCartPage addToCartPage;
    private OrderPage orderPage;
    private LoginPage loginPage;
    private AddressPage addressPage;
    private ShippingPage shippingPage;
    private PaymentPage paymentPage;
    private OrderSummary orderSummary;
    private OrderConfirmationPage orderConfirmationPage;

    @Parameters("browser")
    @BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void setup(@Optional("firefox") String browser) {
        launchApp(browser);
    }

    @Test(groups = "Regression", dataProvider = "getProduct", dataProviderClass = DataProviders.class)
    public void endToEndTest(String productName, String qty, String size) throws Throwable {
        Log.startTestCase("endToEndTest");
        index = new IndexPage();
        searchResultPage = index.searchProduct(productName);
        addToCartPage = searchResultPage.clickOnProduct();
        addToCartPage.enterQuantity(qty);
        addToCartPage.selectSize(size);
        addToCartPage.clickOnAddToCart();
        orderPage = addToCartPage.clickOnCheckOut();
        loginPage = orderPage.clickOnCheckOut();
        addressPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"), addressPage);
        shippingPage = addressPage.clickOnCheckOut();
        shippingPage.checkTheTerms();
        paymentPage = shippingPage.clickOnProceedToCheckOut();
        orderSummary = paymentPage.clickOnPaymentMethod();
        orderConfirmationPage = orderSummary.clickOnconfirmOrderBtn();
        String actualMessage = orderConfirmationPage.validateConfirmMessage();
        String expectedMsg = "Your order on My Store is complete.";
        Assert.assertEquals(actualMessage, expectedMsg);
        Log.endTestCase("endToEndTest");
    }

    @AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void tearDown() {
        getDriver().quit();
    }
}
