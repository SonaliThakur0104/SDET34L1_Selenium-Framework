package com.vtiger.organizationTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.CreateNewOrganizationPage;
import com.vtiger.pomRepository.OrganizationInfoPage;
import com.vtiger.pomRepository.OrganizationPage;

public class CreateOrganizationTest extends BaseClass {

	String organizationName;
	OrganizationPage organizationPage;
	CreateNewOrganizationPage createNewOrganizationPage;
	OrganizationInfoPage organizationInfoPage;

	@Test(groups="baseclass")
	public void createOrganizationTest() {
		organizationName=ExcelFileData.getDataFromExcel("Organization",2,1)+randomNumber;

		organizationPage=new OrganizationPage(driver);
		createNewOrganizationPage=new CreateNewOrganizationPage(driver);
		organizationInfoPage=new OrganizationInfoPage(driver);
		webDriverDataUtility.initializeActions(driver);

		homePage.clickOrganization(driver);
		organizationPage.clickOnOrganizationImg();
		createNewOrganizationPage.createNeworganizationNameandSave(driver, organizationName);
		 WebDriverDataUtility.waitUntilElementVisible(organizationInfoPage.getOrganizationHeaderInfo());

		javaSpecificData.assertionThroughIfCondition(organizationInfoPage.checkOrganizationname(),organizationName,"organization");
		
	}
}
