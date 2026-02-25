package com.anhtester.EcommerceCMS.User.testcases;

import com.anhtester.EcommerceCMS.User.pages.BasePage;
import com.anhtester.EcommerceCMS.User.pages.LoginPage;
import com.anhtester.EcommerceCMS.User.pages.ProfilePage;
import com.anhtester.common.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Feature("Manange user profile feature")
@Story("Update user profile with data")
public class ProfileTest extends BaseTest {

    private ProfilePage profilePage;
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
        profilePage = new ProfilePage();
        profilePage.verifyManageProfilePageIsDisplayed();
        profilePage.fillDataBasicInfo("Customer Example","123456789","123456","123456");
        profilePage.verifyAlertUpdateProfileSuccessDisplayed();
    }

}
