package com.anhtester.ecommercecms.user.testcases;

import com.anhtester.common.BaseTest;
import com.anhtester.dataprovider.DataProviderManager;
import com.anhtester.ecommercecms.user.pages.*;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.reports.AllureManager;
import com.anhtester.utils.LogUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

@Epic("Regression Test CMS")
@Feature("Order Management")
public class OrderTest extends BaseTest {

    LoginPage loginPage;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;

    @Test(priority = 1, dataProvider = "getOrderData", dataProviderClass = DataProviderManager.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that user can place an order successfully with dynamic products from Excel")
    public void testOrderSuccess(Hashtable<String, String> data) {
        String scenario = data.get("ScenarioName") == null ? "" : String.valueOf(data.get("ScenarioName"));
        String productName = data.get("ProductName") == null ? "" : String.valueOf(data.get("ProductName"));
        String addressIndex = data.get("AddressIndex") == null ? "1" : String.valueOf(data.get("AddressIndex"));
        String note = data.get("OrderNote") == null ? "Order Note" : String.valueOf(data.get("OrderNote"));
        String expectedResult = data.get("ExpectedResult") == null ? "" : String.valueOf(data.get("ExpectedResult")).toLowerCase();

        LogUtils.info(">>> BẮT ĐẦU TEST CASE: " + scenario);
        AllureManager.saveTextLog("Scenario: " + scenario + " | Product: " + productName);

        loginPage = new LoginPage();
        homePage = new HomePage();


        loginPage.loginCMS(
                PropertiesHelper.getValue("USER_EMAIL"),
                PropertiesHelper.getValue("USER_PASSWORD")
        );


        productPage = homePage.navigateToProductPage(productName);
        Assert.assertTrue(productPage.isProductPageDisplayed(productName), "LỖI: Trang sản phẩm không hiển thị đúng tên: " + productName);
        productPage.addToCart(1);
        Assert.assertTrue(productPage.isSuccessMessageDisplayed(), "LỖI: Không thấy thông báo thêm sản phẩm vào giỏ hàng!");


        cartPage = productPage.navigateToCartPage();
        Assert.assertTrue(cartPage.isCartPageDisplayed(), "LỖI: Trang giỏ hàng không hiển thị!");

        cartPage.clickContinueToShipping()
                .shippingInfoStep(addressIndex)
                .deliveryInfoStep()
                .paymentStep(note)
                .completeOrder();


        if (expectedResult.equals("success")) {
            Assert.assertTrue(cartPage.isOrderConfirmMessageDisplayed(), "LỖI: Trang xác nhận đặt hàng không hiển thị!");
            String orderCode = cartPage.getOrderCode();
            LogUtils.info("Mã đơn hàng vừa đặt: " + orderCode);


            cartPage.goToPurchaseHistory();
            String newestOrderCode = cartPage.getNewestOrderCode();
            Assert.assertEquals(newestOrderCode, orderCode, "LỖI: Mã đơn hàng trong lịch sử không khớp với mã vừa đặt!");
            LogUtils.info("KẾT QUẢ: " + scenario + " ==> [PASS]");
        }
    }
}
