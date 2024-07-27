package com.pageobjectmodel.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobjectmodel.actiondriver.Action;
import com.pageobjectmodel.base.BaseClass;

public class IndexPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//a[normalize-space()='Sign in']")
    WebElement signinbtn;

    // find Logo
    @FindBy(xpath = "//img[@alt='My Shop']")
    WebElement logo;

    // find search
    @FindBy(id = "search_query_top")
    WebElement searchbox;
    // find search btn
    @FindBy(name = "submit_search")
    WebElement searchboxbtn;

    public IndexPage() {
        PageFactory.initElements(driver.get(), this);
    }

    public LoginPage clickOnSignIn() throws Throwable {
        action.fluentWait(driver.get(), signinbtn, 10);
        action.click(driver.get(), signinbtn);
        return new LoginPage();
    }

    public boolean validateLogo() throws Throwable {
        return action.isDisplayed(driver.get(), logo);
    }

    public String getMyStoreTitle() {
        String myStoreTitel = driver.get().getTitle();
        return myStoreTitel;
    }

    public SearchResultPage searchProduct(String productName) throws Throwable {
        action.type(searchbox, productName);
        action.scrollByVisibilityOfElement(driver.get(), searchboxbtn);
        action.click(driver.get(), searchboxbtn);
        Thread.sleep(3000);
        return new SearchResultPage();
    }
}
