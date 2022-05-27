package com.vtiger.campaignTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.CampaignInformationPage;
import com.vtiger.pomRepository.CampaignPage;
import com.vtiger.pomRepository.CreateNewCampaignPage;

public class CreateCampaignTest extends BaseClass {
	String campaignName;
	CampaignPage campaignPage;
	CreateNewCampaignPage createNewCampaignPage;
	CampaignInformationPage campaignInformationPage;


	@Test(groups="sanity")
	public void createCampaignTest() {

		campaignName=ExcelFileData.getDataFromExcel("Campaign",2,1)+randomNumber;
		campaignPage=new CampaignPage(driver);
		createNewCampaignPage=new CreateNewCampaignPage(driver);
		campaignInformationPage=new CampaignInformationPage(driver);
		homePage.clickCampaign(driver,webDriverDataUtility);
		campaignPage.getCreateCampaignImg().click();
		createNewCampaignPage.createCampaign(campaignName);  
		WebDriverDataUtility.waitUntilElementVisible(campaignInformationPage.getCamapaignHeaderInfo());
		javaSpecificData.assertionThroughIfCondition(campaignInformationPage.checkCampaigninfo(),campaignName,"campaign");
	}
}


