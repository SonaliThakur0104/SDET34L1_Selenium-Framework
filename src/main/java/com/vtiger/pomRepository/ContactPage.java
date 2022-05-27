package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	//Initialize the driver address to all the elements through contructors and make it as public
	
	public ContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@alt='Create Contact...']")
	private WebElement createContactImg;

	public void createNewContact(WebDriver driver) {
		createContactImg.click();
	}
	

}
