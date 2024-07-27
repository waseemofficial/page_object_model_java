package com.pageobjectmodel.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.time.Duration;
//Selenium Imports
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeTest;
//Project Imports
import com.pageobjectmodel.utility.ExtentManager;
import org.apache.logging.log4j.core.config.Configurator;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    public static Properties prop;
    // public static WebDriver driver;

    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    @BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
    public void loadconfig() {
        ExtentManager.setExtent();
        Configurator.initialize(null, "log4j2.xml");

        try {
            prop = new Properties();
            System.out.println("super Constructor Invoked");
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\Configuration\\Config.properties");
            prop.load(ip);
            System.out.println("driver: " + driver);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
    // public static WebDriver getDriver() {
    // // Get Driver from threadLocalmap
    // return driver.get();
    // // return driver;
    // }

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
        // Maximize the screen
        getDriver().manage().window().maximize();
        // Delete all the cookies
        getDriver().manage().deleteAllCookies();
        // Implicit TimeOuts
        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait"))));
        // PageLoad TimeOuts
        getDriver().manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(prop.getProperty("pageLoadTimeOut"))));
        // Launching the URL
        getDriver().get(prop.getProperty("url"));
    }

    @AfterSuite(groups = { "Smoke", "Regression", "Sanity" })
    public void afterSuite() {
        ExtentManager.endReport();
    }
}
