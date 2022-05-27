package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage {
	
	//initialize the driver to all the elements through constructor and make it as public
	public CreateNewCampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(name="campaignname")
	private WebElement campNameTxt;
	
	@FindBy(xpath="//b[text()='Description Information']/../../following-sibling::tr[2]//div/input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//td[contains(.,'Campaign') and @class='dvtCellLabel']/following-sibling::td/img")
	private WebElement searchProductsLookupImg;
	
	
	public void createCampaign(String campaignName) {
		campNameTxt.sendKeys(campaignName);
		saveBtn.click();
		
	}
	
	public void enterCampaignNameAndSwitchToSearchProduct(String campaignName,WebDriver driver)
	{
		campNameTxt.sendKeys(campaignName);
		searchProductsLookupImg.click();

	}
	public void saveCampaignPage(WebDriver driver) {
		saveBtn.click();
	}
	
	
	
	

}
