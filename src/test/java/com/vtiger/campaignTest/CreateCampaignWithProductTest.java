package com.vtiger.campaignTest;


import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.CampaignInformationPage;
import com.vtiger.pomRepository.CampaignPage;
import com.vtiger.pomRepository.CreateNewCampaignPage;
import com.vtiger.pomRepository.CreateNewProductPage;
import com.vtiger.pomRepository.ProductPage;
import com.vtiger.pomRepository.SearchProductspage;

public class CreateCampaignWithProductTest extends BaseClass {
	
	String productName;
	String campaignName;
	CampaignPage campaignPage;
	CreateNewCampaignPage createNewCampaignPage;
	CampaignInformationPage campaignInformationPage;
	ProductPage productPage;
	CreateNewProductPage createNewProductPage;
	SearchProductspage searchProductspage;
	

	@Test(groups="regression")
	public void createCamWithProdTest() {
		campaignName=ExcelFileData.getDataFromExcel("Campaign",5,1)+randomNumber;
		productName=ExcelFileData.getDataFromExcel("Campaign",5,2);
		
		campaignPage=new CampaignPage(driver);
		createNewCampaignPage=new CreateNewCampaignPage(driver);
		campaignInformationPage=new CampaignInformationPage(driver);
		productPage=new ProductPage(driver);
		createNewProductPage=new CreateNewProductPage(driver);
		searchProductspage=new SearchProductspage(driver);
		
		homePage.clickProducts(driver);
		productPage.createProductImg(driver);
		createNewProductPage.createProduct(productName);
		homePage.clickProducts(driver);
		WebDriverDataUtility.waitUntilElementClickable(homePage.getMoreDropDown(driver));
		homePage.clickCampaign(driver,webDriverDataUtility);
		campaignPage.getCreateCampaignImg().click();
		createNewCampaignPage.enterCampaignNameAndSwitchToSearchProduct(campaignName, driver);
		searchProductspage.selectProduct(driver, productName);
		WebDriverDataUtility.switchToWindowBasedOnTitle(driver,"Campaigns");
		createNewCampaignPage.saveCampaignPage(driver);
		WebDriverDataUtility.waitUntilElementVisible(campaignInformationPage.getCamapaignHeaderInfo());
        javaSpecificData.assertionThroughIfCondition(campaignInformationPage.checkCampaigninfo(),campaignName,"campaign with product");
		javaSpecificData.assertionThroughIfCondition(campaignInformationPage.checkProductInfo(),productName,"");
		
	}

}
