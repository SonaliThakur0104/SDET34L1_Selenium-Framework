package com.vtiger.practiceTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.IconstantPathUtility;

public class VtigerTestNgLoginPracticeTest {

	@Test(dataProvider="loginData")
	public void practice1Test(String username,String password) 
	{
		Reporter.log(username+ "----"+password,true);
	}
	
	
	@DataProvider
	public Object[][] loginData() throws EncryptedDocumentException, IOException
	{
		ExcelFileData.openExcel(IconstantPathUtility.EXCELFILEDATA);
		return ExcelFileData.getMultipleDataFromExcel("Logindata");
		
	}
}
