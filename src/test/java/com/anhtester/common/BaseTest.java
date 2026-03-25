package com.anhtester.common;

import com.anhtester.drivers.BrowserFactory;
import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

@Listeners(TestListener.class)
public class BaseTest {
    public SoftAssert softAssert;

    @BeforeSuite
    public void setupBeforeSuite() {
        PropertiesHelper.loadAllFiles();
    }

    @BeforeMethod
    @Parameters({"BROWSER"})
    public void createDriver(@Optional("chrome") String browserName) {
        WebDriver driver = BrowserFactory.createDriver(browserName);

        DriverManager.setDriver(driver);

        if (PropertiesHelper.getValue("HEADLESS").equalsIgnoreCase("false")) {
            DriverManager.getDriver().manage().window().maximize();
        }

        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(ITestResult result) {
        DriverManager.quit();
        softAssert.assertAll();
    }
}
