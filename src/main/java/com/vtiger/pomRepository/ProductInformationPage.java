package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInformationPage {
	
	@FindBy(xpath="//span[@id='dtlview_Product Name']")
	private WebElement actProductNameText;

	@FindBy(xpath="//span[@class='lvtHeaderText']")
	private WebElement checkHeaderText;
	

	//initialize the driver to all the elements through constructor and make it as public
	public ProductInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String checkProductNameInfo() {
		return actProductNameText.getText();
	}
	
	public WebElement getProductHeaderInfo() {
		return checkHeaderText;
	}	
	


}
