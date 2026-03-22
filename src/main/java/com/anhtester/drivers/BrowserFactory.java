package com.anhtester.drivers;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.utils.LogUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    public static WebDriver createDriver(String browserName) {
        WebDriver driver;
        browserName = (browserName == null || browserName.isEmpty()) 
                      ? PropertiesHelper.getValue("BROWSER").toLowerCase() 
                      : browserName.toLowerCase();

        switch (browserName) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                LogUtils.warn("Browser: " + browserName + " is invalid, launching Chrome as default.");
                driver = initChromeDriver();
        }
        return driver;
    }

    private static WebDriver initChromeDriver() {
        LogUtils.info("Launching Chrome browser...");
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("autofill.credit_card_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-save-password-bubble");

        if (PropertiesHelper.getValue("HEADLESS").equalsIgnoreCase("true")) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=" + PropertiesHelper.getValue("WINDOW_SIZE"));
        }

        return new ChromeDriver(options);
    }

    private static WebDriver initFirefoxDriver() {
        LogUtils.info("Launching Firefox browser...");
        FirefoxOptions options = new FirefoxOptions();
        if (PropertiesHelper.getValue("HEADLESS").equalsIgnoreCase("true")) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }

    private static WebDriver initEdgeDriver() {
        LogUtils.info("Launching Edge browser...");
        EdgeOptions options = new EdgeOptions();
        if (PropertiesHelper.getValue("HEADLESS").equalsIgnoreCase("true")) {
            options.addArguments("--headless");
        }
        return new EdgeDriver(options);
    }
}
