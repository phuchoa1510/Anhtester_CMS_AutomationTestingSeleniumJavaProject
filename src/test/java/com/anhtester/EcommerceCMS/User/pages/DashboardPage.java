package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DashboardPage extends BasePage {

    private By headerDashboardPage = By.xpath("//h1[normalize-space()='Dashboard']");

    public By menuDashboard = By.xpath("//div[@class='d-flex align-items-start']//a[@class='aiz-side-nav-link active']");

    public By menuManageProfile = By.xpath("//div[@class='d-flex align-items-start']//span[@class='aiz-side-nav-text'][normalize-space()='Manage Profile']");


    public void clickMenuDashboard() {
        WebUI.clickElement(menuDashboard);
    }

    public void clickMenuManageProfile() {
        WebUI.clickElement(menuManageProfile);
    }

    public void verifyDashboardPageDisplayed() {
        boolean check = WebUI.checkElementExist(headerDashboardPage);
        Assert.assertTrue(check, "Dashboard Page is not displayed");
    }

}
