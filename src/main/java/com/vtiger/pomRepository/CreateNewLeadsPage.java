package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewLeadsPage {

	//initialize the driver to all the elements through constructor and make it as public
	public CreateNewLeadsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="firstname")
	private WebElement leadFirstNameTxt;
	
	@FindBy(name="lastname")
	private WebElement leadLastNameTxt;
	
	@FindBy(name="company")
	private WebElement leadCompanyNameTxt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	

	
	public void createLead(String productName,String leadLastName,String leadCompanyName) {
		leadFirstNameTxt.sendKeys(productName);
		leadLastNameTxt.sendKeys(leadLastName);
		leadCompanyNameTxt.sendKeys(leadCompanyName);

		saveBtn.click();
      }
	
	}


