package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.apache.commons.collections4.Get;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductPage extends BasePage{

    public By productTitle = By.xpath("//h1");

    public By sellerName = By.xpath("//small[normalize-space()='Sold by:']/parent::div");

    public By productPrice = By.xpath("//div[normalize-space()='Price:']/parent::div/following-sibling::div//strong");

    public By productDescription = By.xpath("//a[normalize-space()='Description']/parent::div/following-sibling::div/div[@id='tab_default_1']/div/div");

    public By buttonAddtoCart = By.xpath("//span[normalize-space()='Add to cart']");

    public By addToCartModal = By.xpath("//div[@id='addToCart-modal-body']");

    public By productAddedName = By.xpath("//h6");

    public By productAddedPrice = By.xpath("//h6/parent::div/div/div/div/strong");

    public By messageAddToCartSuccess = By.xpath("//h3[normalize-space()='Item added to your cart!']");

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
    @Step("Add product to cart")
    public void addProductToCart(){
        WebUI.checkElementExist(buttonAddtoCart);
        WebUI.scrollToElement(buttonAddtoCart);
        WebUI.clickElement(buttonAddtoCart);
    }

    @Step("Verify product add right product to cart success")
    public void verifyProductAddToCartSuccess(){
        WebUI.checkElementExist(addToCartModal);
        boolean check = WebUI.checkElementExist(messageAddToCartSuccess);
        Assert.assertTrue(check,"Add product to cart failed");
    }

}


