package com.anhtester.EcommerceCMS.User.pages;

public class BasePage {

    public String iconAnhtester = "//header//img[@alt='Active eCommerce CMS']";
    public String buttonLogin = "//a[text()='Registration']/parent::li/preceding-sibling::li[1]/a";
    public String menuDashboard = "//div[@class='d-flex align-items-start']//a[@class='aiz-side-nav-link active']";
    public String menuManageProfile = "//div[@class='d-flex align-items-start']//span[@class='aiz-side-nav-text'][normalize-space()='Manage Profile']";
    public String buttonHome = "//a[contains(text(),'Home')]";
    public String buttonLogout = "//a[text()='My Panel']/parent::li/following-sibling::li[1]/a";
}
