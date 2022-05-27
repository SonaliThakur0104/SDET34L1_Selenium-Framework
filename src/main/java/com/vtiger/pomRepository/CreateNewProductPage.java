package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewProductPage {
	
	//initialize the driver to all the elements through constructor and make it as public
	public CreateNewProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="productname")
	private WebElement productNameTxt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	

	
	public void createProduct(String productName) {
		productNameTxt.sendKeys(productName);
		saveBtn.click();
      }
	
}
