package com.pageobjectmodel.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobjectmodel.actiondriver.Action;
import com.pageobjectmodel.base.BaseClass;

public class OrderSummary extends BaseClass {

    Action action = new Action();
    @FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
    private WebElement confirmOrderBtn;

    public OrderSummary() {
        PageFactory.initElements(driver.get(), this);
    }

    public OrderConfirmationPage clickOnconfirmOrderBtn() throws Throwable {
        action.click(driver.get(), confirmOrderBtn);
        return new OrderConfirmationPage();
    }
}
