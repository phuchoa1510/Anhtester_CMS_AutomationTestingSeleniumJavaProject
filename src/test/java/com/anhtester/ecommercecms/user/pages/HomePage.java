package com.anhtester.ecommercecms.user.pages;

import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By inputSearch = By.xpath("//input[@id='search']");
    private final By iconAnhtester = By.xpath("//header//img[@alt='Active eCommerce CMS']");
    private final String firstSearchResultXpath = "//div[normalize-space()='Products']/parent::div/descendant::div[normalize-space()='%s']";

    @Step("Click icon Anhtester")
    public HomePage clickIconAnhtester() {
        WebUI.clickElement(iconAnhtester);
        WebUI.waitForPageLoaded();
        return this;
    }

    @Step("Is Home Page displayed")
    public boolean isHomePageDisplayed() {
        return WebUI.checkElementExist(iconAnhtester) && WebUI.checkElementExist(inputSearch);
    }

    @Step("Search and Navigate to Product Page: {0}")
    public ProductPage navigateToProductPage(String productName) {
        By firstResult = By.xpath(String.format(firstSearchResultXpath,productName));
        WebUI.setText(inputSearch, productName);
        WebUI.waitForElementVisible(firstResult, 5);
        WebUI.clickElement(firstResult);
        WebUI.waitForPageLoaded();
        return new ProductPage();
    }
}
