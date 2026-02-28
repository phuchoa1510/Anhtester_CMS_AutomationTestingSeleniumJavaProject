package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class BasePage {

    public By homePagePopup = By.xpath("//i[@class='la la-close fs-20']");

    public By iUnderstoodPopup = By.xpath("//button[normalize-space()='Ok. I Understood']");

    public By buttonCart = By.xpath("//div[@id = 'cart_items']");

    public By quantityInCart = By.xpath("//span[normalize-space() ='Cart']/preceding-sibling::span");

    public By cartItemsDropdownMenu = By.xpath("//div[normalize-space() ='Cart Items']");

    public By buttonViewCart = By.xpath("//a[normalize-space()='View cart']");

    public By buttonMyPanel = By.xpath("//a[normalize-space()='My Panel']");

    public void clickClosePopup() {
        if (WebUI.checkElementExist(homePagePopup)) {
            WebUI.clickElement(homePagePopup);
        }
        if (WebUI.checkElementExist(iUnderstoodPopup)) {
            WebUI.clickElement(iUnderstoodPopup);
        }
    }
    public void navigateToDashboardPage() {
        WebUI.clickElement(buttonMyPanel);
        WebUI.waitForPageLoaded();
        clickClosePopup();
    }

    public void navigateHomePage() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        clickClosePopup();
    }

    public void navigateToCartPage() {
        int quantity = Integer.parseInt(WebUI.getElementText(quantityInCart));
        if (quantity == 0) {
            System.out.println("Not Product in Cart");
        } else {
            WebUI.clickElement(buttonCart);
            boolean check = WebUI.checkElementExist(cartItemsDropdownMenu);
            Assert.assertTrue(check,"Cart Items Dropdown Menu is not present");
            WebUI.clickElement(buttonViewCart);
        }
    }
}
