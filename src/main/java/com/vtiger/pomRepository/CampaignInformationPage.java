
package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInformationPage {
   
	public CampaignInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@id='dtlview_Campaign Name']")
	private WebElement campaignInfoTxt;
	
	@FindBy(id="dtlview_Product")
	private WebElement productInfoTxt;
	

	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement checkHeaderText;
	
	public String checkCampaigninfo() {
		return campaignInfoTxt.getText();
	}
	
	public String checkProductInfo() {
		return productInfoTxt.getText();
		}
	
	public WebElement getCamapaignHeaderInfo() {
		return checkHeaderText;
	}	
	

	
	

}
