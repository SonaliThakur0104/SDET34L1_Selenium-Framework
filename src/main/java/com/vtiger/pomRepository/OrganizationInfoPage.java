package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	@FindBy(xpath="//span[@id='dtlview_Organization Name']")
	private WebElement checkOrgNameTxt;

	@FindBy(xpath="//td[text()='Industry']/following-sibling::td/span[@id='dtlview_Industry']/font")
	private WebElement checkIndustryTxt;

	@FindBy(xpath="//td[text()='Type']/following-sibling::td/span[@id='dtlview_Type']/font")
	private WebElement checkPressTxt;

	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement checkHeaderText;
	


	//Initialize the driver address to all the elements through constructors and make it as public

	public OrganizationInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public String checkOrganizationname()
	{
		return checkOrgNameTxt.getText();
	}
	

	public WebElement getOrganizationname()
	{
		return checkOrgNameTxt;
	}

	public String checkIndustryName()
	{
		return checkIndustryTxt.getText();
	}
	
	public String checkPressName()
	{
		return checkPressTxt.getText();
	}
	public WebElement getOrganizationHeaderInfo() {
		return checkHeaderText;
	}	
	

	
}
