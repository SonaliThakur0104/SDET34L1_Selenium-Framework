package com.vtiger.leadTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.CreateNewLeadsPage;
import com.vtiger.pomRepository.LeadsInfoPage;
import com.vtiger.pomRepository.LeadsPage;

public class CreateMultipleLeadsAndDeleteFirstTest extends BaseClass {
	String leadFirstName;
	String leadLastName;
	String leadCompanyName;
	String leadFirstName1;
	String leadLastName1;
	String leadCompanyName1;
	LeadsPage leadsPage;
	CreateNewLeadsPage createNewLeadsPage;
	LeadsInfoPage leadsInfoPage;


	@Test(groups="baseclass")
	public void createMultipleLeadsTest() {
		leadFirstName=ExcelFileData.getDataFromExcel("Lead",1,1);
		leadLastName=ExcelFileData.getDataFromExcel("Lead",1,2)+randomNumber;
		leadCompanyName=ExcelFileData.getDataFromExcel("Lead",1,3)+randomNumber;
		leadFirstName1=ExcelFileData.getDataFromExcel("Lead",3,1);
		leadLastName1=ExcelFileData.getDataFromExcel("Lead",3,2)+randomNumber;
		leadCompanyName1=ExcelFileData.getDataFromExcel("Lead",3,3)+randomNumber;
		leadsPage=new LeadsPage(driver);
		createNewLeadsPage=new CreateNewLeadsPage(driver);
		leadsInfoPage=new LeadsInfoPage(driver);
		homePage.clickLeads(driver);
		leadsPage.clickCreateLeadsImg();
		createNewLeadsPage.createLead(leadFirstName, leadLastName, leadCompanyName);
		leadsInfoPage.clickOnLeadsLink(driver);
		leadsPage.clickCreateLeadsImg();
		createNewLeadsPage.createLead(leadFirstName1, leadLastName1, leadCompanyName1);
		leadsInfoPage.clickOnLeadsLink(driver);
		leadsPage.clickCheckBoxAndDeleteOriginal();;
		WebDriverDataUtility.alertHandling(driver);
		javaSpecificData.printStatement("Tc Passed");
	}



}


