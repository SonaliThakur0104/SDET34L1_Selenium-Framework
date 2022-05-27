package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverDataUtility;

public class SearchOrganizationPage {
		
			@FindBy(name="search_text")
			private WebElement searchTxt;
			
			@FindBy(name="search")
			private WebElement searchBtn;
			
			//Initialize the driver address to all the elements through constructors and make it as public
			
			public SearchOrganizationPage(WebDriver driver) {
				PageFactory.initElements(driver, this);
			}
		
			
			
			public void selectOrganization(WebDriver driver,String organizationNameCreated) {
				WebDriverDataUtility.switchToWindowBasedOnTitle(driver,"Organization");
				searchTxt.sendKeys(organizationNameCreated);
				searchBtn.click();
				driver.findElement(By.xpath("//a[.='"+organizationNameCreated+"']")).click();
				
				
				}


}
		//
		//        	
		//        driver.findElement(By.name("search_text")).sendKeys(organizationName);
		//        driver.findElement(By.name("search")).click();
		//        driver.findElement(By.linkText(organizationName)).click();
		//        
		//   