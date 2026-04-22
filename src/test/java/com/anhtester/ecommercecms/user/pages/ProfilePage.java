package com.anhtester.ecommercecms.user.pages;

import com.anhtester.helpers.FileHelper;
import com.anhtester.helpers.PropertiesHelper;
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

    private final By buttonBrowse =  By.xpath("//div[@class= 'input-group']/div[normalize-space()='Browse']");
    private final By buttonSelectFile = By.xpath("//button[normalize-space()='Select File']");
    private final By buttonUploadNew = By.xpath("//a[normalize-space()='Upload New']");
    private final By buttonBrowseImage = By.xpath("//button[normalize-space()='Browse']");
    private final String imageUploadedXpath = "//div[normalize-space()='%s']";
    private final By completeSymbol = By.xpath("//div[@class='uppy-StatusBar-statusPrimary']");
    private final String selectedImageXpath = "//div[@title='%s']";
    private final By buttonAddFiles = By.xpath("//button[normalize-space()='Add Files']");
    private final String imageAddedSuccessXpath = "//div[@title='%s']";

    private final By buttonAddNewAddress = By.xpath("//div[normalize-space()='Add New Address']");
    private final By inputAddress = By.xpath("//textarea[@placeholder='Your Address']");
    private final By inputCountry = By.xpath("//button[@title='Select your country']");
    private final By inputCountrySearch = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private final String optionCountryXpath = "//span[@class='text'][normalize-space()='%s']";
    private final By inputState = By.xpath("//button[@title='Select State']");
    private final By inputStateSearch = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private final String optionStateXpath = "//span[@class='text'][normalize-space()='%s']";
    private final By inputCity = By.xpath("//button[@title='Select City']");
    private final By  inputCitySearch = By.xpath("//div[@class='dropdown-menu show']//input[@aria-label='Search']");
    private final String optionCityXpath = "//span[@class='text'][normalize-space()='%s']";
    private final By inputPostalCode = By.xpath("//input[@placeholder='Your Postal Code']");
    private final By inputPhone = By.xpath("//input[@placeholder='+880']");
    private final By buttonSave = By.xpath("//button[normalize-space()='Save']");

    private final By alertMessage = By.xpath("//span[@data-notify='message']");


    @Step("Is Manage Profile page displayed")
    public boolean isManageProfilePageDisplayed() {
        WebUI.waitForPageLoaded();
        return WebUI.checkElementExist(headerManageProfile);
    }

    @Step("Update basic profile information: Name={0}, Phone={1}")
    public ProfilePage updateBasicInfo(String name, String yourphone,String image, String newPassword, String confirmPassword) {
        WebUI.clearElement(inputYourName);
        WebUI.setText(inputYourName, name);
        WebUI.clearElement(inputYourPhone);
        WebUI.setText(inputYourPhone, yourphone);
        uploadAvatar(image);
        WebUI.clearElement(inputNewPassword);
        WebUI.setText(inputNewPassword, newPassword);
        WebUI.clearElement(inputConfirmPassword);
        WebUI.setText(inputConfirmPassword, confirmPassword);
        WebUI.clickElement(buttonUpdateProfile);
        WebUI.waitForPageLoaded();
        return this;
    }

    @Step("Upload Avatar")
    public ProfilePage uploadAvatar(String imageName) {
        WebUI.clickElement(buttonBrowse);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(buttonUploadNew);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(buttonUploadNew);
        WebUI.waitForPageLoaded();
        WebUI.clickElement(buttonBrowseImage);
        WebUI.sleep(2);
        String imageFilePath = PropertiesHelper.getValue("IMAGE_FILE_PATH") + imageName  ;
        FileHelper.uploadFileWithRobot(imageFilePath);
        WebUI.waitForPageLoaded();
        By imageUploaded = By.xpath(String.format(imageUploadedXpath, imageName));
        WebUI.checkElementExist(imageUploaded);
        WebUI.checkElementExist(completeSymbol);
        WebUI.clickElement(buttonSelectFile);
        By selectedImage = By.xpath(String.format(selectedImageXpath, imageName));
        WebUI.clickElement(selectedImage);
        WebUI.clickElement(buttonAddFiles);
        By imageAddedSuccess = By.xpath(String.format(imageUploadedXpath, imageName));
        WebUI.checkElementExist(imageAddedSuccess);
        return this;
    }

    @Step("Add new address: {0}, {1}, {2}, {3}, {4}, {5}")
    public ProfilePage addNewAddress(String address, String country,String state,String city, String postalCode, String phone) {
        WebUI.clickElement(buttonAddNewAddress);
        WebUI.setText(inputAddress, address);
        WebUI.clickElement(inputCountry);
        WebUI.setText(inputCountrySearch, country);
        By optionCountry = By.xpath(String.format(optionCountryXpath,country));
        WebUI.clickElement(optionCountry);
        WebUI.clickElement(inputState);
        WebUI.setText(inputStateSearch,state);
        By optionState = By.xpath(String.format(optionStateXpath,state));
        WebUI.clickElement(optionState);
        WebUI.clickElement(inputCity);
        WebUI.setText(inputCitySearch, city);
        By optionCity = By.xpath(String.format(optionCityXpath,city));
        WebUI.clickElement(optionCity);
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
