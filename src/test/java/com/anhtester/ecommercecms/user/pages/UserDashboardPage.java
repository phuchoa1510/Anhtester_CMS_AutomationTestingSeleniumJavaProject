package com.anhtester.ecommercecms.user.pages;

import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class UserDashboardPage extends BasePage {

    private By headerDashboardPage = By.xpath("//h1[normalize-space()='Dashboard']");
    private By menuDashboard = By.xpath("//div[@class='d-flex align-items-start']//a[@class='aiz-side-nav-link active']");
    private By menuManageProfile = By.xpath("//div[@class='d-flex align-items-start']//span[@class='aiz-side-nav-text'][normalize-space()='Manage Profile']");
    private By menuPurchaseHistory = By.xpath("//section/descendant::span[normalize-space() ='Purchase History']");

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

    public boolean isDashboardPageDisplayed() {
        return WebUI.checkElementExist(headerDashboardPage);
    }

    public PurchaseHistoryPage clickMenuPurchaseHistory() {
        WebUI.clickElement(menuPurchaseHistory);
        return new PurchaseHistoryPage();
    }


}
