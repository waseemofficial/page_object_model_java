package com.pageobjectmodel.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobjectmodel.actiondriver.Action;
import com.pageobjectmodel.base.BaseClass;

public class HomePage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//span[text()='My wishlists']")
    private WebElement myWishList;

    @FindBy(xpath = "//span[text()='Order history and details']")
    private WebElement orderHistory;

    public HomePage() {
        PageFactory.initElements(driver.get(), this);
    }

    public boolean validateMyWishList() throws Throwable {
        return action.isDisplayed(driver.get(), myWishList);
    }

    public boolean validateOrderHistory() throws Throwable {
        return action.isDisplayed(driver.get(), orderHistory);
    }

    public String getCurrURL() throws Throwable {
        String homePageURL = action.getCurrentURL(driver.get());
        return homePageURL;
    }
}
