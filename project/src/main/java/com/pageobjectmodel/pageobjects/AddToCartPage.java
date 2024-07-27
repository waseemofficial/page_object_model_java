package com.pageobjectmodel.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobjectmodel.actiondriver.Action;
import com.pageobjectmodel.base.BaseClass;

public class AddToCartPage extends BaseClass {

    Action action = new Action();

    @FindBy(id = "quantity_wanted")
    private WebElement quantity;

    @FindBy(name = "group_1")
    private WebElement size;

    @FindBy(xpath = "//a[@title='View my shopping cart']")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//*[@id=\"layer_cart\"]//h2/i")
    private WebElement addToCartMessag;

    @FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
    private WebElement proceedToCheckOutBtn;

    public AddToCartPage() {
        PageFactory.initElements(driver.get(), this);
    }

    public void enterQuantity(String quantity1) throws Throwable {
        action.type(quantity, quantity1);
    }

    public void selectSize(String size1) throws Throwable {
        action.selectByVisibleText(size1, size);
    }

    public void clickOnAddToCart() throws Throwable {
        action.click(driver.get(), addToCartBtn);
    }

    public boolean validateAddtoCart() throws Throwable {
        action.fluentWait(driver.get(), addToCartMessag, 10);
        return action.isDisplayed(driver.get(), addToCartMessag);
    }

    public OrderPage clickOnCheckOut() throws Throwable {
        action.fluentWait(driver.get(), proceedToCheckOutBtn, 10);
        action.JSClick(driver.get(), proceedToCheckOutBtn);
        return new OrderPage();
    }
}
