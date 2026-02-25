package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class BasePage {

    public By homePagePopup = By.xpath("//i[@class='la la-close fs-20']");

    public By iUnderstoodPopup = By.xpath("//button[normalize-space()='Ok. I Understood']");

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
        WebUI.waitForPageLoaded();
        clickClosePopup();
    }
}
