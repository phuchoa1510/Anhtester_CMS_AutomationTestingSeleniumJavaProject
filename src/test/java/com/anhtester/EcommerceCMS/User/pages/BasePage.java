package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class BasePage {

    public By iconAnhtester = By.xpath("//header//img[@alt='Active eCommerce CMS']");

    public By menuDashboard = By.xpath("//div[@class='d-flex align-items-start']//a[@class='aiz-side-nav-link active']");

    public By menuManageProfile = By.xpath("//div[@class='d-flex align-items-start']//span[@class='aiz-side-nav-text'][normalize-space()='Manage Profile']");

    public By inputSearch = By.xpath("//input[@id='search']");

    public By buttonLogout = By.xpath("//a[text()='My Panel']/parent::li/following-sibling::li[1]/a");

    public By buttonMyPanel = By.xpath("//a[normalize-space()='My Panel']");

    public By homePagePopup = By.xpath("//i[@class='la la-close fs-20']");

    public By iUnderstoodPopup = By.xpath("//button[normalize-space()='Ok. I Understood']");


    public void navigateToMyPanel (){
        WebUI.clickElement(buttonMyPanel);
    }
    public void clickMenuDashboard() {
        WebUI.clickElement(menuDashboard);
    }
    public void clickMenuManageProfile(){
        WebUI.clickElement(menuManageProfile);
    }
    public void clickiconAnhtester(){
        WebUI.clickElement(iconAnhtester);
    }

    public void clickClosePopup(){
        if (WebUI.checkElementExist(homePagePopup)){
            WebUI.clickElement(homePagePopup);
        }
        if (WebUI.checkElementExist(iUnderstoodPopup)){
            WebUI.clickElement(iUnderstoodPopup);
        }
    }
    public void navigateHomePage(){
        WebUI.openURL(PropertiesHelper.getValue("URL"));
    }
    public void verifyHomePageIsDisplayed() {
        boolean isDisplayLogo = WebUI.checkElementExist(iconAnhtester);
        boolean isDisplayInputSearch = WebUI.checkElementExist(inputSearch);
        Assert.assertTrue(isDisplayLogo && isDisplayInputSearch, "Home page isn't displayed");
    }

}
