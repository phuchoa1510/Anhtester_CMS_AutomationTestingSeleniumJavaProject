package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProfilePage extends BasePage {

    public By headerManageProfile = By.xpath("//h1[normalize-space()='Manage Profile']");
    public By headerBasicInfo = By.xpath("//h1[normalize-space()='Manage Profile']");
    public By labelYourName = By.xpath("//label[normalize-space()='Your Name']");
    public By labelYourPhone = By.xpath("//label[normalize-space()='Your Phone']");
    public By labelPhoto = By.xpath("//label[normalize-space()='Photo]");
    public By labelYourPassword = By.xpath("//label[normalize-space()='Your Password']");
    public By labelConfirmPassword = By.xpath("//label[normalize-space()='Confirm Password']");
    public By headerAddress = By.xpath("//h5[normalize-space()='Address']");
    public By headerChangeYourEmail = By.xpath("//h5[normalize-space()='Change your email']");
    public By labelYourEmail = By.xpath("//label[normalize-space()='Your Email']");
    public By inputYourName = By.xpath("//input[@placeholder='Your name']");
    public By inputYourPhone = By.xpath("//input[@placeholder='Your Phone']");
    public By buttonBrownsePhoto = By.xpath("//div[@class='input-group-text bg-soft-secondary font-weight-medium' and normalize-space()='Browse']");
    public By inputNewPassword = By.xpath("//input[@placeholder='New Password']");
    public By inputConfirmPassword = By.xpath("//input[@placeholder='Confirm Password']");
    public By buttonUpdateProfile = By.xpath("//button[normalize-space()='Update Profile']");
    public By buttonAddNewAddress = By.xpath("//i[@class='la la-plus la-2x']/following-sibling::div[normalize-space() = 'Add New Address']");
    public By inputYourEmail = By.xpath("//input[@placeholder='Your Email']");
    public By buttonVerify = By.xpath("//button[@class='btn btn-outline-secondary new-email-verification']");
    public By buttonUpdateEmail = By.xpath("//button[normalize-space()='Update Email']");

    //Add New Address
    public By lableAddress = By.xpath("//label[normalize-space()='Address']");
    public By labelCountry = By.xpath("//label[normalize-space()='Country']");
    public By labelState = By.xpath("//label[normalize-space()='State']");
    public By labelCity = By.xpath("//label[normalize-space()='City']");
    public By labelPostalCode = By.xpath("//label[normalize-space()='Postal code']");
    public By labelPhone = By.xpath("//label[normalize-space()='Postal code']");
    public By inputAddress = By.xpath("//textarea[@placeholder='Your Address']");
    public By dropdownCountry = By.xpath("//textarea[@placeholder='Your Address']");
    public By inputdropdownCountrySearch = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    public By optionCountry = By.xpath("//a[@class = 'dropdown-item']/span[normalize-space() = '");
    public By dropdownCity = By.xpath("//label[normalize-space()='City']//ancestor::div/following-sibling::div/div/button");
    public By inputPostalCode = By.xpath("//input[@placeholder='Your Postal Code']");
    public By inputPhone = By.xpath("//input[@placeholder='+880']");
    public By buttonSave = By.xpath("//button[normalize-space()='Save']");

    public By massageUpdateProfileSuccess = By.xpath("//span[@data-notify='message']");

    @Step("Verify Mananage Profile page is Displayed")
    public void verifyManageProfilePageIsDisplayed() {
        boolean check = WebUI.checkElementExist(headerManageProfile);
        Assert.assertTrue(check, "Manage Profile Page is not displayed");
    }

    @Step("Update basic profile information with name: {0}, phone: {1}, and password")
    public void fillDataBasicInfo(String yourName, String yourPhone, String newPassword, String confirmPassword) {
        WebUI.setText(inputYourName, yourName);
        WebUI.setText(inputYourPhone, yourPhone);
        WebUI.setText(inputNewPassword, newPassword);
        WebUI.setText(inputConfirmPassword, confirmPassword);
        WebUI.clickElement(buttonUpdateProfile);
    }

    @Step("Add new address in country: {1}")
    public void addNewAddress(String address, String country, String postalCode, String phone) {
        WebUI.clickElement(buttonAddNewAddress);
        WebUI.setText(inputAddress, address);
        WebUI.clickElement(dropdownCity);
        WebUI.clickElement(By.xpath(optionCountry + country + "']"));
        WebUI.setText(inputPostalCode, postalCode);
        WebUI.setText(inputPhone, phone);
        WebUI.clickElement(buttonSave);
    }

    @Step("Change email to: {0}")
    public void changeEmail(String email) {
        WebUI.setText(inputYourEmail, email);
        WebUI.clickElement(buttonVerify);
        WebUI.clickElement(buttonUpdateEmail);
    }

    @Step("Verify Alert Update Profile Success Displayed")
    public void verifyAlertUpdateProfileSuccessDisplayed() {
        boolean isDisplayed = WebUI.checkElementExist(massageUpdateProfileSuccess, 5, 1000);
        Assert.assertTrue(isDisplayed, "Alert message is not displayed");
        String alertText = WebUI.getElementText(massageUpdateProfileSuccess);
        Assert.assertEquals(alertText, "Your Profile has been updated successfully!", "Alert message is not match expected");
    }

}
