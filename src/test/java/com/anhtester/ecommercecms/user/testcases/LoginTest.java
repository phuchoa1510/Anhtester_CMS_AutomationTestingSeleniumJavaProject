package com.anhtester.ecommercecms.user.testcases;

import com.anhtester.common.BaseTest;
import com.anhtester.ecommercecms.user.pages.LoginPage;
import com.anhtester.dataprovider.DataProviderManager;
import com.anhtester.reports.AllureManager;
import com.anhtester.utils.LogUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Hashtable;

@Epic("Regression Test CMS")
@Feature("Login functionality")
public class LoginTest extends BaseTest {

    LoginPage LoginPage;

    @Test(priority = 1, dataProvider = "getLoginData", dataProviderClass = DataProviderManager.class)
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify login with multiple accounts from Excel using Hashtable")
    public void testLoginMultipleAccounts(Hashtable<String, String> data) {
        String scenario = data.get("ScenarioName");
        String email = data.get("Email");
        String password = data.get("Password");
        String expectedResult = data.get("ExpectedResult") != null ? data.get("ExpectedResult").toLowerCase() : "";

        LogUtils.info(">>> BẮT ĐẦU TEST CASE: " + scenario);
        AllureManager.saveTextLog("Scenario: " + scenario + " | Expected: " + expectedResult);

        LoginPage = new LoginPage();
        LoginPage.loginCMS(email, password);

        if (expectedResult.equals("success")) {
            boolean isSuccess = LoginPage.isLoginSuccess();
            Assert.assertTrue(isSuccess, "LỖI [" + scenario + "]: Mong đợi đăng nhập THÀNH CÔNG nhưng không vào được Dashboard!");
            LogUtils.info("KẾT QUẢ: " + scenario + " ==> [PASS]");
        }
        else if (expectedResult.equals("failed")) {
            if (LoginPage.isLoginFailed()) {
                String actualError = LoginPage.getErrorMessage();
                LogUtils.info("KẾT QUẢ: " + scenario + " ==> [PASS] (Hệ thống chặn đúng và hiển thị lỗi: " + actualError + ")");
            }
            else {
                boolean isLoginNotSuccess = !LoginPage.isLoginSuccess();
                Assert.assertTrue(isLoginNotSuccess,
                    "LỖI [" + scenario + "]: Mong đợi BỊ CHẶN nhưng không thấy thông báo lỗi VÀ vẫn vào được Dashboard!");
                LogUtils.info("KẾT QUẢ: " + scenario + " ==> [PASS] (Hệ thống đã chặn và không vào được Dashboard)");
            }
        }

        else {
            Assert.fail("LỖI: Cột 'ExpectedResult' trong Excel phải là 'success' hoặc 'failed'. Giá trị hiện tại: " + expectedResult);
        }
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify login success with valid email and password from Properties")
    public void testLoginSuccessFromProperties() {
        LoginPage = new LoginPage();
        SoftAssert softAssert = new SoftAssert();

        LoginPage.loginCMS(
                com.anhtester.helpers.PropertiesHelper.getValue("USER_EMAIL"),
                com.anhtester.helpers.PropertiesHelper.getValue("USER_PASSWORD")
        );

        softAssert.assertTrue(LoginPage.isLoginSuccess(), "Error: Login success menu not found!");
        softAssert.assertAll();
    }
}
