package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsInfoPage {
	@FindBy(xpath="//span[text()='[ LEA59 ] njj bghyu -  Lead Information']/../../../../following-sibling::table//input[@title='Delete [Alt+D]']'")
	private WebElement deleteBtn;
	
	@FindBy(xpath="//td[@class='small']/table/tbody/tr/td[4]/a[text()='Leads']")
	private WebElement leadsLink; 

	@FindBy(xpath="//input[@name='Duplicate']")
	private WebElement duplicateBtn;

	@FindBy(name="firstname")
	private WebElement firstNameText;
	
	@FindBy(name="lastname")
	private WebElement lastNameText;
	
	@FindBy(name="company")
	private WebElement companyNameText;
	
	@FindBy(xpath="//b[text()='Description Information']/../../following-sibling::tr[2]//input[1]")
	private WebElement saveBtn;

	@FindBy(xpath="//input[@title='Delete [Alt+D]']")
	private WebElement delBtn;


	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement checkHeaderText;
	


	//initialize the driver to all the elements through constructor and make it as public
	public LeadsInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	public void deleteSavedNames() {
		delBtn.click();
	} 

	public void clickOnDeleteButton(WebDriver driver) {
		
		 deleteBtn.click();
	}
	
	public void clickOnLeadsLink(WebDriver driver) {
		leadsLink.click();
	}
	
	public void clickOnDuplicateButtonandsave(WebDriver driver) {
		duplicateBtn.click();
		saveBtn.click();
		leadsLink.click();

	}
	public void clickOnDuplicateButton(WebDriver driver) {
		duplicateBtn.click();
	}
	
	public void editDuplicatename(String leadFirstName1,String leadLastName1,String leadCompanyName1) {
		firstNameText.clear();
		firstNameText.sendKeys(leadFirstName1);
		lastNameText.clear();
		lastNameText.sendKeys(leadLastName1);
		companyNameText.clear();
		companyNameText.sendKeys(leadCompanyName1);
		saveBtn.click();

	}
	public WebElement getLeadHeaderInfo() {
		return checkHeaderText;
	}	
	

	
	
	

}
