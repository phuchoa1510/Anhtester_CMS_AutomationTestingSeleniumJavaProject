package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.apache.commons.collections4.Get;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProductPage extends BasePage{

    public By inputSearch = By.xpath("//input[@id='search']");
    public By firstSearchResult = By.xpath("//ul[@class ='list-group list-group-raw']/li[@class='list-group-item'][1]");
    public By productTitle = By.xpath("//h1");
    public By sellerName = By.xpath("//small[normalize-space()='Sold by:']/parent::div");
    public By productPrice = By.xpath("//div[normalize-space()='Price:']/parent::div/following-sibling::div//strong");
    public By productDescription = By.xpath("//a[normalize-space()='Description']/parent::div/following-sibling::div/div[@id='tab_default_1']/div/div");

    public void navigateToTargetProductPage(String productName){
        navigateHomePage();
        WebUI.checkElementExist(inputSearch);
        WebUI.setText(inputSearch,productName);
        WebUI.waitForElementToBeClickable(firstSearchResult);
        WebUI.clickElement(firstSearchResult);
        WebUI.waitForPageLoaded();
    }
    @Step("Verify Product {0} Page Displayed")
    public void verifyProductTargetPageIsDisplayed(String productName){
        WebUI.checkElementExist(productTitle);
        String productTitleName = WebUI.getElementText(productTitle);
        Assert.assertEquals(productTitleName,productName,"Product title isn't displayed");
    }
    @Step("Get Product Name")
    public String getProductName(){
        WebUI.checkElementExist(productTitle);
        String productName = WebUI.getElementText(productTitle);
        return productName;
    }
    @Step("Get Seller Name")
    public String getProductSellerName(){
        WebUI.checkElementExist(sellerName);
        WebUI.moveToElement(sellerName);
        String fullTextSeller =  WebUI.getElementText(sellerName);
        String seller = fullTextSeller.split("\n")[1].trim();
        return seller;
    }
    @Step("Get Price")
    public String getProductPrice(){
        WebUI.checkElementExist(productPrice);
        WebUI.moveToElement(productPrice);
        String price =  WebUI.getElementText(productPrice);
        return price;
    }
    @Step("Get Description")
    public String getProductDescription(){
        WebUI.checkElementExist(productDescription);
        WebUI.moveToElement(productDescription);
        String description =  WebUI.getElementText(productDescription);
        return description;
    }


}


