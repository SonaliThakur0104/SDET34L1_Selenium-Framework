package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {

	//Initialize the driver address to all the elements through contructors and make it as public
	
	public CampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(xpath= "//img[@alt='Create Campaign...']")
	private WebElement createCampaignImg;
	
	
	
	public WebElement getCreateCampaignImg() {
		return createCampaignImg;
		
	}
}
