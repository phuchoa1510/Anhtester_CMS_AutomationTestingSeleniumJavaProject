package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends BasePage {

    private BasePage basePage;

    private By homePageButtonLogin = By.xpath("//a[text()='Login']");
    private By labelLoginToYourAccount = By.xpath("//h1[normalize-space()='Login to your account.']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By checkboxRememberMe = By.xpath("//span[@class='aiz-square-check']");
    private By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot password?']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By linkRegisterNow = By.xpath("//a[normalize-space()='Register Now']");

    private By errorMessageInvalid = By.xpath("//div[@role='alert']");


    public void verifyLoginPageDisplayed() {
        boolean check = WebUI.checkElementExist(labelLoginToYourAccount);
        Assert.assertTrue(check, "Login page is not displayed.");
    }

    @Step("Navigate to Login page")
    public void navigateToLoginPage() {
        navigateHomePage();
        WebUI.clickElement(homePageButtonLogin);
    }

    private void enterEmail(String email) {
        WebUI.setText(inputEmail, email);
    }

    private void enterPassword(String passwrord) {
        WebUI.setText(inputPassword, passwrord);
    }

    private void clickLoginButton() {
        WebUI.clickElement(buttonLogin);
    }

    @Step("Login CMS with email: {0}")
    public void loginCMS(String email, String password) {
        navigateToLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        WebUI.waitForPageLoaded();
    }

    @Step("Login CMS with default account")
    public void loginCMSDefault() {
        loginCMS(
                PropertiesHelper.getValue("USER_EMAIL"),
                PropertiesHelper.getValue("USER_PASSWORD")
        );
    }

    @Step("Verify login successful and Dashboard displayed")
    public void verifyLoginSuccess() {
        boolean check = WebUI.checkElementExist(By.xpath("//div[@class='d-flex align-items-start']//a[@class='aiz-side-nav-link active']"), 5, 1000);
        Assert.assertTrue(check, "Login failed or Dashboard not displayed");
        WebUI.waitForPageLoaded();
    }

    @Step("Verify login failed and error message displayed")
    public void verifyLoginFailed() {
        WebUI.waitForPageLoaded();
        boolean check = WebUI.checkElementExist(errorMessageInvalid, 5, 1000);
        Assert.assertTrue(check, "Error message for invalid not displayed.");
    }

}
