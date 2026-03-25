package com.anhtester.ecommercecms.user.pages;

import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class UserDashboardPage extends BasePage {

    private By headerDashboardPage = By.xpath("//h1[normalize-space()='Dashboard']");

    private By menuDashboard = By.xpath("//div[@class='d-flex align-items-start']//a[@class='aiz-side-nav-link active']");

    private By menuManageProfile = By.xpath("//div[@class='d-flex align-items-start']//span[@class='aiz-side-nav-text'][normalize-space()='Manage Profile']");

    private By menuPurchaseHistory = By.xpath("//span[normalize-space()='Purchase History']/ancestor::body");

    private By newestOrder = By.xpath("//tbody/tr[1]/td[1]/a");

    @Step("Click menu Dashboard")
    public UserDashboardPage clickMenuDashboard() {
        WebUI.clickElement(menuDashboard);
        return this;
    }

    @Step("Click menu Manage Profile")
    public ProfilePage clickMenuManageProfile() {
        WebUI.clickElement(menuManageProfile);
        return new ProfilePage();
    }

    @Step("Verify Dashboard page displayed")
    public void verifyDashboardPageDisplayed() {
        boolean check = WebUI.checkElementExist(headerDashboardPage);
        Assert.assertTrue(check, "Dashboard Page is not displayed");
    }


}
