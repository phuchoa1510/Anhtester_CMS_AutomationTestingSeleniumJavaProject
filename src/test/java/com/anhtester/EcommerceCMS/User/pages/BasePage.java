package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class BasePage {

    public By iconAnhtester = By.xpath("//header//img[@alt='Active eCommerce CMS']");

    public By menuDashboard = By.xpath("//div[@class='d-flex align-items-start']//a[@class='aiz-side-nav-link active']");

    public By menuManageProfile = By.xpath("//div[@class='d-flex align-items-start']//span[@class='aiz-side-nav-text'][normalize-space()='Manage Profile']");

    public By buttonHome = By.xpath("//a[contains(text(),'Home')]");

    public By buttonLogout = By.xpath("//a[text()='My Panel']/parent::li/following-sibling::li[1]/a");

    public By homePagePopup = By.xpath("//i[@class='la la-close fs-20']");

    public By iUnderstoodPopup = By.xpath("//button[normalize-space()='Ok. I Understood']");

    public By buttonMyPanel = By.xpath("//a[normalize-space()='My Panel']");



    public void navigateToMyPanel (){
        WebUI.clickElement(buttonMyPanel);
    }

    public void clickMenuDashboard() {
        WebUI.clickElement(menuDashboard);
    }
    public void clickMenuManageProfile(){
        WebUI.clickElement(menuManageProfile);
    }

    public void clickClosePopup(){
        if (WebUI.checkElementExist(homePagePopup)){
            WebUI.clickElement(homePagePopup);
        }
        if (WebUI.checkElementExist(iUnderstoodPopup)){
            WebUI.clickElement(iUnderstoodPopup);
        }

    }

}
