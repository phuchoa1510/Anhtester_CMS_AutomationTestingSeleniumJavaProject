package com.anhtester.ecommercecms.user.testcases;

import com.anhtester.common.BaseTest;
import com.anhtester.dataprovider.DataProviderManager;
import com.anhtester.ecommercecms.user.pages.*;
import com.anhtester.helpers.CurrencyHelper;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import com.anhtester.reports.AllureManager;
import com.anhtester.utils.LogUtils;
import io.qameta.allure.*;
import org.apache.commons.io.file.FilesUncheck;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

@Epic("Regression Test CMS")
@Feature("Order Management")
public class OrderTest extends BaseTest {

    BasePage basePage;
    LoginPage loginPage;
    HomePage homePage;
    ProductPage productPage;
    CartPage cartPage;
    UserDashboardPage userDashboardPage;
    PurchaseHistoryPage purchaseHistoryPage;

    @Test(priority = 1, dataProvider = "getOrderData", dataProviderClass = DataProviderManager.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that user can place an order successfully with dynamic products from Excel")
    public void testOrderSuccess(Hashtable<String, String> data) {

        String scenario = data.get("ScenarioName") == null ? "" : String.valueOf(data.get("ScenarioName"));
        String productName = data.get("ProductName") == null ? "" : String.valueOf(data.get("ProductName"));
        String addressIndex = data.get("AddressIndex") == null ? "1" : String.valueOf(data.get("AddressIndex"));
        String note = data.get("OrderNote") == null ? "Order Note" : String.valueOf(data.get("OrderNote"));
        int quantity = Integer.parseInt(data.get("Quantity") == null ? "1" : String.valueOf(data.get("Quantity")));
        String expectedResult = data.get("ExpectedResult") == null ? "" : String.valueOf(data.get("ExpectedResult")).toLowerCase();

        LogUtils.info(">>> BẮT ĐẦU TEST CASE: " + scenario);
        AllureManager.saveTextLog("Scenario: " + scenario + " | Product: " + productName);

        loginPage = new LoginPage();
        homePage = new HomePage();


        loginPage.loginCMS(
                PropertiesHelper.getValue("USER_EMAIL"),
                PropertiesHelper.getValue("USER_PASSWORD")
        );

        productPage = WebUI.retryExecute(
                () -> homePage.navigateToProductPage(productName),
                3,
                () -> {
                    WebUI.refreshPage();
                    WebUI.waitForPageLoaded();
                    WebUI.sleep(2);
                }
        );
        Assert.assertTrue(productPage.isProductPageDisplayed(productName), "LỖI: Trang sản phẩm không hiển thị đúng!");

        String actualName = productPage.getProductName();

        LogUtils.info("Expected Data: Name=" + productName);
        LogUtils.info("UI Data: Name=" + actualName);

        Assert.assertTrue(actualName.toLowerCase().contains(productName.toLowerCase()), "LỖI: Tên sản phẩm không chứa từ khóa tìm kiếm!");

        productPage = productPage.addToCart(quantity);
        cartPage = productPage.navigateToCartPage();

        Assert.assertTrue(cartPage.isCartPageDisplayed(), "LỖI: Trang giỏ hàng không hiển thị!");

        cartPage.clickContinueToShipping()
                .shippingInfoStep(addressIndex)
                .deliveryInfoStep()
                .paymentStep(note)
                .completeOrder();
        userDashboardPage = new UserDashboardPage();
        purchaseHistoryPage = new PurchaseHistoryPage();

        if (expectedResult.equals("success")) {
            Assert.assertTrue(cartPage.isOrderConfirmMessageDisplayed(), "LỖI: Trang xác nhận đặt hàng không hiển thị!");
            String orderCode = cartPage.getOrderCode();
            LogUtils.info("Mã đơn hàng vừa đặt: " + orderCode);

            basePage = new BasePage().navigateToUserDashboardPage();
            userDashboardPage.clickMenuPurchaseHistory();
            String newestOrderCode = purchaseHistoryPage.getNewestOrderCode();
            Assert.assertEquals(newestOrderCode, orderCode, "LỖI: Không tìm thấy mã đơn hàng vừa đặt!");
            LogUtils.info("KẾT QUẢ: " + scenario + " ==> [PASS]");
        }
    }
}
