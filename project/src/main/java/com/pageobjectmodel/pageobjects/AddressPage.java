package com.pageobjectmodel.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobjectmodel.actiondriver.Action;
import com.pageobjectmodel.base.BaseClass;

public class AddressPage extends BaseClass {

    Action action = new Action();
    @FindBy(xpath = "//span[text()='Proceed to checkout']")
    private WebElement proceedToCheckOut;

    public AddressPage() {
        PageFactory.initElements(driver.get(), this);
    }

    public ShippingPage clickOnCheckOut() throws Throwable {
        action.click(driver.get(), proceedToCheckOut);
        return new ShippingPage();
    }
}
