package com.anhtester.ecommercecms.admin.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;

public class AdminDashboardPage {

    private By menuDashboard = By.xpath("//span[normalize-space()='Dashboard']");
    private By menuProducts = By.xpath("//span[normalize-space()='Products']");
    private By buttonAddNewProduct = By.xpath("//span[normalize-space()='Add New Product']");


    public AdminDashboardPage clickMenuDashboard(){
        WebUI.clickElement(menuDashboard);
        return this;
    }

    public AdminDashboardPage clickMenuProduct(){
        WebUI.clickElement(buttonAddNewProduct);
        return this;
    }
    public AddNewProductPage clickAddNewProduct(){
        WebUI.clickElement(buttonAddNewProduct);
        return new AddNewProductPage();
    }

}
