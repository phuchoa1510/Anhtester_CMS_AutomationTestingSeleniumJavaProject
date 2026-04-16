package com.anhtester.ecommercecms.user.pages.old;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.old.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CartPage extends BasePage {

    private final By buttonHome = By.xpath("//a[contains(text(),'Home')]");

    // Locators for My Cart step
    private final By stepInCart = By.xpath("//div[@class='col active']");
    private final By buttonContinueToShipping = By.xpath("//a[normalize-space()='Continue to Shipping']");
    private final By buttonContinueToDeliveryInfo = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    private final By productInCartItems = By.xpath("//section[@id='cart-summary']//li");
    private final By subTotal = By.xpath("//section[@id='cart-summary']//span[normalize-space()='Subtotal']/following-sibling::span");

    // Locators for Delivery Info step
    private final By buttonHomeDeliveryType = By.xpath("//span/span[normalize-space()='Home Delivery']");

    // Locators for Payment step
    private final By buttonContinueToPayment = By.xpath("//button[normalize-space()='Continue to Payment']");
    private final By inputAdditionalInfo = By.xpath("//textarea[@placeholder='Type your text']");
    private final By inputCouponCode = By.xpath("//input[@placeholder='Have coupon code? Enter here']");
    private final By buttonCashOnDelivery = By.xpath("//span/span/span[normalize-space()='Cash on Delivery']");
    private final By checkboxAgreeTermAndConditions = By.xpath("//span[normalize-space()='I agree to the']/preceding-sibling::span");
    private final By buttonCompleteOrder = By.xpath("//button[normalize-space()='Complete Order']");
    private final By paymentSubtotal = By.xpath("//th[normalize-space()='Subtotal']/following-sibling::td/span");
    private final By totalShipping = By.xpath("//th[normalize-space()='Total Shipping']/parent::tr/td/span");
    private final By totalOrderAmount = By.xpath("//span[normalize-space()='Total']/parent::th/following-sibling::td/strong/span");

    // Locators for Confirmation step
    private final By orderConfirmMessage = By.xpath("//h1[normalize-space()='Thank You for Your Order!']");
    private final By orderCodeDisplay = By.xpath("//h2/span");

    private final By menuPurchaseHistory = By.xpath("//span[normalize-space()='Purchase History']");
    private final By newestOrderCode = By.xpath("//tbody/tr[1]/td[1]/a");

    // Dynamic XPaths
    private final String productNameXpath = "//section[@id='cart-summary']//li[%d]/div/div[1]/span[2]";
    private final String productPriceXpath = "//section[@id='cart-summary']//li[%d]/div/div[2]/span[2]";
    private final String productQuantityXpath = "//section[@id='cart-summary']//li[%d]/div/div[4]/div/input";
    private final String addressOptionXpath = "//form[@data-toggle='validator']/div/div/div[%s]";

    @Step("Verify Cart page is displayed")
    public void verifyCartPageIsDisplayed() {
        WebUI.waitForPageLoaded();
        Assert.assertTrue(WebUI.checkElementExist(stepInCart), "Cart page is not displayed");
    }

    private int parsePrice(By locator) {
        String text = WebUI.getElementText(locator);
        return parsePrice(text);
    }

    private int parsePrice(String text) {
        text = text.replace("$", "").replace(",", "").trim();
        if (text.contains(".")) {
            text = text.split("\\.")[0];
        }
        return Integer.parseInt(text);
    }

    @Step("Check products in My Cart step: Expected {0} items, Expected names: {1}")
    public void checkMyCartStep(int expectedTotalItems, String... expectedProductNames) {
        List<WebElement> listItems = WebUI.getWebElements(productInCartItems);
        Assert.assertEquals(listItems.size(), expectedTotalItems, "The number of items in Cart is incorrect.");

        int calculatedSubtotal = 0;
        for (int i = 1; i <= listItems.size(); i++) {
            By productNameLocator = By.xpath(String.format(productNameXpath, i));
            By productPriceLocator = By.xpath(String.format(productPriceXpath, i));
            By productQuantityLocator = By.xpath(String.format(productQuantityXpath, i));

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
        By addressOption = By.xpath(String.format(addressOptionXpath, addressIndex));
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
