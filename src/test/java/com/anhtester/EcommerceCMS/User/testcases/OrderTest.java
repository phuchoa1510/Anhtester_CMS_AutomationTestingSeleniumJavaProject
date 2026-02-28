package com.anhtester.EcommerceCMS.User.testcases;

import com.anhtester.EcommerceCMS.User.pages.*;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.ExcelHelper;

public class OrderTest extends BaseTest {

    private DashboardPage dashboardPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private BasePage basePage;

    public void OrderSuccessTest(){
        loginPage = new LoginPage();
        basePage = new BasePage();
        dashboardPage = new DashboardPage();
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
        homePage.navigateToProductPage(excel.getCellData(0,2));
        productPage.verifyProductTargetPageIsDisplayed(excel.getCellData(0,2));
        productPage.addProductToCart();
        basePage.navigateToCartPage();
        cartPage.verifyCartPageIsDisplayed();
        cartPage.myCartStep(2,excel.getCellData(0,1),excel.getCellData(0,2));
        cartPage.shippingInfoStep("1");
        cartPage.deliveryInfoStep();
        cartPage.paymentStep("","");
        dashboardPage.navigateToDashboardPage();
        dashboardPage.clickMenuPurchaseHistory();
        String newestOrderCode = dashboardPage.getNewestOrderCode();
        cartPage.completeOrderStep(newestOrderCode);
    }
}
