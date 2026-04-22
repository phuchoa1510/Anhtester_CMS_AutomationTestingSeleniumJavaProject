package com.anhtester.helpers;

import java.awt.Color;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.anhtester.utils.LogUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper {

    private FileInputStream fis;
    private FileOutputStream fileOut;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private Row row;
    private CellStyle cellstyle;
    private Color mycolor;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String ExcelPath, String SheetName) {
        try {
            File f = new File(ExcelPath);

            if (!f.exists()) {
                LogUtils.info("File doesn't exist.");
            }

            fis = new FileInputStream(ExcelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(SheetName);

            if (sheet == null) {
                throw new Exception("Sheet name doesn't exist.");
            }

            this.excelFilePath = ExcelPath;


            sheet.getRow(0).forEach(cell -> {
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            LogUtils.info(e.getMessage());
        }
    }

    public String getCellData(int columnIndex, int rowIndex) {
        try {
            cell = sheet.getRow(rowIndex).getCell(columnIndex);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }


    public String getCellData(String columnName, int rowIndex) {

        if (columns.get(columnName) == null) {
            try {
                throw new Exception("Column name doesn't exist.");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return getCellData(columns.get(columnName), rowIndex);
    }


    public void setCellData(String text, int columnIndex, int rowIndex) {
        try {
            row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            cell = row.getCell(columnIndex);

            if (cell == null) {
                cell = row.createCell(columnIndex);
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }


    public void setCellData(String text, String columnName, int rowIndex) {
        try {
            row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            cell = row.getCell(columns.get(columnName));

            if (cell == null) {
                cell = row.createCell(columns.get(columnName));
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            LogUtils.info("Set cell data successfully!");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public Object[][] getExcelData(String filePath, String sheetName) {
        Object[][] data = null;
        Workbook workbook = null;
        try {

            FileInputStream fis = new FileInputStream(filePath);


            workbook = new XSSFWorkbook(fis);


            Sheet sh = workbook.getSheet(sheetName);


            Row row = sh.getRow(0);


            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();

            LogUtils.info(noOfRows + " - " + noOfCols);

            Cell cell;
            data = new Object[noOfRows - 1][noOfCols];


            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sh.getRow(i);
                    cell = row.getCell(j);

                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            data[i - 1][j] = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BLANK:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        default:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                    }
                }
            }
        } catch (Exception e) {
            LogUtils.info("The exception is:" + e.getMessage());
            throw new RuntimeException(e);
        }
        return data;
    }


    public int getColumns() {
        try {
            row = sheet.getRow(0);
            return row.getLastCellNum();
        } catch (Exception e) {
            LogUtils.info(e.getMessage());
            throw (e);
        }
    }


    public int getLastRowNum() {
        return sheet.getLastRowNum();
    }


    public int getPhysicalNumberOfRows() {
        return sheet.getPhysicalNumberOfRows();
    }

    public Object[][] getDataHashTable(String excelPath, String sheetName, int startRow, int endRow) {
        LogUtils.info("Excel Path: " + excelPath);
        Object[][] data = null;

        try {
            File f = new File(excelPath);
            if (!f.exists()) {
                LogUtils.info("File Excel path not found.");
            }

            fis = new FileInputStream(excelPath);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            int columns = getColumns();


            java.util.List<Hashtable<String, String>> dataList = new java.util.ArrayList<>();

            for (int rowNums = startRow; rowNums <= endRow; rowNums++) {
                Hashtable<String, String> table = new Hashtable<>();
                boolean isRowEmpty = true;

                for (int colNum = 0; colNum < columns; colNum++) {
                    String headerName = getCellData(colNum, 0);
                    String cellValue = getCellData(colNum, rowNums);


                    if (cellValue != null && !cellValue.trim().isEmpty()) {
                        isRowEmpty = false;
                    }
                    table.put(headerName, cellValue);
                }


                if (!isRowEmpty) {
                    dataList.add(table);
                }
            }


            data = new Object[dataList.size()][1];
            for (int i = 0; i < dataList.size(); i++) {
                data[i][0] = dataList.get(i);
            }

            LogUtils.info("Total rows with data found: " + dataList.size());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }


    public Object[][] getDataFromSpecificRows(String excelPath, String sheetName, int[] rowNumbers) {
        LogUtils.info("Excel File: " + excelPath);
        LogUtils.info("Sheet Name: " + sheetName);
        LogUtils.info("Reading data from specific rows: " + Arrays.toString(rowNumbers));

        Object[][] data = null;

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                LogUtils.info("File Excel path not found.");
                throw new FileNotFoundException("File Excel path not found.");
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                LogUtils.info("Sheet name not found.");
                throw new RuntimeException("Sheet name not found.");
            }

            int columns = getColumns();
            LogUtils.info("Column count: " + columns);


            data = new Object[rowNumbers.length][columns];


            for (int i = 0; i < rowNumbers.length; i++) {
                int rowNum = rowNumbers[i];

                if (rowNum > sheet.getLastRowNum()) {
                    LogUtils.info("WARNING: Row " + rowNum + " does not exist in the sheet.");

                    for (int j = 0; j < columns; j++) {
                        data[i][j] = "";
                    }
                    continue;
                }

                for (int j = 0; j < columns; j++) {
                    data[i][j] = getCellData(j, rowNum);
                }
            }


            workbook.close();
            fis.close();

        } catch (Exception e) {
            LogUtils.info("Exception in getDataFromSpecificRows: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }


    public Object[][] getDataHashTableFromSpecificRows(String excelPath, String sheetName, int[] rowNumbers) {
        LogUtils.info("Excel File: " + excelPath);
        LogUtils.info("Sheet Name: " + sheetName);
        LogUtils.info("Reading data from specific rows: " + Arrays.toString(rowNumbers));

        Object[][] data = null;

        try {
            File f = new File(excelPath);

            if (!f.exists()) {
                LogUtils.info("File Excel path not found.");
                throw new FileNotFoundException("File Excel path not found.");
            }

            fis = new FileInputStream(excelPath);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                LogUtils.info("Sheet name not found.");
                throw new RuntimeException("Sheet name not found.");
            }

            int columns = getColumns();

            data = new Object[rowNumbers.length][1];


            for (int i = 0; i < rowNumbers.length; i++) {
                int rowNum = rowNumbers[i];

                if (rowNum > sheet.getLastRowNum()) {
                    LogUtils.info("WARNING: Row " + rowNum + " does not exist in the sheet.");
                    data[i][0] = new Hashtable<String, String>();
                    continue;
                }

                Hashtable<String, String> table = new Hashtable<>();
                for (int j = 0; j < columns; j++) {

                    String columnName = getCellData(j, 0);

                    String cellValue = getCellData(j, rowNum);

                    table.put(columnName, cellValue);
                }
                data[i][0] = table;
            }


            workbook.close();
            fis.close();

        } catch (Exception e) {
            LogUtils.info("Exception in getDataHashTableFromSpecificRows: " + e.getMessage());
            e.printStackTrace();
        }

        return data;
    }

}