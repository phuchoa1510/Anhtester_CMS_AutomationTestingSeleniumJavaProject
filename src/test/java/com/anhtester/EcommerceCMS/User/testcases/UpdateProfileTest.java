package com.anhtester.EcommerceCMS.User.testcases;

import com.anhtester.EcommerceCMS.User.pages.DashboardPage;
import com.anhtester.EcommerceCMS.User.pages.LoginPage;
import com.anhtester.EcommerceCMS.User.pages.ProfilePage;
import com.anhtester.common.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Ecommerce CMS")
@Feature("Manange user profile feature")
@Story("Update user profile with data")
public class UpdateProfileTest extends BaseTest {

    private ProfilePage profilePage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    @Test
    @Description("Update user profile")
    @Severity(SeverityLevel.CRITICAL)
    public void testUpdateProfile() {
        loginPage = new LoginPage();
        loginPage.loginCMS();
        dashboardPage = new DashboardPage();
        dashboardPage.clickMenuManageProfile();
        profilePage = new ProfilePage();
        profilePage.verifyManageProfilePageIsDisplayed();
        profilePage.fillDataBasicInfo("Customer Example", "123456789", "123456", "123456");
        profilePage.verifyAlertUpdateProfileSuccessDisplayed();
    }

}
