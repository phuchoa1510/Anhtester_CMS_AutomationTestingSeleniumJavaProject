package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import javax.swing.text.DocumentFilter;

public class BasePage {

    private By homePagePopup = By.xpath("//i[@class='la la-close fs-20']");

    private By iUnderstood = By.xpath("//button[normalize-space()='Ok. I Understood']");

    private By buttonCart = By.xpath("//div[@id = 'cart_items']");

    private By quantityInCart = By.xpath("//span[normalize-space() ='Cart']/preceding-sibling::span");

    private By cartItemsDropdownMenu = By.xpath("//div[normalize-space() ='Cart Items']");

    private By buttonViewCart = By.xpath("//a[normalize-space()='View cart']");

    private By buttonMyPanel = By.xpath("//a[normalize-space()='My Panel']");

    private By buttonLogout = By.xpath("//a[normalize-space()='My Panel']/parent::li/following-sibling::li/a[normalize-space()='Logout']");

    @Step("Click close popup")
    public void clickClosePopup() {
        if (WebUI.checkElementExist(homePagePopup)) {
            WebUI.scrollToElement(homePagePopup);
            WebUI.clickElement(homePagePopup);
        }
    }

    @Step("Click close I Understood")
    public void clickCloseIUnderstood() {
        if (WebUI.checkElementExist(iUnderstood)) {
            WebUI.scrollToElement(iUnderstood);
            WebUI.clickElement(iUnderstood);
        }
    }

    @Step("Navigate to Dashboard page")
    public UserDashboardPage navigateToDashboardPage() {
        WebUI.clickElement(buttonMyPanel);
        WebUI.waitForPageLoaded();
        return new UserDashboardPage();
    }

    @Step("Navigate to Home page")
    public HomePage navigateHomePage() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        clickCloseIUnderstood();
        clickClosePopup();
        WebUI.sleep(3);
        return new HomePage();
    }

    @Step("Navigate to Cart page")
    public CartPage navigateToCartPage() {
        int quantity = Integer.parseInt(WebUI.getElementText(quantityInCart));
        if (quantity == 0) {
            System.out.println("Not Product in Cart");
        } else {
            WebUI.clickElement(buttonCart);
            boolean check = WebUI.checkElementExist(cartItemsDropdownMenu);
            Assert.assertTrue(check, "Cart Items Dropdown Menu is not present");
            WebUI.clickElement(buttonViewCart);
        }
        return new CartPage();
    }

    @Step("Logout user account")
    public void logout() {
        WebUI.checkElementExist(buttonLogout);
        WebUI.clickElement(buttonLogout);
    }

}
