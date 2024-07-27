package com.pageobjectmodel.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobjectmodel.actiondriver.Action;
import com.pageobjectmodel.base.BaseClass;

public class SearchResultPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//*[@id=\"center_column\"]//img")
    private WebElement productResult;

    public SearchResultPage() {
        PageFactory.initElements(driver.get(), this);
    }

    public boolean isProductAvailable() throws Throwable {
        return action.isDisplayed(driver.get(), productResult);
    }

    public AddToCartPage clickOnProduct() throws Throwable {
        action.click(driver.get(), productResult);
        return new AddToCartPage();
    }
}
