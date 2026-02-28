package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import org.apache.xmlbeans.impl.xb.xsdschema.AppinfoDocument;
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
    public By productInCart = By.xpath("//section[@id='cart-summary']//li");
    public By productTax = By.xpath("//section[@id ='cart-summary']//li[1]/div/div[3]/span[2]");
    public By productTotal = By.xpath("//section[@id ='cart-summary']//li[1]/div/div[5]/span[2]");
    public By subTotal = By.xpath("//section[@id ='cart-summary']//span[normalize-space()= 'Subtotal']/following-sibling::span");

    //Locator for step Delivery Info
    public By buttonHomeDeliveryType = By.xpath("//span/span[normalize-space()='Home Delivery']");
    public By buttonAddNewAddress = By.xpath("//div/div/div/div/div[normalize-space() = 'Add New Address']");
    public By inputAddress = By.xpath("//textarea[@placeholder='Your Address']");
    public By inputCountry = By.xpath("//button[@title='Select your country']");
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
    public By paymentSubtotal = By.xpath("//th[normalize-space()='Subtotal']/parent::tr/td/span");
    public By numberOfItems = By.xpath("//h3[normalize-space()='Summary']/parent::div/div/span");
    public By totalShipping = By.xpath("//th[normalize-space()='Total Shipping']/parent::tr/td/span");
    public By total = By.xpath("//span[normalize-space()='Total']/parent::th/following-sibling::td/strong/span");

    //Locator for step Confirmation
    public By messageOrderConfirm = By.xpath("Your order has been placed successfully");
    public By orderCode = By.xpath("//h2/span");


    public void verifyCartPageIsDisplayed() {
        WebUI.waitForPageLoaded();
        clickClosePopup();
        boolean check = WebUI.checkElementExist(stepInCart);
        Assert.assertTrue(check, "Cart page is not displayed");
    }

    public void myCartStep(int totalItems, String productname1, String productName2) {
        List<WebElement> listItems = WebUI.getWebElements(productInCart);
        List<String> productPrice = new ArrayList<>();
        Assert.assertEquals(totalItems, listItems.size(), "The number of items in Cart is incorrect.");
        for (int i = 1; i <= listItems.size(); i++) {
            List<String> productList = new ArrayList<>();
            String xpathProductName = ("//section[@id ='cart-summary']//li[" + i + "]/div/div[1]/span[2]");
            String name = WebUI.getElementText(By.xpath(xpathProductName));
            productList.add(name);
            Assert.assertEquals(productList.get(0), productname1, "The product name is incorrect.");
            Assert.assertEquals(productList.get(1), productName2, "The product name is incorrect.");
        }
        for (int i = 1; i <= listItems.size(); i++) {
            String xpathProductPrice = "//section[@id ='cart-summary']//li[" + i + "]/div/div[2]/span[2]";
            String xpathProductQuanity = "//section[@id ='cart-summary']//li[" + i + "]/div/div[4]/div/input";
            String price = (WebUI.getElementText(By.xpath(xpathProductPrice)))
                    .replace("$", "")
                    .replace(",", "")
                    .split("\\.")[0];
            String quantity = WebUI.getElementText(By.xpath(xpathProductQuanity));
            productPrice.add(price);
            productPrice.add(quantity);
        }
        int i = 0;
        int totalPrice = (Integer.parseInt(productPrice.get(i)) * Integer.parseInt(productPrice.get(i++))) + (Integer.parseInt(productPrice.get((i++) + 1)) * Integer.parseInt(productPrice.get((i++) + 2)));
        int subTotalPrice = Integer.parseInt((WebUI.getElementText(subTotal))
                .replace("$", "")
                .replace(",", "")
                .split("\\.")[0]);
        Assert.assertTrue(totalPrice == subTotalPrice, "The Subtotal price is incorrect.");
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
        int subTotalPrice = Integer.parseInt((WebUI.getElementText(subTotal))
                .replace("$", "")
                .replace(",", "")
                .split("\\.")[0]);
        int totalPrice = Integer.parseInt((WebUI.getElementText(total))
                .replace("$", "")
                .replace(",", "")
                .split("\\.")[0]);
        int totalShippingPrice = Integer.parseInt((WebUI.getElementText(totalShipping))
                .replace("$", "")
                .replace(",", "")
                .split("\\.")[0]);
        Assert.assertTrue((subTotalPrice + totalShippingPrice) == totalPrice, "The Total is incorrect.");
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

    public void completeOrderStep(String newestOrderCode) {
        WebUI.waitForPageLoaded();
        clickClosePopup();
        WebUI.checkElementExist(messageOrderConfirm);
        WebUI.scrollToElement(orderCode);
        String code = WebUI.getElementText(orderCode);
        Assert.assertEquals(orderCode,newestOrderCode,"The order was not found in the purchase history..");
    }

}
