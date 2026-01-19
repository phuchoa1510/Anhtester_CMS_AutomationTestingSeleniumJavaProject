package com.anhtester.listeners;

import com.anhtester.helpers.CaptureHelper;
import com.anhtester.helpers.SystemHelper;
import com.anhtester.reports.AllureManager;
import com.anhtester.reports.ExtentTestManager;
import com.anhtester.utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        LogUtils.info("======== ON START " + context.getName() + " ========");
        CaptureHelper.startRecord(context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LogUtils.info("======== ON FINISH " + context.getName() + " ========");
        CaptureHelper.stopRecord();
    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("===== Execution Started: " + result.getName() + " =====");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("+++++ Execution PASSED: " + result.getName() + " +++++");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.warn("***** Execution SKIPPED: " + result.getName() + " *****");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        LogUtils.error("----- Execution FAILED but within success percentage: " + result.getName() + " -----");
        CaptureHelper.takeScreenshot(result.getName() + "_" + SystemHelper.getDateTimeNowFormat());
    }
    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " is failed.");
        //Screenshot khi fail
        //CaptureHelper.captureScreenshot(result.getName());
        LogUtils.error(result.getThrowable().toString());

        //Extent Report
        ExtentTestManager.addScreenshot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");

        //Allure Report
        AllureManager.saveTextLog(result.getName() + " is failed.");
        AllureManager.saveScreenshotPNG();
    }
}
