package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;

public class HomePage extends BasePage {


    public By inputSearch = By.xpath("//input[@id='search']");

    public By buttonLogout = By.xpath("//a[text()='My Panel']/parent::li/following-sibling::li[1]/a");

    public By buttonMyPanel = By.xpath("//a[normalize-space()='My Panel']");

    public By iconAnhtester = By.xpath("//header//img[@alt='Active eCommerce CMS']");

    String firstSearchResult = "//div[@id ='search-content']/descendant::ul/li[1]/a/div/div[2]/div[normalize-space()='";

    @Step("Click icon Anhtester")
    public void clickiconAnhtester() {
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
        int attempts = 0;
        while (attempts < 5) {
            try {
                WebUI.setText(inputSearch, productName);
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        WebUI.sleep(3);
        String xpathFisrtSearch = firstSearchResult + productName +"']";
        By firstSearch = By.xpath(xpathFisrtSearch);
        while (attempts < 5) {
            try {
                WebUI.waitForElementToBeClickable(firstSearch,10);
                WebUI.clickElement(firstSearch);
                break;
            } catch (StaleElementReferenceException e) {
                attempts++;
            }
        }
        WebUI.waitForPageLoaded();
    }

}
