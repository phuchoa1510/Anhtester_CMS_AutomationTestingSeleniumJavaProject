package com.anhtester.EcommerceCMS.User.testcases;

import com.anhtester.EcommerceCMS.User.pages.BasePage;
import com.anhtester.EcommerceCMS.User.pages.LoginPage;
import com.anhtester.common.BaseTest;
import com.anhtester.helpers.PropertiesHelper;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

@Slf4j
public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @Test(priority = 1)
    public void testLoginSuccess() {
        loginPage = new LoginPage();
        loginPage.loginCMS(PropertiesHelper.getValue("USER_EMAIL"), PropertiesHelper.getValue("USER_PASSWORD"));
        loginPage.verifyLoginSuccess();
    }

    @Test(priority = 2)
    public void testLoginFailed() {
        loginPage = new LoginPage();
        loginPage.loginCMS(PropertiesHelper.getValue("USER_EMAIL"), "123");
        loginPage.verifyLoginFailed();
    }


}
