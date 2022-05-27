package com.vtiger.practiceTest;

import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNgLoginPracticeTest {
	
	@Test(dataProvider="loginData")
	public void practice1Test(String username,String password) 
	{
		Reporter.log(username+ "----"+password,true);
	}
	
    @DataProvider
	public Object[][] loginData()
	{
		Object[][] data=new Object[5][2];
		
		data[0][0]="admin1";
		data[0][1]="password1";
		
		data[1][0]="admin2";
		data[1][1]="password2";
		
		data[2][0]="admin3";
		data[2][1]="password3";
		
		data[3][0]="admin4";
		data[3][1]="password4";
		
		data[4][0]="admin5";
		data[4][1]="password5";
		
		return data;
	}
}
