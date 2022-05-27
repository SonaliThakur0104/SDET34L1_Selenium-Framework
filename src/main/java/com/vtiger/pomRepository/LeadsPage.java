package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverDataUtility;

public class LeadsPage {
	//Initialize the driver address to all the elements through constructors and make it as public
	
		public LeadsPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath= "//img[@alt='Create Lead...']")
		private WebElement clickLeadsImg;
		
		@FindBy(xpath= "//a[text()='Lead No']/../../following-sibling::tr[3]/td[1]//input[@name='selected_id']")
		private WebElement clickCheckBox1;
		
		@FindBy(xpath= "//a[text()='Lead No']/../../following-sibling::tr[4]/td[1]//input[@name='selected_id']")
		private WebElement clickCheckBox2;
		
		@FindBy(xpath= "//a[text()='Lead No']/../../following-sibling::tr[3]/td[4]//a[@title='Leads']")
		private WebElement clickCheckBox3;
		

		@FindBy(xpath= "//input[@value='Delete']")
		private WebElement deleteBtn;
		
		@FindBy(linkText="Go to Advanced Search")
		private WebElement searchLink;
		
		@FindBy(id="fcol0")
		private WebElement firstDropdown;
		
		@FindBy(xpath="//td[@align='center']//input[@class='crmbutton create small']")
		private WebElement searchBtn;
		
		
		public void clickCreateLeadsImg() {
			 clickLeadsImg.click();
			
		}
		
		public void clickCheckBoxAndDelete() {
			clickCheckBox1.click();
			clickCheckBox2.click();
			deleteBtn.click();
			
		}
		
		public void clickCheckBoxofName() {
			clickCheckBox3.click();
		}
		
		public void clickCheckBoxAndDeleteDuplicate() {
			clickCheckBox2.click();
			deleteBtn.click();
			
		}
		public void clickCheckBoxAndDeleteOriginal() {
			clickCheckBox1.click();
			deleteBtn.click();
		}
		
		public void clickAdvancedSearch() {
			searchLink.click();
		}
		
		
		
		public void salutationDropDown(String visibleText) {
			WebDriverDataUtility.listDropDown(visibleText, firstDropdown);
		}
		
		public void clickSearchButton() {
			searchBtn.click();
		}
}


