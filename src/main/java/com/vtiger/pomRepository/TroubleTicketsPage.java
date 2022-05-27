package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TroubleTicketsPage {
	
	/**
	 * This method is used to initialize the driver class to all the elements through constructors and make it as public
	 * @param driver
	 */
	public TroubleTicketsPage(WebDriver driver) {
		PageFactory.initElements(driver, null);
		}
	
	@FindBy(xpath="//img[@alt='Create Ticket...']")
	private WebElement troubleTicketImg;
	
	public void clickTroubleTicketLookupImg() {
		troubleTicketImg.click();
	}

}
