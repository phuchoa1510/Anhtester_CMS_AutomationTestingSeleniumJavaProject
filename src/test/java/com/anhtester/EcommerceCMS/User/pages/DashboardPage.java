package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class DashboardPage extends BasePage{

    private By headerDashboardPage = By.xpath("//h1[normalize-space()='Dashboard']");


    public void verifyDashboardPageDisplayed(){
        boolean check = WebUI.checkElementExist(headerDashboardPage);
        Assert.assertTrue(check, "Dashboard Page is not displayed");
    }

}
