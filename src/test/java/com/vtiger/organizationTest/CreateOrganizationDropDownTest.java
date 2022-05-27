package com.vtiger.organizationTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.CreateNewOrganizationPage;
import com.vtiger.pomRepository.OrganizationInfoPage;
import com.vtiger.pomRepository.OrganizationPage;

public class CreateOrganizationDropDownTest extends BaseClass {
	String organizationName;
	String industryName;
	String typeName;
	OrganizationPage organizationPage;
	CreateNewOrganizationPage createNewOrganizationPage;
	OrganizationInfoPage organizationInfoPage;

	
	
	@Test(groups="sanity")
	public void createOrganizationTest() {
		organizationName=ExcelFileData.getDataFromExcel("Organization",2,1)+randomNumber;
		industryName=ExcelFileData.getDataFromExcel("Organization",2,2);
		typeName=ExcelFileData.getDataFromExcel("Organization",2,3);

		organizationPage=new OrganizationPage(driver);
		createNewOrganizationPage=new CreateNewOrganizationPage(driver);
	    organizationInfoPage=new OrganizationInfoPage(driver);
		webDriverDataUtility.initializeActions(driver);

		homePage.clickOrganization(driver);
		organizationPage.clickOnOrganizationImg();
		createNewOrganizationPage.createNewOrganizationNameDropDown(organizationName);
		createNewOrganizationPage.industryDropdown("Education","Press");
		createNewOrganizationPage.saveNewOrganizationName();
		 WebDriverDataUtility.waitUntilElementVisible(organizationInfoPage.getOrganizationHeaderInfo());

		javaSpecificData.assertionThroughIfCondition(organizationInfoPage.checkOrganizationname(),organizationName,"organization with dropdown");
		javaSpecificData.assertionThroughIfCondition(organizationInfoPage.checkIndustryName(),industryName,"");
		javaSpecificData.assertionThroughIfCondition(organizationInfoPage.checkPressName(),typeName,"");
		
	}

}
