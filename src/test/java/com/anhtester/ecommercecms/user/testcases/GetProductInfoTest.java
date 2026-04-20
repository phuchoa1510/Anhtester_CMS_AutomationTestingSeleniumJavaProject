package com.anhtester.ecommercecms.user.testcases;

import com.anhtester.common.BaseTest;
import com.anhtester.dataprovider.DataProviderManager;
import com.anhtester.ecommercecms.user.pages.HomePage;
import com.anhtester.ecommercecms.user.pages.LoginPage;
import com.anhtester.ecommercecms.user.pages.ProductPage;
import com.anhtester.helpers.CurrencyHelper;
import com.anhtester.keywords.WebUI;
import com.anhtester.reports.AllureManager;
import com.anhtester.utils.LogUtils;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

@Slf4j
@Epic("Regression Test CMS")
@Feature("Product Information")
public class GetProductInfoTest extends BaseTest {

    HomePage homePage;
    ProductPage productPage;
    LoginPage loginPage;

    @Test(priority = 1, dataProvider = "getProductInfoData", dataProviderClass = DataProviderManager.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify product detailed information from dynamic Excel data")
    public void testGetProductInfo(Hashtable<String, String> data) {
        String scenario = data.get("ScenarioName") == null ? "" : String.valueOf(data.get("ScenarioName"));
        String targetProduct = data.get("ProductName") == null ? "" : String.valueOf(data.get("ProductName"));
        String expectedPrice = data.get("Price") == null ? "" : String.valueOf(CurrencyHelper.getNumericPrice(data.get("Price")));
        String expectedSeller = data.get("SellerName") == null ? "" : String.valueOf(data.get("SellerName"));

        LogUtils.info(">>> BẮT ĐẦU TEST CASE: " + scenario);
        AllureManager.saveTextLog("Scenario: " + scenario + " | Product: " + targetProduct);

        homePage = new HomePage();
        loginPage = new LoginPage();
        loginPage.loginCMS(
                com.anhtester.helpers.PropertiesHelper.getValue("USER_EMAIL"),
                com.anhtester.helpers.PropertiesHelper.getValue("USER_PASSWORD")
        );


        try {
            productPage = homePage.navigateToProductPage(targetProduct);

        }catch(Exception e){
            LogUtils.warn(">>> Lần 1 thất bại do lỗi Web. Đang refresh và thử lại lần 2...");
            WebUI.refreshPage(); // Refresh trang
            WebUI.waitForPageLoaded();
            productPage = homePage.navigateToProductPage(targetProduct);
        }
         Assert.assertTrue(productPage.isProductPageDisplayed(targetProduct), "LỖI: Trang sản phẩm không hiển thị đúng!");

        productPage = homePage.navigateToProductPage(targetProduct);
        Assert.assertTrue(productPage.isProductPageDisplayed(targetProduct), "LỖI: Trang sản phẩm không hiển thị không đúng !");

        String actualName = productPage.getProductName();
        String actualPrice = String.valueOf(CurrencyHelper.getNumericPrice(productPage.getProductPrice()));
        String actualSeller = productPage.getProductSellerName();

        LogUtils.info("Expected Data: Name=" + targetProduct + ", Price=" + expectedPrice + ", Seller=" + expectedSeller);
        LogUtils.info("UI Data: Name=" + actualName + ", Price=" + actualPrice + ", Seller=" + actualSeller);

        Assert.assertTrue(actualName.toLowerCase().contains(targetProduct.toLowerCase()), "LỖI: Tên sản phẩm không chứa từ khóa tìm kiếm!");

        if (!expectedPrice.isEmpty()) {
            Assert.assertEquals(actualPrice, expectedPrice, "LỖI: Giá sản phẩm không khớp!");
        }

        if (!expectedSeller.isEmpty()) {
            Assert.assertEquals(actualSeller, expectedSeller, "LỖI: Tên người bán không khớp!");
        }

        LogUtils.info("KẾT QUẢ: " + scenario + " ==> [PASS]");
    }
}
