package com.anhtester.ecommercecms.user.pages.old;

import com.anhtester.keywords.old.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProductPage extends BasePage{

    private By productTitle = By.xpath("//h1");

    private By sellerName = By.xpath("//small[normalize-space()='Sold by:']/parent::div");

    private By productPrice = By.xpath("//div[normalize-space()='Price:']/parent::div/following-sibling::div//strong");

    private By productDescription = By.xpath("//a[normalize-space()='Description']/parent::div/following-sibling::div/div[@id='tab_default_1']/div/div");

    private By buttonAddtoCart = By.xpath("//span[normalize-space()='Add to cart']");

    private By addToCartModal = By.xpath("//div[@id='addToCart-modal-body']");

    private By productAddedName = By.xpath("//h6");

    private By productAddedPrice = By.xpath("//h6/parent::div/div/div/div/strong");

    private By messageAddToCartSuccess = By.xpath("//h3[normalize-space()='Item added to your cart!']");

    private By buttonCloseModal = By.xpath("//div[@id='addToCart-modal-body']/preceding-sibling::button");

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
        WebUI.waitForElementVisible(addToCartModal,5);
        WebUI.checkElementExist(addToCartModal);
        boolean check = WebUI.checkElementExist(messageAddToCartSuccess,3,2);
        Assert.assertTrue(check,"Add product to cart failed");
        WebUI.clickElement(buttonCloseModal);
    }

}


