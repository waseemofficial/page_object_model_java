package com.pageobjectmodel.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pageobjectmodel.actiondriver.Action;
import com.pageobjectmodel.base.BaseClass;

public class AccountCreationPage extends BaseClass {

    Action action = new Action();

    @FindBy(id = "center_column")
    private WebElement formTitle;

    @FindBy(id = "uniform-id_gender1")
    private WebElement mr;

    @FindBy(id = "id_gender2")
    private WebElement mrs;

    @FindBy(name = "customer_firstname")
    private WebElement firstName;

    @FindBy(name = "customer_lastname")
    private WebElement lastName;

    @FindBy(name = "passwd")
    private WebElement passWord;

    @FindBy(name = "days")
    private WebElement days;

    @FindBy(name = "months")
    private WebElement months;

    @FindBy(name = "years")
    private WebElement years;

    @FindBy(name = "firstname")
    private WebElement customerNirstName;

    @FindBy(name = "lastname")
    private WebElement customerLastName;

    @FindBy(name = "company")
    private WebElement companyName;

    @FindBy(name = "address1")
    private WebElement address;

    @FindBy(name = "city")
    private WebElement city;

    @FindBy(name = "id_state")
    private WebElement state;

    @FindBy(name = "postcode")
    private WebElement postCode;

    @FindBy(name = "id_country")
    private WebElement country;

    @FindBy(name = "phone")
    private WebElement phone;

    @FindBy(name = "phone_mobile")
    private WebElement mobile;

    @FindBy(name = "alias")
    private WebElement ref;

    @FindBy(name = "submitAccount")
    private WebElement registerBtn;

    public AccountCreationPage() {
        PageFactory.initElements(driver.get(), this);
    }

    public void createAccount(String gender, String fName,
            String lName,
            String pswd,
            String day,
            String month,
            String year,
            String comPany,
            String addr,
            String cityString,
            String stateName,
            String zip,
            String countryName,
            String mobilePhone) throws Throwable {
        Thread.sleep(2000);
        if (gender.equalsIgnoreCase("Mr")) {
            action.click(driver.get(), mr);
        } else {
            action.click(driver.get(), mrs);
        }

        action.type(firstName, fName);
        action.type(lastName, lName);
        action.type(passWord, pswd);
        action.selectByValue(days, day);
        action.selectByValue(months, month);
        action.selectByValue(years, year);
        action.type(companyName, comPany);
        action.type(address, addr);
        action.type(city, cityString);
        action.selectByVisibleText(stateName, state);
        action.type(postCode, zip);
        action.selectByVisibleText(countryName, country);
        action.type(mobile, mobilePhone);
    }

    public HomePage validateRegistration() throws Throwable {
        registerBtn.click();
        return new HomePage();
    }

    public boolean validateAcountCreatePage() throws Throwable {
        return action.isDisplayed(driver.get(), formTitle);
    }
}
