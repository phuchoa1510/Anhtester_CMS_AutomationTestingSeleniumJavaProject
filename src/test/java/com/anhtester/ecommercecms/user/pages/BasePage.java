package com.anhtester.ecommercecms.user.pages;

import com.anhtester.ecommercecms.admin.pages.AdminDashboardPage;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class BasePage {

    private final By homePagePopup = By.xpath("//i[@class='la la-close fs-20']");
    private final By iUnderstood = By.xpath("//button[normalize-space()='Ok. I Understood']");
    private final By buttonCart = By.xpath("//div[@id = 'cart_items']");
    private final By quantityInCart = By.xpath("//span[normalize-space() ='Cart']/preceding-sibling::span");
    private final By cartItemsDropdownMenu = By.xpath("//div[normalize-space() ='Cart Items']");
    private final By buttonViewCart = By.xpath("//a[normalize-space()='View cart']");
    private final By buttonMyPanel = By.xpath("//a[normalize-space()='My Panel']");

    @Step("Click close popup")
    public BasePage clickClosePopup() {
        if (WebUI.checkElementExist(homePagePopup)) {
            WebUI.scrollToElement(homePagePopup);
            WebUI.clickElement(homePagePopup);
        }
        return this;
    }

    @Step("Click close I Understood")
    public BasePage clickCloseIUnderstood() {
        if (WebUI.checkElementExist(iUnderstood)) {
            WebUI.scrollToElement(iUnderstood);
            WebUI.clickElement(iUnderstood);
        }
        return this;
    }

    @Step("Navigate to User Dashboard page")
    public UserDashboardPage navigateToUserDashboardPage() {
        WebUI.clickElement(buttonMyPanel);
        WebUI.waitForPageLoaded();
        return new UserDashboardPage();
    }
    @Step("Navigate to Admin Dashboard page")
    public AdminDashboardPage navigateToAdminDashboardPage() {
        WebUI.clickElement(buttonMyPanel);
        WebUI.waitForPageLoaded();
        return new AdminDashboardPage();
    }

    @Step("Navigate to Home page")
    public BasePage navigateHomePage() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        clickCloseIUnderstood();
        clickClosePopup();
        return this;
    }

    public int getCartQuantity() {
        String text = WebUI.getElementText(quantityInCart);
        return Integer.parseInt(text.trim());
    }

    public boolean isCartDropdownDisplayed() {
        return WebUI.checkElementExist(cartItemsDropdownMenu);
    }

    @Step("Navigate to Cart page")
    public CartPage navigateToCartPage() {
        if (getCartQuantity() > 0) {
            WebUI.clickElement(buttonCart);
            WebUI.clickElement(buttonViewCart);
        }
        return new CartPage();
    }
}
