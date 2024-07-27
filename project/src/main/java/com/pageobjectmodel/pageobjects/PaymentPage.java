package com.pageobjectmodel.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobjectmodel.actiondriver.Action;
import com.pageobjectmodel.base.BaseClass;

public class PaymentPage extends BaseClass {

    Action action = new Action();

    @FindBy(xpath = "//a[contains(text(),'Pay by bank wire')]")
    private WebElement bankWireMethod;

    @FindBy(xpath = "//a[contains(text(),'Pay by check')]")
    private WebElement payByCheckMethod;

    public PaymentPage() {
        PageFactory.initElements(driver.get(), this);
    }

    public OrderSummary clickOnPaymentMethod() throws Throwable {
        action.click(driver.get(), bankWireMethod);
        return new OrderSummary();
    }
}
