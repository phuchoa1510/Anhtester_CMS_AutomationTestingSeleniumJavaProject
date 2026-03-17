package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProfilePage extends BasePage {

    private By headerManageProfile = By.xpath("//h1[normalize-space()='Manage Profile']");
    private By headerBasicInfo = By.xpath("//h1[normalize-space()='Manage Profile']");
    private By labelYourName = By.xpath("//label[normalize-space()='Your Name']");
    private By labelYourPhone = By.xpath("//label[normalize-space()='Your Phone']");
    private By labelPhoto = By.xpath("//label[normalize-space()='Photo]");
    private By labelYourPassword = By.xpath("//label[normalize-space()='Your Password']");
    private By labelConfirmPassword = By.xpath("//label[normalize-space()='Confirm Password']");
    private By headerAddress = By.xpath("//h5[normalize-space()='Address']");
    private By headerChangeYourEmail = By.xpath("//h5[normalize-space()='Change your email']");
    private By labelYourEmail = By.xpath("//label[normalize-space()='Your Email']");
    private By inputYourName = By.xpath("//input[@placeholder='Your name']");
    private By inputYourPhone = By.xpath("//input[@placeholder='Your Phone']");
    private By buttonBrownsePhoto = By.xpath("//div[@class='input-group-text bg-soft-secondary font-weight-medium' and normalize-space()='Browse']");
    private By inputNewPassword = By.xpath("//input[@placeholder='New Password']");
    private By inputConfirmPassword = By.xpath("//input[@placeholder='Confirm Password']");
    private By buttonUpdateProfile = By.xpath("//button[normalize-space()='Update Profile']");
    private By buttonAddNewAddress = By.xpath("//i[@class='la la-plus la-2x']/following-sibling::div[normalize-space() = 'Add New Address']");
    private By inputYourEmail = By.xpath("//input[@placeholder='Your Email']");
    private By buttonVerify = By.xpath("//button[@class='btn btn-outline-secondary new-email-verification']");
    private By buttonUpdateEmail = By.xpath("//button[normalize-space()='Update Email']");

    //Add New Address
    private By lableAddress = By.xpath("//label[normalize-space()='Address']");
    private By labelCountry = By.xpath("//label[normalize-space()='Country']");
    private By labelState = By.xpath("//label[normalize-space()='State']");
    private By labelCity = By.xpath("//label[normalize-space()='City']");
    private By labelPostalCode = By.xpath("//label[normalize-space()='Postal code']");
    private By labelPhone = By.xpath("//label[normalize-space()='Postal code']");
    private By inputAddress = By.xpath("//textarea[@placeholder='Your Address']");
    private By dropdownCountry = By.xpath("//textarea[@placeholder='Your Address']");
    private By inputdropdownCountrySearch = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private By optionCountry = By.xpath("//a[@class = 'dropdown-item']/span[normalize-space() = '");
    private By dropdownCity = By.xpath("//label[normalize-space()='City']//ancestor::div/following-sibling::div/div/button");
    private By inputPostalCode = By.xpath("//input[@placeholder='Your Postal Code']");
    private By inputPhone = By.xpath("//input[@placeholder='+880']");
    private By buttonSave = By.xpath("//button[normalize-space()='Save']");

    private By massageUpdateProfileSuccess = By.xpath("//span[@data-notify='message']");

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
