package com.vtiger.practiceTest;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class TestngBasicConfigAnnotationsTest {
	
	@BeforeSuite
	public void beforeSuite1Test() {
		Reporter.log("Before Suite 1",true);
		}
	
	@AfterSuite
	public void afterSuite1Test() {
		Reporter.log("After Suite 1",true);
		}
	
	@BeforeClass
	public void beforeClass1Test() {
		Reporter.log("Before Class 1",true);
		}

	@AfterClass
	public void afterClass1Test() {
		Reporter.log("After Class 1",true);
		}

	@BeforeTest
	public void beforeTest1Test() {
		Reporter.log("Before Test 1",true);
		}

	@AfterTest
	public void afterTest1Test() {
		Reporter.log("After test 1",true);
		}

	@BeforeMethod
	public void beforeMethod1Test() {
		Reporter.log("Before Method 1",true);
		}

	@BeforeMethod
	public void beforeMethod2Test() {
		Reporter.log("Before Method 2",true);
		}

	@AfterMethod
	public void afterMethod2Test() {
		Reporter.log("After Method 1",true);
		}


}
