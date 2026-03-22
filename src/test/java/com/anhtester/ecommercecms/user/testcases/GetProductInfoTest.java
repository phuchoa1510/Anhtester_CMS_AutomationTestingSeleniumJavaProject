package com.anhtester.ecommercecms.user.testcases;

import com.anhtester.ecommercecms.user.pages.HomePage;
import com.anhtester.ecommercecms.user.pages.ProductPage;
import com.anhtester.common.BaseTestOld;
import com.anhtester.helpers.ExcelHelper;
import io.qameta.allure.*;

import org.testng.annotations.Test;


@Epic("Ecommerce CMS")
@Feature("Product Manage")
@Story("Search a ramdom product and get details")

public class GetProductInfoTest extends BaseTestOld {
    private ProductPage productpage;
    private HomePage homepage;

    @Description("Find Product :Laptop Dell XPS 15 and get details ")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void getProductInfo() {
        productpage = new ProductPage();
        homepage = new HomePage();
        homepage.navigateToProductPage("Laptop Dell XPS 15");
        productpage.verifyProductTargetPageIsDisplayed("Laptop Dell XPS 15");
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("src/test/resources/testdata/testData.xlsx", "Product_get_list");
        String productName = productpage.getProductName();
        String sellerName = productpage.getProductSellerName();
        String productPrice = productpage.getProductPrice();
        String description = productpage.getProductDescription();
        excel.setCellData(productName, 0, 1);
        excel.setCellData(sellerName, 1, 1);
        excel.setCellData(productPrice, 2, 1);
        excel.setCellData(description, 3, 1);
    }
}
