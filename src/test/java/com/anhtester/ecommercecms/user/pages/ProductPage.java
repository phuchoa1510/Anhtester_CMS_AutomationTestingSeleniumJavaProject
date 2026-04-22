package com.anhtester.ecommercecms.user.pages;

import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {

    private final By buttonAddToCart = By.xpath("//span[normalize-space() = 'Add to cart']");
    private final By inputQuantity = By.xpath("//input[@name='quantity']");
    private final By successMessage = By.xpath("//div[contains(@class, 'toast-success')]");
    private final By productTitle = By.xpath("//h1");
    private final By cartIcon = By.xpath("//i[@class='la la-shopping-cart']/parent::a");

    private final By messageAddToCartSuccess = By.xpath("//h3[normalize-space()='Item added to your cart!']");
    private final By buttonCloseModal = By.xpath("//div[@id='addToCart-modal-body']/preceding-sibling::button");
    private final By buttonProceedToCheckout = By.xpath("//a[normalize-space()='Proceed to Checkout']");
    private final By buttonBackToShopping = By.xpath("//button[normalize-space()='Back to shopping']");


    private By productItemByName(String name) {
        return By.xpath("//a[contains(@class, 'd-block text-reset') and contains(text(), '" + name + "')]");
    }

    private final By sellerName = By.xpath("//div/small[normalize-space()= 'Sold by:']/parent::div");
    private final By productPrice = By.xpath("//div[normalize-space()='Price:']/parent::div/following-sibling::div//strong");
    private final By productDescription = By.xpath("//a[normalize-space()='Description']/parent::div/following-sibling::div/div[@id='tab_default_1']");
    private final By buttonBuyNow = By.xpath("//button[normalize-space()='Buy Now']");

    public String getProductName() {
        return WebUI.getElementText(productTitle);
    }

    public String getProductSellerName() {
        String fullTextSeller = WebUI.getElementText(sellerName);
        if (fullTextSeller.contains("\n")) {
            return fullTextSeller.split("\n")[1].trim();
        }
        return fullTextSeller;
    }

    public String getProductPrice() {
        return WebUI.getElementText(productPrice);
    }

    public String getProductDescription() {
        return WebUI.getElementText(productDescription);
    }

    public boolean isSuccessMessageDisplayed() {
        return WebUI.checkElementExist(messageAddToCartSuccess, 5, 500);
    }

    public boolean isProductPageDisplayed(String expectedName) {
        return WebUI.getElementText(productTitle).toLowerCase().contains(expectedName.toLowerCase());
    }

    @Step("Buy a product with quantity: {0}")
    public CartPage buyProduct(int quantity) {
        if (quantity > 1) {
            WebUI.setText(inputQuantity, String.valueOf(quantity));
        }
        WebUI.scrollToElement(buttonBuyNow);
        WebUI.clickElement(buttonBuyNow);
        WebUI.waitForPageLoaded();
        return new CartPage();
    }

    @Step("Add product to cart with quantity: {0}")
    public ProductPage addToCart(int quantity) {
        if (quantity > 1) {
            WebUI.setText(inputQuantity, String.valueOf(quantity));
        }
        WebUI.scrollToElement(buttonAddToCart);
        WebUI.clickElement(buttonAddToCart);
        return this;
    }

    @Step("Navigate to Cart Page")
    public CartPage navigateToCartPage() {
        WebUI.waitForElementVisible(buttonProceedToCheckout);
        WebUI.scrollToElement(buttonProceedToCheckout);
        WebUI.clickElement(buttonProceedToCheckout);
        return new CartPage();
    }

    //@Step("Navigate to Cart Page")
    //public CartPage navigateToCartPage() {
        //WebUI.clickElement(cartIcon);
        //WebUI.waitForPageLoaded();
        //return new CartPage();
    //}

    public ProductPage backToShopping() {
        WebUI.scrollToElement(buttonBackToShopping);
        WebUI.clickElement(buttonBackToShopping);
        return this;
    }

}
