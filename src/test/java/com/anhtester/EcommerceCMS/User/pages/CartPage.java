package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.security.PublicKey;

public class CartPage extends BasePage {


    public By buttonHome = By.xpath("//a[contains(text(),'Home')]");
    public By stepInCart = By.xpath("//div[@class='col active']/descendant::h3");
    public By buttonContinueToShipping = By.xpath("//a[normalize-space()='Continue to Shipping']");
    public By buttonContinueToDeliveryInfo = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    public By buttonHomeDeliveryType = By.xpath("//span/span[normalize-space()='Home Delivery']");
    public By buttonContinueToPayment = By.xpath("//button[normalize-space()='Continue to Payment']");
    public By inputAdditionalInfo = By.xpath("//textarea[@placeholder='Type your text']");
    public By inputCoupoCode = By.xpath("//input[@placeholder='Have coupon code? Enter here']");
    public By buttonCashOnDelivery = By.xpath("//span/span/span[normalize-space() ='Cash on Delivery']");
    public By checkboxAgreeTermAndConditions = By.xpath("//span[normalize-space() = 'I agree to the']/preceding-sibling::span");
    public By buttonCompleteOrder = By.xpath("//button[normalize-space()='Complete Order']");
    public By messageOrderSuccess = By.xpath("//h1[normalize-space()='Thank You for Your Order!']");

    String stepName = WebUI.getElementText(stepInCart);

    public void navigateToShippingStep(){
        WebUI.scrollToElement(buttonContinueToShipping);
        WebUI.clickElement(buttonContinueToShipping);
        WebUI.waitForPageLoaded();
    }
    public void navigateToDeliveryInfoStep(String chosen){
        String addressChosenDyamic = "//form[@data-toggle= 'validator']/div/div/div[" + chosen +"]";
        WebUI.scrollToElement(By.xpath(addressChosenDyamic));
        WebUI.clickElement(By.xpath(addressChosenDyamic));
        WebUI.scrollToElement(buttonContinueToDeliveryInfo);
        WebUI.clickElement(buttonContinueToDeliveryInfo);
        WebUI.waitForPageLoaded();
    }
    public void navigateToPaymentStep(){
        WebUI.scrollToElement(buttonHomeDeliveryType);
        WebUI.clickElement(buttonHomeDeliveryType);
        WebUI.scrollToElement(buttonContinueToPayment);
        WebUI.clickElement(buttonContinueToPayment);
        WebUI.waitForPageLoaded();
    }
    public void completeOrder(String additional, String couponCode){
        WebUI.scrollToElement(inputAdditionalInfo);
        WebUI.setText(inputAdditionalInfo,additional);
        WebUI.scrollToElement(inputCoupoCode);
        WebUI.setText(inputCoupoCode,couponCode);
        WebUI.scrollToElement(buttonCashOnDelivery);
        WebUI.clickElement(buttonCashOnDelivery);
        WebUI.scrollToElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(buttonCompleteOrder);
    }


}
