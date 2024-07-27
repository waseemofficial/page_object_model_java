`https://www.youtube.com/watch?v=2yDLEgSTG_c` try this

```java

public static void launchApp(String browserName) {
    if (browserName == null || browserName.isEmpty()) {
        throw new IllegalArgumentException("Browser name cannot be null or empty");
    }

    RemoteWebDriver webDriver;
    switch (browserName.toLowerCase()) {
        case "chrome":
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
            break;
        case "firefox":
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
            break;
        case "ie":
            WebDriverManager.iedriver().setup();
            webDriver = new InternetExplorerDriver();
            break;
        default:
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
    }

    driver.set(webDriver);

    getDriver().manage().window().maximize();
    getDriver().manage().deleteAllCookies();
    getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
    getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(prop.getProperty("pageLoadTimeOut"))));
    getDriver().get(prop.getProperty("url"));
}

private static RemoteWebDriver getDriver() {
    return driver.get();
}
```

```java

package com.pageobjectmodel.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.pageobjectmodel.utility.ExtentManager;
import org.apache.logging.log4j.core.config.Configurator;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    public static Properties prop;
    private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    @BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
    public void loadConfig() {
        ExtentManager.setExtent();
        Configurator.initialize(null, "log4j2.xml");

        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\Config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RemoteWebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(RemoteWebDriver webDriver) {
        driver.set(webDriver);
    }

    public static void launchApp(String browserName) {
        if (browserName == null || browserName.isEmpty()) {
            throw new IllegalArgumentException("Browser name cannot be null or empty");
        }

        RemoteWebDriver webDriver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        setDriver(webDriver);

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(prop.getProperty("pageLoadTimeOut"))));
        getDriver().get(prop.getProperty("url"));
    }

    @AfterSuite(groups = { "Smoke", "Regression", "Sanity" })
    public void afterSuite() {
        ExtentManager.endReport();
    }
}
```


###################################################################3

```java

package com.pageobjectmodel.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pageobjectmodel.base.BaseClass;
import com.pageobjectmodel.pageobjects.IndexPage;
import com.pageobjectmodel.utility.Log;

public class IndexPageTest extends BaseClass {

    private IndexPage indexPage;

    @Parameters("browser")
    @BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void setup(@Optional("firefox") String browser) {
        launchApp(browser);
        indexPage = new IndexPage();
    }

    @Test(groups = "Smoke")
    public void verifyLogo() {
        Log.startTestCase("verifyLogo");
        Assert.assertTrue(indexPage.validateLogo(), "Logo validation failed");
        Log.endTestCase("verifyLogo");
    }

    @Test(groups = "Smoke")
    public void verifyTitle() {
        Log.startTestCase("verifyTitle");
        String expectedTitle = "My Store1";
        String actualTitle = indexPage.getMyStoreTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Title verification failed");
        Log.endTestCase("verifyTitle");
    }

    @AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }
}

```

################################################################
```java
package com.pageobjectmodel.testcases;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.*;
import com.pageobjectmodel.base.BaseClass;
import com.pageobjectmodel.dataprovider.DataProviders;
import com.pageobjectmodel.pageobjects.*;
import com.pageobjectmodel.utility.Log;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class AccountCreationPageTest extends BaseClass {

    private IndexPage indexPage;
    private LoginPage loginPage;
    private AccountCreationPage accountCreationPage;
    private HomePage homePage;
    private WebDriverWait wait;

    @Parameters("browser")
    @BeforeMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void setup(@Optional("firefox") String browser) {
        launchApp(browser);
        wait = new WebDriverWait(driver.get(), Duration.ofSeconds(10));
    }

    @Test(groups = "Sanity", dataProvider = "email", dataProviderClass = DataProviders.class)
    public void verifyCreateAccountPageTest(String email) throws Throwable {
        Log.startTestCase("verifyCreateAccountPageTest");
        indexPage = new IndexPage();
        loginPage = indexPage.clickOnSignIn();
        accountCreationPage = loginPage.createNewAccount(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreationPage.getAccountCreationPageIdentifier()));
        boolean result = accountCreationPage.validateAcountCreatePage();
        Assert.assertTrue(result);
        Log.endTestCase("verifyCreateAccountPageTest");
    }

    @Test(groups = "Regression", dataProvider = "newAcountDetailsData", dataProviderClass = DataProviders.class)
    public void createAccountTest(HashMap<String, String> hashMapValue) throws Throwable {
        Log.startTestCase("createAccountTest");
        indexPage = new IndexPage();
        loginPage = indexPage.clickOnSignIn();
        accountCreationPage = loginPage.createNewAccount(hashMapValue.get("Email"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreationPage.getAccountCreationPageIdentifier()));
        accountCreationPage.createAccount(
                hashMapValue.get("Gender"),
                hashMapValue.get("FirstName"),
                hashMapValue.get("LastName"),
                hashMapValue.get("SetPassword"),
                hashMapValue.get("Day"),
                hashMapValue.get("Month"),
                hashMapValue.get("Year"),
                hashMapValue.get("Company"),
                hashMapValue.get("Address"),
                hashMapValue.get("City"),
                hashMapValue.get("State"),
                hashMapValue.get("Zipcode"),
                hashMapValue.get("Country"),
                hashMapValue.get("MobilePhone"));
        homePage = accountCreationPage.validateRegistration();
        wait.until(ExpectedConditions.urlToBe("http://automationpractice.com/index.php?controller=my-account"));
        Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", homePage.getCurrURL());
        Log.endTestCase("createAccountTest");
    }

    @AfterMethod(groups = { "Smoke", "Sanity", "Regression" })
    public void tearDown() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }
}

```


##############################################################################################3

# Log4j2

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"/>
        </Console>
        <RollingFile name="File" fileName="Logs/log4j2.log" filePattern="Logs/log4j2-%d{yyyy-MM-dd}-%i.log">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"/>
            <Policies>
                <SizeBasedTriggeringPolicy size="10MB"/>
            </Policies>
            <DefaultRolloverStrategy max="10"/>
        </RollingFile>
    </Appenders>
    <Loggers>
        <Root level="debug">
            <AppenderRef ref="Console"/>
            <AppenderRef ref="File"/>
        </Root>
    </Loggers>
</Configuration>


```