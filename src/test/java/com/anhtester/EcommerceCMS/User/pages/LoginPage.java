package com.anhtester.EcommerceCMS.User.pages;

import com.anhtester.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage {

    public By labelLoginToYourAccount = By.xpath("//h1[normalize-space()='Login to your account.']");
    public By inputEmail = By.xpath("//input[@id='email']");
    public By inputPassword = By.xpath("//input[@id='password']");
    public By checkboxRememberMe = By.xpath("//span[@class='aiz-square-check']");
    public By linkForgotPassword = By.xpath("//a[normalize-space()='Forgot password?']");
    public By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    public By linkRegisterNow = By.xpath("//a[normalize-space()='Register Now']");

    public void verifyLoginPageDisplayed(){
        boolean check = WebUI.checkElementExist(labelLoginToYourAccount);
        Assert.assertTrue(check, "Login page is not displayed.");
    }

    public void verifyLoginSuccess(){

    }

    public void navigateToLoginUserPage(){
        WebUI.openURL();
        WebUI.waitForPageLoaded();
    }

    private void enterEmail(String email){
        WebUI.setText(inputEmail,email);
    }

    private void enterPassword(String passwrord){
        WebUI.setText(inputPassword,passwrord);
    }

    private void clickLoginButton(){
        WebUI.clickElement(buttonLogin);
    }

    public void loginUser(String email, String password){
        navigateToLoginUserPage();
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        WebUI.waitForPageLoaded();
    }

}
