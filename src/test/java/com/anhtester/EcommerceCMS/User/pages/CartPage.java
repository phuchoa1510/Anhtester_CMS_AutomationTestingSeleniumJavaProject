package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CartPage extends BasePage {


    public By buttonHome = By.xpath("//a[contains(text(),'Home')]");
    //Locator for step My Cart
    public By stepInCart = By.xpath("//div[@class='col active']");
    public By buttonContinueToShipping = By.xpath("//a[normalize-space()='Continue to Shipping']");
    public By buttonContinueToDeliveryInfo = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    public By productInCart = By.xpath("//section[@id='cart-summary']//li");
    public By productName = By.xpath("//section[@id ='cart-summary']//li[1]/div/div[1]/span[2]");
    public By productPrice = By.xpath("//section[@id ='cart-summary']//li[1]/div/div[1]/span[2]");
    public By productTax = By.xpath("//section[@id ='cart-summary']//li[1]/div/div[1]/span[2]");
    public By productQuantity = By.xpath("//section[@id ='cart-summary']//li[1]/div/div[4]/div/input");
    public By productTotal = By.xpath("//section[@id ='cart-summary']//li[1]/div/div[5]/span[2]");
    public By subTotal = By.xpath("//section[@id ='cart-summary']//span[normalize-space()= 'Subtotal']/following-sibling::span");

    //Locator for step Delivery Info
    public By buttonHomeDeliveryType = By.xpath("//span/span[normalize-space()='Home Delivery']");
    public By buttonAddNewAddress = By.xpath("//div/div/div/div/div[normalize-space() = 'Add New Address']");
    public By inputAddress = By.xpath("//textarea[@placeholder='Your Address']");
    public By inputCountry  = By.xpath("//button[@title='Select your country']");
    public By inputSearchCountry = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public By inputState = By.xpath("//body[1]/div[7]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[2]/div[1]/button[1]");
    public By inputSearchState = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public By inputCity = By.xpath("//body[1]/div[7]/div[1]/div[1]/form[1]/div[1]/div[1]/div[4]/div[2]/div[1]/button[1]");
    public By inputSearhCity = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public By inputPostalCode = By.xpath("//input[@placeholder='Your Postal Code']");
    public By inputPhone = By.xpath("//input[@placeholder='+880']");
    public By buttonSave = By.xpath("//button[normalize-space()='Save']");
    //Locator for step Payment
    public By buttonContinueToPayment = By.xpath("//button[normalize-space()='Continue to Payment']");
    public By inputAdditionalInfo = By.xpath("//textarea[@placeholder='Type your text']");
    public By inputCoupoCode = By.xpath("//input[@placeholder='Have coupon code? Enter here']");
    public By buttonCashOnDelivery = By.xpath("//span/span/span[normalize-space() ='Cash on Delivery']");
    public By checkboxAgreeTermAndConditions = By.xpath("//span[normalize-space() = 'I agree to the']/preceding-sibling::span");
    public By buttonCompleteOrder = By.xpath("//button[normalize-space()='Complete Order']");
    public By messageOrderSuccess = By.xpath("//h1[normalize-space()='Thank You for Your Order!']");
    public By numberOfItems = By.xpath("//h3[normalize-space()='Summary']/parent::div/div/span");
    public By totalShipping = By.xpath("//th[normalize-space()='Total Shipping']/parent::tr/td/span");
    public By total = By.xpath("//span[normalize-space()='Total']/parent::th/following-sibling::td/strong/span");
    //Locator for step Confirmation
    public By messageOrderConfirm = By.xpath("Your order has been placed successfully");
    public By orderCode = By.xpath("//h2/span");


    public void verifyCartPageIsDisplayed() {
        boolean check = WebUI.checkElementExist(stepInCart);
        Assert.assertTrue(check,"Cart page is not displayed");
    }

    public void myCartStep() {
        WebUI.scrollToElement(buttonContinueToShipping);
        WebUI.clickElement(buttonContinueToShipping);
        WebUI.waitForPageLoaded();
    }

    public void shippingInfoStep(String chosen) {
        String addressChosenDyamic = "//form[@data-toggle= 'validator']/div/div/div[" + chosen + "]";
        WebUI.scrollToElement(By.xpath(addressChosenDyamic));
        WebUI.clickElement(By.xpath(addressChosenDyamic));
        WebUI.scrollToElement(buttonContinueToDeliveryInfo);
        WebUI.clickElement(buttonContinueToDeliveryInfo);
        WebUI.waitForPageLoaded();
    }

    public void deliveryInfoStep() {
        WebUI.scrollToElement(buttonHomeDeliveryType);
        WebUI.clickElement(buttonHomeDeliveryType);
        WebUI.scrollToElement(buttonContinueToPayment);
        WebUI.clickElement(buttonContinueToPayment);
        WebUI.waitForPageLoaded();
    }

    public void paymentStep(String additional, String couponCode) {
        WebUI.scrollToElement(inputAdditionalInfo);
        WebUI.setText(inputAdditionalInfo, additional);
        WebUI.scrollToElement(inputCoupoCode);
        WebUI.setText(inputCoupoCode, couponCode);
        WebUI.scrollToElement(buttonCashOnDelivery);
        WebUI.clickElement(buttonCashOnDelivery);
        WebUI.scrollToElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(checkboxAgreeTermAndConditions);
        WebUI.clickElement(buttonCompleteOrder);
    }

}
