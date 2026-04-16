package com.anhtester.ecommercecms.user.testcases;

import com.anhtester.common.BaseTest;
import com.anhtester.dataprovider.DataProviderManager;
import com.anhtester.ecommercecms.user.pages.HomePage;
import com.anhtester.ecommercecms.user.pages.ProductPage;
import com.anhtester.reports.AllureManager;
import com.anhtester.utils.LogUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

@Epic("Regression Test CMS")
@Feature("Product Information")
public class GetProductInfoTest extends BaseTest {

    HomePage homePage;
    ProductPage productPage;

    @Test(priority = 1, dataProvider = "getProductInfoData", dataProviderClass = DataProviderManager.class)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify product detailed information from dynamic Excel data")
    public void testGetProductInfo(Hashtable<String, String> data) {
        String scenario = data.get("ScenarioName") == null ? "" : String.valueOf(data.get("ScenarioName"));
        String targetProduct = data.get("SearchKey") == null ? "" : String.valueOf(data.get("SearchKey"));
        String expectedPrice = data.get("ExpectedPrice") == null ? "" : String.valueOf(data.get("ExpectedPrice"));
        String expectedSeller = data.get("ExpectedSeller") == null ? "" : String.valueOf(data.get("ExpectedSeller"));

        LogUtils.info(">>> BẮT ĐẦU TEST CASE: " + scenario);
        AllureManager.saveTextLog("Scenario: " + scenario + " | Product: " + targetProduct);

        homePage = new HomePage();


        productPage = homePage.navigateToProductPage(targetProduct);
        Assert.assertTrue(productPage.isProductPageDisplayed(targetProduct), "LỖI: Trang sản phẩm không hiển thị đúng!");


        String actualName = productPage.getProductName();
        String actualPrice = productPage.getProductPrice();
        String actualSeller = productPage.getProductSellerName();

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
