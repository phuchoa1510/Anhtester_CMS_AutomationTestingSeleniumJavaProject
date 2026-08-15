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

        String action = data.get("Action") == null ? "" : String.valueOf(data.get("Action"));

        if(!"Run".equals(action)) {
            return;
        }
        //Basic Info
        String scenario = data.get("ScenarioName") == null ? "" : String.valueOf(data.get("ScenarioName"));
        String name = data.get("Name") == null ? "" : String.valueOf(data.get("Name"));
        String yourphone = data.get("YourPhone") == null ? "" : String.valueOf(data.get("YourPhone"));
        String imageName = data.get("ImageName") == null ? "" : String.valueOf(data.get("ImageName"));
        String newPassword = data.get("NewPassword") == null ? "" : String.valueOf(data.get("NewPassword"));
        String confirmPassword = data.get("ConfirmPassword") == null ? "" : String.valueOf(data.get("ConfirmPassword"));

        //Address
        String address = data.get("Address") == null ? "" : String.valueOf(data.get("Address"));
        String country = data.get("Country") == null ? "" : String.valueOf(data.get("Country"));
        String state = data.get("State") == null ? "" : String.valueOf(data.get("State"));
        String city = data.get("City") == null ? "" : String.valueOf(data.get("City"));
        String postalCode = data.get("PostalCode") == null ? "" : String.valueOf(data.get("PostalCode"));
        String phone = data.get("Phone") == null ? "" : String.valueOf(data.get("Phone"));

        //Change your email
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

        if ("UpdateBasicInfo".equals(scenario)) {
            profilePage.updateBasicInfo(name, yourphone, imageName, newPassword, confirmPassword);
        }
        else if ("UpdateAddress".equals(scenario)) {
            profilePage.addNewAddress(address, country, state, city, postalCode, phone);
        }
        else if ("UpdateEmail".equals(scenario)) {
            profilePage.addNewAddress(address, country, state, city, postalCode, phone);
        }
        else if ("UpdateInfo".equals(scenario)) {
            profilePage.updateBasicInfo(name, yourphone, imageName, newPassword, confirmPassword);
            profilePage.addNewAddress(address, country, state, city, postalCode, phone);
        }

        Assert.assertTrue(profilePage.isUpdateSuccess(), "LỖI [" + scenario + "]: Cập nhật thất bại !");
        LogUtils.info("KẾT QUẢ: " + scenario + " ==> [PASS]");
    }
}
