package com.sdet34l1.genericLibrary;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener {

	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("On Start");
		ExtentSparkReporter spark=new ExtentSparkReporter("./extentReport/extentReport.html");
		spark.config().setDocumentTitle("DocumentTitle");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Report Name");
		
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Environment","Testing Environment");
		report.setSystemInfo("Reporter Name","Sonali");
		report.setSystemInfo("Plateform","Windows 10");
		report.setSystemInfo("Unit testing tool","TESTNG");
		report.setSystemInfo("Build Management Tool","Maven");
		report.setSystemInfo("Automation Tool","Selenium");

		
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("On Test Start");
		test=report.createTest(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("On Test Success");
		test.log(Status.PASS, result.getMethod().getMethodName()+ " is passed.");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("On Test Failure");
		test.log(Status.FAIL, result.getMethod().getMethodName()+ " is failed.");
		test.log(Status.FAIL, result.getThrowable());
		

		try {
			test.addScreenCaptureFromPath(WebDriverDataUtility.takeScreenShot(result.getMethod().getMethodName(),BaseClass.staticdriver));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("On Test Skipped");
		test.log(Status.SKIP, result.getMethod().getMethodName()+ " is skipped.");
		test.log(Status.SKIP, result.getThrowable());
		}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("onTestFailedButWithinSuccessPercentage");

	}

	
	@Override
	public void onFinish(ITestContext context) {
		System.out.println("On Finish");
		report.flush();
		
	}

}
