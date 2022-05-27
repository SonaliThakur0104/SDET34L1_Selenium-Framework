package com.vtiger.contactsTest;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.ContactInfoPage;
import com.vtiger.pomRepository.ContactPage;
import com.vtiger.pomRepository.CreateNewContactPage;

public class CreateContactsTest extends BaseClass {
	String contactName;
	ContactPage contactPage;
	CreateNewContactPage createNewContactPage;
	ContactInfoPage contactInfoPage;

	@Test(groups="sanity")
	public void createContactsTest() {
		contactName=ExcelFileData.getDataFromExcel("Contacts",2,1)+randomNumber;
		contactPage=new ContactPage(driver);
		createNewContactPage= new CreateNewContactPage(driver);
		contactInfoPage=new ContactInfoPage(driver);

		homePage.clickContacts(driver);
		contactPage.createNewContact(driver);
		createNewContactPage.createContact(contactName);
		 WebDriverDataUtility.waitUntilElementVisible(contactInfoPage.getContactHeaderInfo());
		javaSpecificData.assertionThroughIfCondition(contactInfoPage.checkContactInfo(driver),contactName,"contact");	

	}
}