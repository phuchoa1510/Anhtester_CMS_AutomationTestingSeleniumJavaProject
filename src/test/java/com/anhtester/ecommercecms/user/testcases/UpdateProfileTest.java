package com.anhtester.ecommercecms.user.testcases;


import com.anhtester.ecommercecms.user.pages.LoginPage;
import com.anhtester.ecommercecms.user.pages.ProfilePage;
import com.anhtester.ecommercecms.user.pages.UserDashboardPage;
import com.anhtester.common.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

@Epic("Ecommerce CMS")
@Feature("Manange user profile feature")
@Story("Update user profile with data")
public class UpdateProfileTest extends BaseTest {

    private ProfilePage profilePage;
    private LoginPage loginPage;
    private UserDashboardPage userDashboardPage;

    @Test
    @Description("Update user profile")
    @Severity(SeverityLevel.CRITICAL)
    public void testUpdateProfile() {
        loginPage = new LoginPage();
        loginPage.loginCMS();
        userDashboardPage = new UserDashboardPage();
        userDashboardPage.clickMenuManageProfile();
        profilePage = new ProfilePage();
        profilePage.verifyManageProfilePageIsDisplayed();
        profilePage.fillDataBasicInfo("Customer Example", "123456789", "123456", "123456");
        profilePage.verifyAlertUpdateProfileSuccessDisplayed();
    }
}
