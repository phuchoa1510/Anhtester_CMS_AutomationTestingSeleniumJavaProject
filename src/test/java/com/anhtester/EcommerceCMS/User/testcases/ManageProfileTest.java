package com.anhtester.EcommerceCMS.User.testcases;

import com.anhtester.EcommerceCMS.User.pages.BasePage;
import com.anhtester.EcommerceCMS.User.pages.LoginPage;
import com.anhtester.EcommerceCMS.User.pages.ManageProfilePage;
import com.anhtester.common.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Feature("Manange user profile feature")
@Story("Update user profile with data")
public class ManageProfileTest extends BaseTest {

    private ManageProfilePage manageProfilePage;
    private LoginPage loginPage;
    private BasePage basePage;


    @Test
    @Description("Update user profile")
    @Link( name = "https://drive.google.com/drive/u/0/home")
    @Severity(SeverityLevel.CRITICAL)
    public void testUpdateProfile () {
        loginPage = new LoginPage();
        loginPage.loginCMS();
        basePage = new BasePage();
        basePage.clickMenuManageProfile();
        manageProfilePage = new ManageProfilePage();
        manageProfilePage.verifyManageProfilePageIsDisplayed();
        manageProfilePage.fillDataBasicInfo("Customer Example","123456789","123456","123456");
        manageProfilePage.verifyAlertUpdateProfileSuccessDisplayed();
    }

}
