package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverDataUtility;

//Create the class as webpage name and make it as public

public class HomePage {
	
	//declare all the elements and specify the access specifiers as private
	@FindBy(linkText="More")
	private WebElement moreDropDown;
	
	@FindBy(linkText="Campaigns")
	private WebElement campaignsTab;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorIcon;
	
	@FindBy(linkText= "Sign Out")
	private WebElement signOutLink;
	
	@FindBy(linkText="Products")
	private WebElement productsTab;
	
	@FindBy(linkText="Contacts")
	private WebElement contactsTab;
	
	@FindBy(linkText="Organizations")
	private WebElement organizationsTab;
	
	@FindBy(linkText="Documents")
	private WebElement documentsTab;
	
	@FindBy(linkText="Leads")
	private WebElement leadsTab;
	
	@FindBy(linkText="Trouble Tickets")
	private WebElement troubleTicketsTab;
	
	
	
	//Initialize the driver address to all the elements through constructors and make it as public
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//business library
	public void clickCampaign(WebDriver driver,WebDriverDataUtility webDriverDataUtility) {
		webDriverDataUtility.mouseHoverOnTheElement(moreDropDown, driver);
		campaignsTab.click();
	}
	
	public WebElement getMoreDropDown(WebDriver driver) {
		return moreDropDown;
	}
	
	public void signout(WebDriver driver,WebDriverDataUtility webDriverDataUtility) {
		webDriverDataUtility.mouseHoverOnTheElement(administratorIcon, driver);
		signOutLink.click();
	}
	
	public void clickProducts(WebDriver driver) {
		productsTab.click();
	}
	
	public void clickContacts(WebDriver driver) {
		contactsTab.click();
	}
	
	public void clickOrganization(WebDriver driver) {
		organizationsTab.click();
	}
	
	public void clickDocuments(WebDriver driver) {
		documentsTab.click();
	}
	
	public void clickLeads(WebDriver driver) {
		leadsTab.click();
	}
	
	public void clickTroubleTickets(WebDriver driver) {
		troubleTicketsTab.click();
	}
	
	
	
	
}
