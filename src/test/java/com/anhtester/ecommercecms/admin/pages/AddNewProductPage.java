package com.anhtester.ecommercecms.admin.pages;

import org.openqa.selenium.By;

public class AddNewProductPage {

    private By inputProductName = By.xpath("//input[@placeholder='Product Name']");
    private By dropdownCategory = By.xpath("//button[@title='Sport shoes']");
    private By inputCategorySearch = By.xpath("//button[@data-id ='category_id']/following-sibling::div/div/input");
    private final String optionCategoryXpath = "//span[@class='text'][normalize-space()='%s']";
    private By inputBrand = By.xpath("//button[@title='Select Brand']");
    private final String optionBrandXpath = "//span[@class='text'][normalize-space()='%s']";
    private By inputUnit = By.xpath("//label[normalize-space()='Unit']/following-sibling::div/input");
    private By inputWeight = By.xpath("//small[normalize-space()='(In Kg)']//parent::label/following-sibling::div/input");
    private By inputMinimumPurchaseQty = By.xpath("//input[@name='min_qty']");
    private By inputTag = By.xpath("//tags[@role='tagslist']");
    private By inputBarcode = By.xpath("//input[@placeholder='Barcode']");
    private By buttonBrowseGalleryImages =  By.xpath("//small[normalize-space()='(600x600)']/parent::label/following-sibling::div/div/div[normalize-space()='Browse']");
    private By getButtonBrowseGalleryImagesThumnailImage = By.xpath("//small[normalize-space()='Browse']");
    private By inputVideoProvider =  By.xpath("//button[@title='Youtube']");
    private By inputVideoLink = By.xpath("//input[@placeholder='Video Link']");
    private By checkboxColors = By.xpath("//input[@name='colors_active']");
    private By dropdownColors = By.xpath("//button[@data-id = 'colors']");



}
