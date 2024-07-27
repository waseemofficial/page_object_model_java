package com.pageobjectmodel.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobjectmodel.actiondriver.Action;
import com.pageobjectmodel.base.BaseClass;

public class ShippingPage extends BaseClass {

    Action action = new Action();
    @FindBy(id = "cgv")
    private WebElement terms;

    @FindBy(xpath = "//button/span[contains(text(),'Proceed to checkout')]")
    private WebElement proceedToCheckOutBtn;

    public ShippingPage() {
        PageFactory.initElements(driver.get(), this);
    }

    public void checkTheTerms() throws Throwable {
        action.click(driver.get(), terms);
    }

    public PaymentPage clickOnProceedToCheckOut() throws Throwable {
        action.click(driver.get(), proceedToCheckOutBtn);
        return new PaymentPage();
    }
}
