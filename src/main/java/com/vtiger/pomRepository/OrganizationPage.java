package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {


	@FindBy(xpath= "//img[@title='Create Organization...']")
	private WebElement clickOrganizationImg;

	@FindBy(xpath="//img[@src='themes/softed/images/Home.PNG']")
	private WebElement homeImg;

	//Initialize the driver address to all the elements through constructors and make it as public

	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}



	public void clickOnOrganizationImg() {
		clickOrganizationImg.click();
	}
	

	public void clickOnHomeImg() {
		homeImg.click();
	}
	


}
