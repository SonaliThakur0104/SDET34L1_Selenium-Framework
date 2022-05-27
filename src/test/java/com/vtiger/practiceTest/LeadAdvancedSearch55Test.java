package com.vtiger.practiceTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.LeadsPage;

public class LeadAdvancedSearch55Test extends BaseClass {
	LeadsPage leadsPage;
	WebDriverDataUtility webDriverDataUtility=new WebDriverDataUtility(null);


	@Test
	public void leadSearchWithTest() 
	{
		webDriverDataUtility.initializeActions(driver);
		homePage.clickLeads(driver);
        leadsPage=new LeadsPage(driver);
		leadsPage.clickAdvancedSearch();
		leadsPage.salutationDropDown("Lead Source");
		leadsPage.clickSearchButton();
		javaSpecificData.printStatement("TC passed");
	}
	
	

}
