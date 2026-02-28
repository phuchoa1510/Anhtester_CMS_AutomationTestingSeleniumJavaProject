package com.anhtester.common;

import com.anhtester.drivers.DriverManager;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.listeners.TestListener;
import com.anhtester.utils.LogUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

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
        WebDriver driver;

        if (PropertiesHelper.getValue("BROWSER").isEmpty() || PropertiesHelper.getValue("BROWSER") == null) {
            browserName = browserName;
        } else {
            browserName = PropertiesHelper.getValue("BROWSER");
        }

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                LogUtils.info("Launching Chrome browser...");

                ChromeOptions options = new ChromeOptions();

                // ===== Disable Chrome Password Manager & Popup =====
                Map<String, Object> prefs = new HashMap<>();

                // Tắt save password
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);

                // Tắt cảnh báo password leak
                prefs.put("profile.password_manager_leak_detection", false);

                // Tắt notification
                prefs.put("profile.default_content_setting_values.notifications", 2);

                // Tắt autofill
                prefs.put("autofill.profile_enabled", false);
                prefs.put("autofill.credit_card_enabled", false);

                options.setExperimentalOption("prefs", prefs);

                // ===== Headless config =====
                if (PropertiesHelper.getValue("HEADLESS").equalsIgnoreCase("true")) {
                    options.addArguments("--headless=new");
                    options.addArguments("--window-size=" + PropertiesHelper.getValue("WINDOW_SIZE"));
                }

                // (Optional) Tắt save password bubble ở Chrome version mới
                options.addArguments("--disable-save-password-bubble");

                driver = new ChromeDriver(options);
                break;
            case "firefox":
                LogUtils.info("Launching Firefox browser...");
                driver = new FirefoxDriver();
                break;
            case "edge":
                LogUtils.info("Launching Edge browser...");
                driver = new EdgeDriver();
                break;
            default:
                LogUtils.info("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = new ChromeDriver();
        }

        DriverManager.setDriver(driver);

        if (PropertiesHelper.getValue("HEADLESS").equalsIgnoreCase("false")) {
            DriverManager.getDriver().manage().window().maximize();
        }

        softAssert = new SoftAssert();
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(ITestResult result) {
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
            softAssert.assertAll();
        }
    }

}
