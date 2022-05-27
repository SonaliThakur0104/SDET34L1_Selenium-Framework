package com.vtiger.contactsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.ContactInfoPage;
import com.vtiger.pomRepository.ContactPage;
import com.vtiger.pomRepository.CreateNewContactPage;
import com.vtiger.pomRepository.CreateNewOrganizationPage;
import com.vtiger.pomRepository.OrganizationInfoPage;
import com.vtiger.pomRepository.OrganizationPage;
import com.vtiger.pomRepository.SearchOrganizationPage;

public class CreateContactsWithOrganizationTest extends BaseClass{
	String  contactName;
	String organizationName;
	String organizationNameCreated;
	OrganizationPage organizationPage;
	CreateNewOrganizationPage createNewOrganizationPage;
	OrganizationInfoPage organizationInfoPage;
	ContactPage contactPage;
	CreateNewContactPage createNewContactPage;
	ContactInfoPage contactInfoPage;
	SearchOrganizationPage searchOrganizationPage;

	

	@Test(groups="regression")
	public void createContWithOrgTest() {
		
		contactName=ExcelFileData.getDataFromExcel("Contacts",5,2)+randomNumber;
		organizationName=ExcelFileData.getDataFromExcel("Contacts",5,1)+randomNumber;
		organizationNameCreated=ExcelFileData.getDataFromExcel("Contacts",5,1);

		organizationPage=new OrganizationPage(driver);
		createNewOrganizationPage=new CreateNewOrganizationPage(driver);
		organizationInfoPage=new OrganizationInfoPage(driver);
		contactPage=new ContactPage(driver);
		createNewContactPage= new CreateNewContactPage(driver);
		contactInfoPage=new ContactInfoPage(driver);
		searchOrganizationPage=new SearchOrganizationPage(driver);
		webDriverDataUtility.initializeActions(driver);
		homePage.clickOrganization(driver);
		organizationPage.clickOnOrganizationImg();
		createNewOrganizationPage.createNeworganizationName(driver, organizationName);
		WebDriverDataUtility.explicitlyWait(driver,longTimeout);
		WebDriverDataUtility.waitUntilElementClickable(organizationInfoPage.getOrganizationname());
		homePage.clickContacts(driver);
		contactPage.createNewContact(driver);
		createNewContactPage.enterContactNameAndSwitchToSearchOrganization(contactName, driver);

		searchOrganizationPage.selectOrganization(driver, organizationNameCreated);
		WebDriverDataUtility.switchToWindowBasedOnTitle(driver,"Contacts");
		createNewContactPage.saveContactPage(driver);
		
		WebDriverDataUtility.waitUntilElementVisible(contactInfoPage.getContactHeaderInfo());
		javaSpecificData.assertionThroughIfCondition(contactInfoPage.checkContactInfo(driver),contactName,"contact with organization");
		javaSpecificData.assertionThroughIfCondition(contactInfoPage.checkOrganizationInfo(driver),organizationNameCreated,"");
	

	}
}
