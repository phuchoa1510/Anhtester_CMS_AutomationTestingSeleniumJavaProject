package com.anhtester.EcommerceCMS.User.testcases;

import com.anhtester.EcommerceCMS.User.pages.ProductPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.ExcelHelper;
import org.testng.annotations.Test;


public class GetProductInfoTest extends BaseTest {
    private ProductPage productpage;

    @Test
    public void getandWriteProductInfo() {
        productpage = new ProductPage();
        productpage.navigateToTargetProductPage("Laptop Dell XPS 15");
        productpage.verifyProductTargetPageIsDisplayed("Laptop Dell XPS 15");
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile("src/test/resources/testdata/testData.xlsx","Sheet1");
        String productName = productpage.getProductName();
        String sellerName = productpage.getProductSellerName();
        String productPrice = productpage.getProductPrice();
        String description =  productpage.getProductDescription();
        excel.setCellData(productName,0,1);
        excel.setCellData(sellerName,1,1);
        excel.setCellData(productPrice,2,1);
        excel.setCellData(description,3,1);
    }
}
