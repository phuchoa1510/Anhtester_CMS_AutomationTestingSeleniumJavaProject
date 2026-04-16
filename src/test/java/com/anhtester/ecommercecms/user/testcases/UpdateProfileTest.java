package com.anhtester.ecommercecms.user.testcases;

import com.anhtester.common.BaseTest;
import com.anhtester.dataprovider.DataProviderManager;
import com.anhtester.ecommercecms.user.pages.LoginPage;
import com.anhtester.ecommercecms.user.pages.ProfilePage;
import com.anhtester.ecommercecms.user.pages.UserDashboardPage;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.reports.AllureManager;
import com.anhtester.utils.LogUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Hashtable;

@Epic("Regression Test CMS")
@Feature("Profile Management")
public class UpdateProfileTest extends BaseTest {

    LoginPage loginPage;
    UserDashboardPage userDashboardPage;
    ProfilePage profilePage;

    @Test(priority = 1, dataProvider = "getProfileData", dataProviderClass = DataProviderManager.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that user can update basic profile information with multiple data sets")
    public void testUpdateProfile(Hashtable<String, String> data) {
        String scenario = data.get("ScenarioName") == null ? "" : String.valueOf(data.get("ScenarioName"));
        String name = data.get("Name") == null ? "" : String.valueOf(data.get("Name"));
        String phone = data.get("Phone") == null ? "" : String.valueOf(data.get("Phone"));
        String newPassword = data.get("NewPassword") == null ? "" : String.valueOf(data.get("NewPassword"));
        String confirmPassword = data.get("ConfirmPassword") == null ? "" : String.valueOf(data.get("ConfirmPassword"));
        String expectedResult = data.get("ExpectedResult") == null ? "" : String.valueOf(data.get("ExpectedResult")).toLowerCase();
        String expectedMessage = data.get("ExpectedMessage") == null ? "" : String.valueOf(data.get("ExpectedMessage"));

        LogUtils.info(">>> BẮT ĐẦU TEST CASE: " + scenario);
        AllureManager.saveTextLog("Scenario: " + scenario + " | Expected: " + expectedResult);

        loginPage = new LoginPage();


        loginPage.loginCMS(
                PropertiesHelper.getValue("USER_EMAIL"),
                PropertiesHelper.getValue("USER_PASSWORD")
        );
        userDashboardPage = new UserDashboardPage();


        profilePage = userDashboardPage.clickMenuManageProfile();
        Assert.assertTrue(profilePage.isManageProfilePageDisplayed(), "LỖI: Trang Manage Profile không hiển thị!");


        profilePage.updateBasicInfo(name, phone, newPassword, confirmPassword);


        if (expectedResult.equals("success")) {
            Assert.assertTrue(profilePage.isUpdateSuccess(), "LỖI [" + scenario + "]: Cập nhật thất bại nhưng mong đợi thành công!");
            Assert.assertEquals(profilePage.getAlertMessageText(), expectedMessage, "LỖI: Message thành công không khớp!");
            LogUtils.info("KẾT QUẢ: " + scenario + " ==> [PASS]");
        }
        else if (expectedResult.equals("failed")) {


            boolean isBlocked = profilePage.isUpdateSuccess();
            LogUtils.info("KẾT QUẢ: " + scenario + " ==> [CHECKED] - Message nhận được: " + profilePage.getAlertMessageText());

        }
    }
}
