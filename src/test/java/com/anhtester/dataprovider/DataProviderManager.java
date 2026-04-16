package com.anhtester.dataprovider;

import com.anhtester.helpers.ExcelHelper;
import com.anhtester.helpers.PropertiesHelper;
import org.testng.annotations.DataProvider;

public class DataProviderManager {

    @DataProvider(name = "getLoginData", parallel = false)
    public Object[][] getLoginData() {
        ExcelHelper excelHelper = new ExcelHelper();
        String path = PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH");

        excelHelper.setExcelFile(path, "Login");
        int totalRows = excelHelper.getPhysicalNumberOfRows();

        return excelHelper.getDataHashTable(path, "Login", 1, totalRows - 1);
    }

    @DataProvider(name = "getProfileData", parallel = false)
    public Object[][] getProfileData() {
        ExcelHelper excelHelper = new ExcelHelper();
        String path = PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH");
        excelHelper.setExcelFile(path, "Profile");
        return excelHelper.getDataHashTable(path, "Profile", 1, excelHelper.getPhysicalNumberOfRows() - 1);
    }

    @DataProvider(name = "getOrderData", parallel = false)
    public Object[][] getOrderData() {
        ExcelHelper excelHelper = new ExcelHelper();
        String path = PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH");
        excelHelper.setExcelFile(path, "Order");
        return excelHelper.getDataHashTable(path, "Order", 1, excelHelper.getPhysicalNumberOfRows() - 1);
    }

    @DataProvider(name = "getProductInfoData", parallel = false)
    public Object[][] getProductInfoData() {
        ExcelHelper excelHelper = new ExcelHelper();
        String path = PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH");
        excelHelper.setExcelFile(path, "ProductInfo");
        return excelHelper.getDataHashTable(path, "ProductInfo", 1, excelHelper.getPhysicalNumberOfRows() - 1);
    }



}
