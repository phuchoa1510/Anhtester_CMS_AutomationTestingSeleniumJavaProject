package com.anhtester.ecommercecms.user.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class BasePage {

    private final By homePagePopup = By.xpath("//i[@class='la la-close fs-20']");
    private final By iUnderstood = By.xpath("//button[normalize-space()='Ok. I Understood']");
    private final By buttonCart = By.xpath("//div[@id = 'cart_items']");
    private final By quantityInCart = By.xpath("//span[normalize-space() ='Cart']/preceding-sibling::span");
    private final By cartItemsDropdownMenu = By.xpath("//div[normalize-space() ='Cart Items']");
    private final By buttonViewCart = By.xpath("//a[normalize-space()='View cart']");
    private final By buttonMyPanel = By.xpath("//a[normalize-space()='My Panel']");

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
    public void navigateHomePage() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        clickCloseIUnderstood();
        clickClosePopup();
        WebUI.sleep(1); // Giảm sleep xuống mức tối thiểu nếu cần, hoặc thay bằng wait
    }

    @Step("Navigate to Cart page")
    public CartPage navigateToCartPage() {
        int quantity = Integer.parseInt(WebUI.getElementText(quantityInCart));
        if (quantity == 0) {
            System.out.println("No Product in Cart");
        } else {
            WebUI.clickElement(buttonCart);
            boolean check = WebUI.checkElementExist(cartItemsDropdownMenu);
            Assert.assertTrue(check,"Cart Items Dropdown Menu is not present");
            WebUI.clickElement(buttonViewCart);
        }
        return new CartPage();
    }

}
