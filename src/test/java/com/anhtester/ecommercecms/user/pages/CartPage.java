package com.anhtester.ecommercecms.user.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private final By stepInCart = By.xpath("//div[@class='col active']");
    private final By buttonContinueToShipping = By.xpath("//a[normalize-space()='Continue to Shipping']");
    private final By buttonContinueToDeliveryInfo = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    private final By buttonHomeDeliveryType = By.xpath("//span/span[normalize-space()='Home Delivery']");
    private final By buttonContinueToPayment = By.xpath("//button[normalize-space()='Continue to Payment']");
    private final By inputAdditionalInfo = By.xpath("//textarea[@placeholder='Type your text']");
    private final By buttonCashOnDelivery = By.xpath("//span/span/span[normalize-space()='Cash on Delivery']");
    private final By checkboxAgreeTermAndConditions = By.xpath("//span[normalize-space()='I agree to the']/preceding-sibling::span");
    private final By buttonCompleteOrder = By.xpath("//button[normalize-space()='Complete Order']");

    private final By orderConfirmMessage = By.xpath("//h1[normalize-space()='Thank You for Your Order!']");
    private final By orderCodeDisplay = By.xpath("//h2/span");
    private final By firstOrderCodeInHistory = By.xpath("//table//tbody/tr[1]/td[1]/a");

    private final String addressOptionXpath = "//form[@data-toggle='validator']/div/div/div[%s]";

    @Step("Is Cart page displayed")
    public boolean isCartPageDisplayed() {
        WebUI.waitForPageLoaded();
        return WebUI.checkElementExist(stepInCart);
    }

    @Step("Click Continue to Shipping")
    public CartPage clickContinueToShipping() {
        WebUI.scrollToElement(buttonContinueToShipping);
        WebUI.clickElement(buttonContinueToShipping);
        WebUI.waitForPageLoaded();
        return this;
    }

    @Step("Shipping Info step: Choose address index {0}")
    public CartPage shippingInfoStep(String addressIndex) {
        By addressOption = By.xpath(String.format(addressOptionXpath, addressIndex));
        WebUI.scrollToElement(addressOption);
        WebUI.clickElement(addressOption);
        WebUI.clickElement(buttonContinueToDeliveryInfo);
        WebUI.waitForPageLoaded();
        return this;
    }

    @Step("Delivery Info step: Choose Home Delivery")
    public CartPage deliveryInfoStep() {
        WebUI.scrollToElement(buttonHomeDeliveryType);
        WebUI.clickElement(buttonHomeDeliveryType);
        WebUI.clickElement(buttonContinueToPayment);
        WebUI.waitForPageLoaded();
        return this;
    }

    @Step("Payment step: Choose COD and agree to terms")
    public CartPage paymentStep(String additionalInfo) {
        WebUI.setText(inputAdditionalInfo, additionalInfo);
        WebUI.clickElement(buttonCashOnDelivery);
        WebUI.clickElement(checkboxAgreeTermAndConditions);
        return this;
    }

    @Step("Complete Order")
    public CartPage completeOrder() {
        WebUI.clickElement(buttonCompleteOrder);
        WebUI.waitForPageLoaded();
        return this;
    }

    @Step("Go to Purchase History")
    public CartPage goToPurchaseHistory() {
        WebUI.openURL(PropertiesHelper.getValue("URL") + "/purchase_history");
        WebUI.waitForPageLoaded();
        return this;
    }



    public boolean isOrderConfirmMessageDisplayed() {
        return WebUI.checkElementExist(orderConfirmMessage);
    }

    public String getOrderCode() {
        return WebUI.getElementText(orderCodeDisplay);
    }

    public String getNewestOrderCode() {
        return WebUI.getElementText(firstOrderCodeInHistory);
    }
}
