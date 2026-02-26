package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class HomePage extends BasePage {


    public By inputSearch = By.xpath("//input[@id='search']");

    public By buttonLogout = By.xpath("//a[text()='My Panel']/parent::li/following-sibling::li[1]/a");

    public By buttonMyPanel = By.xpath("//a[normalize-space()='My Panel']");

    public By iconAnhtester = By.xpath("//header//img[@alt='Active eCommerce CMS']");

    public By firstSearchResult = By.xpath("//ul[@class ='list-group list-group-raw']/li[@class='list-group-item'][1]");

    public void clickiconAnhtester() {
        WebUI.clickElement(iconAnhtester);
    }

    public void navigateToMyPanel() {
        WebUI.clickElement(buttonMyPanel);
    }

    public void verifyHomePageIsDisplayed() {
        boolean isDisplayLogo = WebUI.checkElementExist(iconAnhtester);
        boolean isDisplayInputSearch = WebUI.checkElementExist(inputSearch);
        Assert.assertTrue(isDisplayLogo && isDisplayInputSearch, "Home page isn't displayed");
    }

    public void navigateToProductPage(String productName) {
        navigateHomePage();
        WebUI.checkElementExist(inputSearch);
        WebUI.setText(inputSearch, productName);
        WebUI.waitForElementToBeClickable(firstSearchResult);
        WebUI.clickElement(firstSearchResult);
        WebUI.waitForPageLoaded();
    }

}
