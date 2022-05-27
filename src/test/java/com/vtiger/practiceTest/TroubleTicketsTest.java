package com.vtiger.practiceTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.TroubleTicketsPage;

public class TroubleTicketsTest extends BaseClass{
	HomePage homePage;
	TroubleTicketsPage troubleTicketsPage;
	
	@Test
	public void troubleTicketsTest() {
		
		homePage=new HomePage(driver);
		homePage.clickTroubleTickets(driver);
		troubleTicketsPage=new TroubleTicketsPage(driver);
		
	}
	

}
