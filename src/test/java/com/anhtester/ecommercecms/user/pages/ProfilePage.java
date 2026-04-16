package com.anhtester.ecommercecms.user.pages;

import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class ProfilePage extends BasePage {


    private final By headerManageProfile = By.xpath("//h1[normalize-space()='Manage Profile']");
    private final By inputYourName = By.xpath("//input[@name='name']");
    private final By inputYourPhone = By.xpath("//input[@name='phone']");
    private final By inputNewPassword = By.xpath("//input[@name='new_password']");
    private final By inputConfirmPassword = By.xpath("//input[@name='confirm_password']");
    private final By buttonUpdateProfile = By.xpath("//button[normalize-space()='Update Profile']");

    private final By buttonAddNewAddress = By.xpath("//div[normalize-space()='Add New Address']");
    private final By inputAddress = By.xpath("//textarea[@name='address']");
    private final By dropdownCity = By.xpath("//label[normalize-space()='City']/following-sibling::div//button");
    private final String optionCountry = "//a[@class='dropdown-item']/span[normalize-space()='%s']";
    private final By inputPostalCode = By.xpath("//input[@name='postal_code']");
    private final By inputPhone = By.xpath("//input[@name='phone' and @placeholder='+880']");
    private final By buttonSave = By.xpath("//button[normalize-space()='Save']");

    private final By alertMessage = By.xpath("//span[@data-notify='message']");


    @Step("Is Manage Profile page displayed")
    public boolean isManageProfilePageDisplayed() {
        WebUI.waitForPageLoaded();
        return WebUI.checkElementExist(headerManageProfile);
    }

    @Step("Update basic profile information: Name={0}, Phone={1}")
    public ProfilePage updateBasicInfo(String yourName, String yourPhone, String newPassword, String confirmPassword) {
        WebUI.setText(inputYourName, yourName);
        WebUI.setText(inputYourPhone, yourPhone);
        WebUI.setText(inputNewPassword, newPassword);
        WebUI.setText(inputConfirmPassword, confirmPassword);
        WebUI.clickElement(buttonUpdateProfile);
        WebUI.waitForPageLoaded();
        return this;
    }

    @Step("Add new address: {0}, {1}, {2}, {3}")
    public ProfilePage addNewAddress(String address, String country, String postalCode, String phone) {
        WebUI.clickElement(buttonAddNewAddress);
        WebUI.setText(inputAddress, address);
        WebUI.clickElement(dropdownCity);
        WebUI.clickElement(By.xpath(String.format(optionCountry, country)));
        WebUI.setText(inputPostalCode, postalCode);
        WebUI.setText(inputPhone, phone);
        WebUI.clickElement(buttonSave);
        WebUI.waitForPageLoaded();
        return this;
    }

    @Step("Check if update success message is displayed")
    public boolean isUpdateSuccess() {
        return WebUI.checkElementExist(alertMessage, 5, 1000);
    }

    @Step("Get alert message text")
    public String getAlertMessageText() {
        return WebUI.getElementText(alertMessage);
    }

}
