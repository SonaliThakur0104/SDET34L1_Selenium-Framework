package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverDataUtility;

public class CreateNewOrganizationPage {
	//Initialize the driver address to all the elements through contructors and make it as public
	
		public CreateNewOrganizationPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(name="accountname")
		private WebElement newOrganizationName;
		
		@FindBy(name="button")
		private WebElement saveBtn;
		
		@FindBy(xpath="//select[@name='industry']")
		private WebElement industryDrpdwn;
		
		@FindBy(xpath="//select[@name='accounttype']")
		private WebElement pressDrpdwn;
		
		
		public void createNeworganizationNameandSave(WebDriver driver,String organizationName) {
			newOrganizationName.sendKeys(organizationName);
			saveBtn.click();
		
		}

		public void createNeworganizationName(WebDriver driver,String organizationName) {
			newOrganizationName.sendKeys(organizationName);
			saveBtn.click();
		
		
		}
		public void createNewOrganizationNameDropDown(String organizationName) {
			newOrganizationName.sendKeys(organizationName);
		
		
		
		}
		
		public void industryDropdown(String value1,String value2) {
			WebDriverDataUtility.listDropDown(industryDrpdwn, value1);
			WebDriverDataUtility.listDropDown(pressDrpdwn, value2);
			}
		
		public void saveNewOrganizationName() {
			saveBtn.click();
		}
		
		

}







