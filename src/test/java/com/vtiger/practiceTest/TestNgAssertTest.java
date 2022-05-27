package com.vtiger.practiceTest;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNgAssertTest extends TestngBasicConfigAnnotationsTest {
	SoftAssert softAssert=new SoftAssert();
	@Test
	public void testNGPractice1Test() {
		Reporter.log("a-practice",true);
		Reporter.log("b-practice",true);
		Reporter.log("c-practice",true);
		Assert.fail("Expected Error");
		softAssert.assertEquals("abc", "xyz");
		Reporter.log("d-practice",true);
		softAssert.assertEquals("mno", "pqr");
		Reporter.log("e-practice",true);

	}
	@Test
	public void testNGPractice2Test() {
		Reporter.log("f-practice",true);
		Reporter.log("g-practice",true);
		Reporter.log("h-practice",true);
		softAssert.assertAll();
		Reporter.log("i-practice",true);
		Reporter.log("j-practice",true);

	}
	@Test
	public void testNGPractice3Test() {
		Reporter.log("k-practice",true);
		Reporter.log("l-practice",true);
		Reporter.log("m-practice",true);
		Reporter.log("n-practice",true);
		Reporter.log("o-practice",true);

	}

}
