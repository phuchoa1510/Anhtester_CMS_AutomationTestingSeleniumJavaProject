package com.anhtester.EcommerceCMS.User.testcases;

import com.anhtester.EcommerceCMS.User.pages.BasePage;
import com.anhtester.EcommerceCMS.User.pages.LoginPage;
import com.anhtester.EcommerceCMS.User.pages.ManageProfilePage;
import com.anhtester.common.BaseTest;
import org.testng.annotations.Test;


public class ManageProfileTest extends BaseTest {

    private ManageProfilePage manageProfilePage;
    private LoginPage loginPage;
    private BasePage basePage;

    @Test
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
