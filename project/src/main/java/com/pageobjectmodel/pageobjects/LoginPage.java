package com.pageobjectmodel.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobjectmodel.actiondriver.Action;
import com.pageobjectmodel.base.BaseClass;

public class LoginPage extends BaseClass {
    Action action = new Action();

    @FindBy(id = "email")
    private WebElement userName;

    @FindBy(id = "passwd")
    private WebElement password;

    @FindBy(id = "SubmitLogin")
    private WebElement signInBtn;

    @FindBy(name = "email_create")
    private WebElement emailForNewAccount;

    @FindBy(name = "SubmitCreate")
    private WebElement createNewAccountBtn;

    public LoginPage() {
        PageFactory.initElements(driver.get(), this);

    }

    public HomePage login(String uname, String pswd, HomePage homePage) throws Throwable {
        action.scrollByVisibilityOfElement(driver.get(), userName);
        action.type(userName, uname);
        action.type(password, pswd);
        action.JSClick(driver.get(), signInBtn);
        Thread.sleep(2000);
        homePage = new HomePage();
        return homePage;
    }

    public AddressPage login(String uname, String pswd, AddressPage addressPage)
            throws Throwable {
        action.scrollByVisibilityOfElement(driver.get(), userName);
        action.type(userName, uname);
        action.type(password, pswd);
        action.click(driver.get(), signInBtn);
        Thread.sleep(2000);
        addressPage = new AddressPage();
        return addressPage;
    }

    public AccountCreationPage createNewAccount(String newEmail) throws Throwable {
        action.type(emailForNewAccount, newEmail);
        action.click(driver.get(), createNewAccountBtn);
        return new AccountCreationPage();
    }
}
