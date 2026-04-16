package com.anhtester.ecommercecms.user.pages;

import com.anhtester.keywords.WebUI;
import com.anhtester.utils.LogUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private By inputField(String id) {
        return By.xpath("//input[@id='" + id + "']");
    }
    private By buttonWithText(String text) {
        return By.xpath("//button[normalize-space()='" + text + "']");
    }

    private final By homePageButtonLogin = By.xpath("//a[text()='Login']");
    private final By labelLoginToYourAccount = By.xpath("//h1[normalize-space()='Login to your account.']");
    private final By errorMessageInvalid = By.xpath("//div[@role='alert']");
    private final By userDashboardMenu = By.xpath("//h1[normalize-space()='Dashboard']");

    @Step("Navigate to Login page")
    public LoginPage navigateToLoginPage() {
        navigateHomePage();
        WebUI.clickElement(homePageButtonLogin);
        return this;
    }

    @Step("Login CMS with email: {0} and password: {1}")
    public void loginCMS(String email, String password) {
        navigateToLoginPage();
        WebUI.setText(inputField("email"), email);
        WebUI.setText(inputField("password"), password);
        WebUI.clickElement(buttonWithText("Login"));
        WebUI.waitForPageLoaded();
    }
    @Step("Check Login Success (Auto-detect Dashboard)")
    public boolean isLoginSuccess() {
        boolean isUser = WebUI.checkElementExist(userDashboardMenu, 5, 1000);

        if (isUser) LogUtils.info(">> Nhận diện: Đã đăng nhập vào USER DASHBOARD.");
        
        return isUser;
    }

    @Step("Check Login Failed (Error message displayed)")
    public boolean isLoginFailed() {
        return WebUI.checkElementExist(errorMessageInvalid, 5, 1000);
    }

    @Step("Get error message text")
    public String getErrorMessage() {
        return WebUI.getElementText(errorMessageInvalid);
    }
}
