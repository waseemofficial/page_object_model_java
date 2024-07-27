package com.pageobjectmodel.utility;

import java.io.IOException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExtentManager {
    private static final Logger logger = LogManager.getLogger(ExtentManager.class);
    private static ExtentSparkReporter sparkReporter;
    public static ExtentReports extent;
    public static ExtentTest test;

    public static void setExtent() {
        sparkReporter = new ExtentSparkReporter(
                System.getProperty("user.dir") + "/test-output/ExtentReport/MyReport.html");
        loadConfig();
        configureReporter();
        initializeExtentReports();
    }

    private static void loadConfig() {
        try {
            sparkReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
        } catch (IOException e) {
            logger.error("Exception while loading ExtentReports config", e);
        }
    }

    private static void configureReporter() {
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("automationpractice Test Automation Report");
        sparkReporter.config().setTheme(Theme.DARK);
    }

    private static void initializeExtentReports() {
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        setSystemInfo();
    }

    private static void setSystemInfo() {
        extent.setSystemInfo("HostName", "MyHost");
        extent.setSystemInfo("ProjectName", "Project");
        extent.setSystemInfo("Tester", "was");
        extent.setSystemInfo("OS", "Win10");
        extent.setSystemInfo("Browser", "Firefox");
    }

    public static void endReport() {
        if (extent != null) {
            extent.flush();
            logger.info("ExtentReports flushed successfully");
        }
    }
}
