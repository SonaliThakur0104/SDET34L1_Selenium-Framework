package com.vtiger.practiceTest;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestngPractice1Test extends TestngBasicConfigAnnotationsTest {
	@Test
	public void practice1Test() {
		Reporter.log("TestngPractice1Test---test1",true);
	}
	
	@Test
	public void practice2Test() {
		Reporter.log("TestngPractice1Test---test2",true);
		
	}
	
	@Test
	public void practice3Test() {
		Reporter.log("TestngPractice1Test---test3",true);
		
	}

}

