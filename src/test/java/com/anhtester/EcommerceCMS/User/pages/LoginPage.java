package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage extends BasePage {

    private BasePage basePage;

    public By homePageButtonLogin = By.xpath("//a[text()='Login']");
    public By labelLoginToYourAccount = By.xpath("//h1[normalize-space()='Login to your account.']");
    public By inputEmail = By.xpath("//input[@id='email']");
    public By inputPassword = By.xpath("//input[@id='password']");
    public By checkboxRememberMe = By.xpath("//span[@class='aiz-square-check']");
    public By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot password?']");
    public By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    public By linkRegisterNow = By.xpath("//a[normalize-space()='Register Now']");

    public By errorMessageInvalid = By.xpath("//div[@role='alert']");

    public void verifyLoginPageDisplayed() {
        boolean check = WebUI.checkElementExist(labelLoginToYourAccount);
        Assert.assertTrue(check, "Login page is not displayed.");
    }

    public void navigateToLoginUserPage() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        basePage = new BasePage();
        basePage.clickClosePopup();
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

    public void loginCMS(String email, String password) {
        navigateToLoginUserPage();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        WebUI.waitForPageLoaded();
    }

    public DashboardPage loginCMS() {
        navigateToLoginUserPage();
        enterEmail(PropertiesHelper.getValue("USER_EMAIL"));
        enterPassword(PropertiesHelper.getValue("USER_PASSWORD"));
        clickLoginButton();
        WebUI.waitForPageLoaded();

        return new DashboardPage();
    }

    public void verifyLoginSuccess() {
        boolean check = WebUI.checkElementExist(By.xpath("//div[@class='d-flex align-items-start']//a[@class='aiz-side-nav-link active']"), 5, 1000);
        Assert.assertTrue(check, "Login failed or Dashboard not displayed");
    }

    public void verifyLoginFailed() {
        WebUI.waitForPageLoaded();
        boolean check = WebUI.checkElementExist(errorMessageInvalid, 5, 1000);
        Assert.assertTrue(check, "Error message for invalid not displayed.");
    }

}
