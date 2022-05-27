package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentsPage {

	@FindBy(xpath= "//img[@alt='Create Document...']")
	private WebElement createDocumentImg;

	//Initialize the driver address to all the elements through contructors and make it as public

	public DocumentsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	
	public void clickOnDocumentImg() {
		createDocumentImg.click();

	}

}
