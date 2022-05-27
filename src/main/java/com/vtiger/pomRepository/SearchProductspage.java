package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverDataUtility;

public class SearchProductspage {
	//Initialize the driver address to all the elements through contructors and make it as public
	
		public SearchProductspage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(name="search_text")
		private WebElement searchTxt;
		
		@FindBy(name="search")
		private WebElement searchBtn;
		
		
		
		
		public void selectProduct(WebDriver driver,String productName) {
			WebDriverDataUtility.switchToWindowBasedOnTitle(driver,"Products");
			searchTxt.sendKeys(productName);
			searchBtn.click();
			driver.findElement(By.xpath("//a[.='"+productName+"']")).click();
			
			
			}

}

