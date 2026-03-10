package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DashboardPage extends BasePage {

    private By headerDashboardPage = By.xpath("//h1[normalize-space()='Dashboard']");

    public By menuDashboard = By.xpath("//div[@class='d-flex align-items-start']//a[@class='aiz-side-nav-link active']");

    public By menuManageProfile = By.xpath("//div[@class='d-flex align-items-start']//span[@class='aiz-side-nav-text'][normalize-space()='Manage Profile']");

    public By menuPurchaseHistory = By.xpath("//span[normalize-space()='Purchase History']/ancestor::body");

    public By newestOrder = By.xpath("//tbody/tr[1]/td[1]/a");

    @Step("Click menu Dashboard")
    public void clickMenuDashboard() {
        WebUI.clickElement(menuDashboard);
    }

    @Step("Click menu Manage Profile")
    public void clickMenuManageProfile() {
        WebUI.clickElement(menuManageProfile);
    }

    @Step("Verify Dashboard page displayed")
    public void verifyDashboardPageDisplayed() {
        boolean check = WebUI.checkElementExist(headerDashboardPage);
        Assert.assertTrue(check, "Dashboard Page is not displayed");
    }


}
