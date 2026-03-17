package com.anhtester.ecommercecms.user.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends BasePage {

    private final By homePageButtonLogin = By.xpath("//a[text()='Login']");
    private final By labelLoginToYourAccount = By.xpath("//h1[normalize-space()='Login to your account.']");
    private final By inputEmail = By.xpath("//input[@id='email']");
    private final By inputPassword = By.xpath("//input[@id='password']");
    private final By checkboxRememberMe = By.xpath("//span[@class='aiz-square-check']");
    private final By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot password?']");
    private final By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private final By linkRegisterNow = By.xpath("//a[normalize-space()='Register Now']");
    private final By errorMessageInvalid = By.xpath("//div[@role='alert']");
    private final By menuActiveDashboard = By.xpath("//div[@class='d-flex align-items-start']//a[@class='aiz-side-nav-link active']");

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

    private void enterPassword(String password) {
        WebUI.setText(inputPassword, password);
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
    public UserDashboardPage loginCMS() {
        loginCMS(
                PropertiesHelper.getValue("USER_EMAIL"),
                PropertiesHelper.getValue("USER_PASSWORD")
        );
        return new UserDashboardPage();
    }

    @Step("Verify login successful and Dashboard displayed")
    public void verifyLoginSuccess() {
        boolean check = WebUI.checkElementExist(menuActiveDashboard, 5, 1000);
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
