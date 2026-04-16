package com.anhtester.ecommercecms.user.pages.old;

import com.anhtester.keywords.old.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends BasePage {

    private final By inputSearch = By.xpath("//input[@id='search']");
    private final By buttonLogout = By.xpath("//a[text()='My Panel']/parent::li/following-sibling::li[1]/a");
    private final By buttonMyPanel = By.xpath("//a[normalize-space()='My Panel']");
    private final By iconAnhtester = By.xpath("//header//img[@alt='Active eCommerce CMS']");

    private final String firstSearchResultXpath = "//div[@id ='search-content']/descendant::ul/li[1]/a/div/div[2]/div[normalize-space()='%s']";

    @Step("Click icon Anhtester")
    public void clickIconAnhtester() {
        WebUI.clickElement(iconAnhtester);
    }

    @Step("Verify Home Page displayed")
    public void verifyHomePageIsDisplayed() {
        boolean isDisplayLogo = WebUI.checkElementExist(iconAnhtester);
        boolean isDisplayInputSearch = WebUI.checkElementExist(inputSearch);
        Assert.assertTrue(isDisplayLogo && isDisplayInputSearch, "Home page isn't displayed");
    }

    @Step("Navigate to Product Page: {0}")
    public void navigateToProductPage(String productName) {
        WebUI.setText(inputSearch, productName);
        
        String xpathFirstSearch = String.format(firstSearchResultXpath, productName);
        By firstSearch = By.xpath(xpathFirstSearch);

        // Thay thế sleep bằng wait cho element xuất hiện và click được
        WebUI.waitForElementVisible(firstSearch, 10);
        WebUI.clickElement(firstSearch);
        WebUI.waitForPageLoaded();
    }

}
