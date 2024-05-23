package com.solvd.listeners;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
        takeScreenshot(driver, result.getMethod().getMethodName());
    }

    private void takeScreenshot(WebDriver driver, String methodName) {
        if (driver == null) {
            logger.error("Driver is null, cannot take screenshot");
            return;
        }

        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destFile = "./screenshots/" + methodName + "_" + timestamp + ".png";
        try {
            File destDir = new File("./screenshots/");
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            FileUtils.copyFile(srcFile, new File(destFile));
            logger.info("Screenshot taken: " + destFile);
        } catch (IOException e) {
            logger.error("Failed to take screenshot: ", e);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {}

    @Override
    public void onTestSuccess(ITestResult result) {}

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}
}