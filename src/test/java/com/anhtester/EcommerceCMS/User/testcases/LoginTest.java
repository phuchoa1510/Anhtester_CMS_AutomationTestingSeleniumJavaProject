package com.anhtester.EcommerceCMS.User.testcases;

import com.anhtester.EcommerceCMS.User.pages.BasePage;
import com.anhtester.EcommerceCMS.User.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.PropertiesHelper;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Ecommerce CMS")
@Feature("Login user feature")
@Story("Login with valid and invalid credentials")

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Description("Verify user can login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void testLoginSuccess() {
        loginPage = new LoginPage();
        loginPage.loginCMS();
        loginPage.verifyLoginSuccess();
    }

    @Description("Verify user can login with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2)
    public void testLoginFailed() {
        loginPage = new LoginPage();
        loginPage.loginCMS(PropertiesHelper.getValue("USER_EMAIL"), "123");
        loginPage.verifyLoginFailed();
    }

}
