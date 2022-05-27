package com.vtiger.leadTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.CreateNewLeadsPage;
import com.vtiger.pomRepository.LeadsInfoPage;
import com.vtiger.pomRepository.LeadsPage;

public class CreateDuplicateLeadTest extends BaseClass {
	String leadFirstName;
	String leadLastName;
	String leadCompanyName;
	String leadFirstName1;
	String leadLastName1;
	String leadCompanyName1;
	LeadsPage leadsPage;
	CreateNewLeadsPage createNewLeadsPage;
	LeadsInfoPage leadsInfoPage;



	@Test(groups="sanity")
	public void createDuplicateLeadTest(){
		leadFirstName=ExcelFileData.getDataFromExcel("Lead",1,1);
		leadLastName=ExcelFileData.getDataFromExcel("Lead",1,2)+randomNumber;
		leadCompanyName=ExcelFileData.getDataFromExcel("Lead",1,3)+randomNumber;
		leadsPage=new LeadsPage(driver);
		createNewLeadsPage=new CreateNewLeadsPage(driver);
		leadsInfoPage=new LeadsInfoPage(driver);
		webDriverDataUtility.initializeActions(driver);
		homePage.clickLeads(driver);
		leadsPage.clickCreateLeadsImg();
		createNewLeadsPage.createLead(leadFirstName, leadLastName, leadCompanyName);
		leadsInfoPage.clickOnLeadsLink(driver);
		leadsPage.clickCheckBoxofName();
		leadsInfoPage.clickOnDuplicateButton(driver);
		javaSpecificData.printStatement("Tc Passed");


	}

}
