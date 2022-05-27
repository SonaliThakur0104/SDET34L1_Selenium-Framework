package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	//Initialize the driver address to all the elements through contructors and make it as public
	
		public ProductPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath="//img[@title='Create Product...']")
		private WebElement clickProductImg;
		
		public void createProductImg(WebDriver driver)
		{
			 clickProductImg.click();
		}
		
		
		
		
		

}
