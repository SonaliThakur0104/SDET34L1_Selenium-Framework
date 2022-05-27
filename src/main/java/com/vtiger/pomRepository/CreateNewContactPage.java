package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {

	//Initialize the driver address to all the elements through contructors and make it as public
	
	public CreateNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="lastname")
	private WebElement createLastNameTxt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//b[contains(.,'Contact Information')]/../../following-sibling::tr[3]/td[2]/img")
	private WebElement searchOrganizationsLookupImg;
	
	

	public void createContact(String contactName) {
		createLastNameTxt.sendKeys(contactName);
		saveBtn.click();
	}
	
	public void enterContactNameAndSwitchToSearchOrganization(String contactName,WebDriver driver)
	{
		createLastNameTxt.sendKeys(contactName);
		searchOrganizationsLookupImg.click();

	}
	public void saveContactPage(WebDriver driver) {
		saveBtn.click();
	}

}
