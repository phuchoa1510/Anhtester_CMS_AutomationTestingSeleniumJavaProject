package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    public By buttonHome = By.xpath("//a[contains(text(),'Home')]");

    //Locator for step My Cart
    public By stepInCart = By.xpath("//div[@class='col active']");
    public By buttonContinueToShipping = By.xpath("//a[normalize-space()='Continue to Shipping']");
    public By buttonContinueToDeliveryInfo = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    public By productInCartItems = By.xpath("//section[@id='cart-summary']//li");
    public By subTotal = By.xpath("//section[@id='cart-summary']//span[normalize-space()='Subtotal']/following-sibling::span");

    //Locator for step Delivery Info
    public By buttonHomeDeliveryType = By.xpath("//span/span[normalize-space()='Home Delivery']");

    //Locator for step Payment
    public By buttonContinueToPayment = By.xpath("//button[normalize-space()='Continue to Payment']");
    public By inputAdditionalInfo = By.xpath("//textarea[@placeholder='Type your text']");
    public By inputCouponCode = By.xpath("//input[@placeholder='Have coupon code? Enter here']");
    public By buttonCashOnDelivery = By.xpath("//span/span/span[normalize-space()='Cash on Delivery']");
    public By checkboxAgreeTermAndConditions = By.xpath("//span[normalize-space()='I agree to the']/preceding-sibling::span");
    public By buttonCompleteOrder = By.xpath("//button[normalize-space()='Complete Order']");
    public By paymentSubtotal = By.xpath("//th[normalize-space()='Subtotal']/following-sibling::td/span");
    public By totalShipping = By.xpath("//th[normalize-space()='Total Shipping']/parent::tr/td/span");
    public By totalOrderAmount = By.xpath("//span[normalize-space()='Total']/parent::th/following-sibling::td/strong/span");

    //Locator for step Confirmation
    public By orderConfirmMessage = By.xpath("//h1[normalize-space()='Thank You for Your Order!']");
    public By orderCodeDisplay = By.xpath("//h2/span");

    public By menuPurchaseHistory = By.xpath("//span[normalize-space()='Purchase History']");
    public By newestOrderCode = By.xpath("//tbody/tr[1]/td[1]/a");

    @Step("Verify Cart page is displayed")
    public void verifyCartPageIsDisplayed() {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(stepInCart), "Cart page is not displayed");
    }

    private int parsePrice(By locator) {
        String text = WebUI.getElementText(locator)
                .replace("$", "")
                .replace(",", "")
                .trim();
        if (text.contains(".")) {
            text = text.split("\\.")[0];
        }
        return Integer.parseInt(text);
    }

    private int parsePrice(String text) {
        text = text.replace("$", "")
                .replace(",", "")
                .trim();
        if (text.contains(".")) {
            text = text.split("\\.")[0];
        }
        return Integer.parseInt(text);
    }

    @Step("Check products in My Cart step: Expected {0} items, Expected names: {1}")
    public void myCartStep(int expectedTotalItems, String... expectedProductNames) {
        List<WebElement> listItems = WebUI.getWebElements(productInCartItems);
        Assert.assertEquals(listItems.size(), expectedTotalItems, "The number of items in Cart is incorrect.");

        int calculatedSubtotal = 0;
        for (int i = 1; i <= listItems.size(); i++) {
            By productNameLocator = By.xpath("//section[@id='cart-summary']//li[" + i + "]/div/div[1]/span[2]");
            By productPriceLocator = By.xpath("//section[@id='cart-summary']//li[" + i + "]/div/div[2]/span[2]");
            By productQuantityLocator = By.xpath("//section[@id='cart-summary']//li[" + i + "]/div/div[4]/div/input");

            String actualName = WebUI.getElementText(productNameLocator);
            if (i <= expectedProductNames.length) {
                Assert.assertEquals(actualName, expectedProductNames[i - 1], "The product name at item " + i + " is incorrect.");
            }

            int price = parsePrice(productPriceLocator);
            int quantity = Integer.parseInt(WebUI.getElementAttribute(productQuantityLocator, "value"));
            calculatedSubtotal += (price * quantity);
        }

        int actualSubtotal = parsePrice(subTotal);
        Assert.assertEquals(actualSubtotal, calculatedSubtotal, "The Subtotal price is incorrect.");

        WebUI.scrollToElement(buttonContinueToShipping);
        WebUI.clickElement(buttonContinueToShipping);
        WebUI.waitForPageLoaded();
    }

    @Step("Shipping Info step: Choose address at index {0}")
    public void shippingInfoStep(String addressIndex) {
        By addressOption = By.xpath("//form[@data-toggle='validator']/div/div/div[" + addressIndex + "]");
        WebUI.scrollToElement(addressOption);
        WebUI.clickElement(addressOption);
        WebUI.scrollToElement(buttonContinueToDeliveryInfo);
        WebUI.clickElement(buttonContinueToDeliveryInfo);
        WebUI.waitForPageLoaded();
    }

    @Step("Delivery Info step: Choose Home Delivery and proceed to payment")
    public void deliveryInfoStep() {
        WebUI.scrollToElement(buttonHomeDeliveryType);
        WebUI.clickElement(buttonHomeDeliveryType);
        WebUI.scrollToElement(buttonContinueToPayment);
        WebUI.clickElement(buttonContinueToPayment);
        WebUI.waitForPageLoaded();
    }

    @Step("Payment step with additional info: {0} and coupon: {1}")
    public void paymentStep(String additionalInfo, String couponCode) {
        WebUI.scrollToElement(inputAdditionalInfo);
        WebUI.setText(inputAdditionalInfo, additionalInfo);
        WebUI.scrollToElement(inputCouponCode);
        WebUI.setText(inputCouponCode, couponCode);
        WebUI.scrollToElement(buttonCashOnDelivery);
        WebUI.clickElement(buttonCashOnDelivery);
        WebUI.scrollToElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(checkboxAgreeTermAndConditions);

        WebUI.waitForElementVisible(paymentSubtotal);
        int subTotalPrice = parsePrice(paymentSubtotal);
        int totalShippingPrice = parsePrice(totalShipping);
        int actualTotalPrice = parsePrice(totalOrderAmount);

        Assert.assertEquals(actualTotalPrice, (subTotalPrice + totalShippingPrice), "The Total order amount is incorrect.");
        WebUI.clickElement(buttonCompleteOrder);
    }

    @Step("Complete Order step and verify success message")
    public void completeOrderStep() {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(orderConfirmMessage), "The Order Confirm message is not found.");
    }

    @Step("Verify order success in Purchase History")
    public void verifyOrderSuccess() {
        WebUI.scrollToElement(orderCodeDisplay);
        String expectedOrderCode = WebUI.getElementText(orderCodeDisplay);
        
        WebUI.openURL(PropertiesHelper.getValue("URL") + "/purchase_history");
        WebUI.waitForPageLoaded();
        
        String actualNewestOrderCode = WebUI.getElementText(newestOrderCode);
        Assert.assertEquals(actualNewestOrderCode, expectedOrderCode, "The order was unsuccessful or not found in history.");
    }

}
