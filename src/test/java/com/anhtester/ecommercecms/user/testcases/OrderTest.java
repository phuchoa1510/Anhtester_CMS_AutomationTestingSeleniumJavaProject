package com.anhtester.ecommercecms.user.testcases;

import com.anhtester.ecommercecms.user.pages.*;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.ExcelHelper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

@Epic("Ecommerce CMS")
@Feature("Order Management")
public class OrderTest extends BaseTest {

    private UserDashboardPage userDashboardPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private BasePage basePage;

    @Test
    @Story("Place order successfully")
    @Description("Verify that user can add products to cart and complete the order successfully")
    public void OrderSuccessTest(){
        cartPage  = new CartPage();
        loginPage = new LoginPage();
        basePage = new BasePage();
        userDashboardPage = new UserDashboardPage();
        productPage = new ProductPage();
        homePage = new HomePage();
        loginPage = new LoginPage();
        loginPage.loginCMS();
        loginPage.verifyLoginSuccess();
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("src/test/resources/testdata/testData.xlsx", "Product_list");
        homePage.navigateToProductPage(excel.getCellData(0,1));
        productPage.verifyProductTargetPageIsDisplayed(excel.getCellData(0,1));
        productPage.addProductToCart();
        productPage.verifyProductAddToCartSuccess();
        homePage.navigateToProductPage(excel.getCellData(0,2));
        productPage.verifyProductTargetPageIsDisplayed(excel.getCellData(0,2));
        productPage.addProductToCart();
        productPage.verifyProductAddToCartSuccess();
        basePage.navigateToCartPage();
        cartPage.verifyCartPageIsDisplayed();
        cartPage.checkMyCartStep(2,excel.getCellData(0,1),excel.getCellData(0,2));
        cartPage.shippingInfoStep("1");
        cartPage.deliveryInfoStep();
        cartPage.paymentStep("","");
        cartPage.completeOrderStep();
        cartPage.verifyOrderSuccess();
    }
}
