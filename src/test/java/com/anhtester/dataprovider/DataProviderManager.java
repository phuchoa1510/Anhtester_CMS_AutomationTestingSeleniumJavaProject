package com.anhtester.dataprovider;

import com.anhtester.helpers.ExcelHelper;
import com.anhtester.helpers.PropertiesHelper;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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
        Object[][] allData = excelHelper.getDataHashTable(path, "Profile", 1, excelHelper.getPhysicalNumberOfRows() - 1);
        List<Object[]> filteredList = new ArrayList<>();
        for (Object[] row : allData) {
            @SuppressWarnings("unchecked")
            Hashtable<String, String> data = (Hashtable<String, String>) row[0];

            String action = data.get("Action") == null ? "" : String.valueOf(data.get("Action"));
            if ("Run".equalsIgnoreCase(action)) {
                filteredList.add(row);
            }
        }
        return filteredList.toArray(new Object[0][]);
    }

    @DataProvider(name = "getOrderData", parallel = false)
    public Object[][] getOrderData() {
        ExcelHelper excelHelper = new ExcelHelper();
        String path = PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH");
        excelHelper.setExcelFile(path, "NewProduct");
        return excelHelper.getDataHashTable(path, "NewProduct", 1, excelHelper.getPhysicalNumberOfRows() - 1);
    }

    @DataProvider(name = "getProductInfoData", parallel = false)
    public Object[][] getProductInfoData() {
        ExcelHelper excelHelper = new ExcelHelper();
        String path = PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH");
        excelHelper.setExcelFile(path, "ProductInfo");
        return excelHelper.getDataHashTable(path, "ProductInfo", 1, excelHelper.getPhysicalNumberOfRows() - 1);
    }


}
