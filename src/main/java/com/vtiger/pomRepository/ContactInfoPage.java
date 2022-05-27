package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	@FindBy(xpath="//span[@id='dtlview_Last Name']")
	private WebElement checkContactLastName;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement checkHeaderText;
	
	@FindBy(xpath="//td[text()='Organization Name']/following-sibling::td/a")
	private WebElement checkOrganizationName;
	
		
		//initialize the driver to all the elements through constructor and make it as public
				public ContactInfoPage(WebDriver driver) {
					PageFactory.initElements(driver, this);
				}
				
				
				
		public String checkContactInfo(WebDriver driver) {
			return checkContactLastName.getText();
		}	
		
		public String checkOrganizationInfo(WebDriver driver) {
			return checkOrganizationName.getText();
		}	
		
		public WebElement getContactHeaderInfo() {
			return checkHeaderText;
		}	
		

}
