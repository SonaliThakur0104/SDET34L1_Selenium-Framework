package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentInfoPage {
	@FindBy(id="dtlview_Title")
	private WebElement actualTitleTxt;

	@FindBy(xpath="//td[text()='Notes']/following-sibling::td/p")
	private WebElement actualNotesTxt;

	@FindBy(xpath="//td[text()='File Name']/following-sibling::td/a")
	private WebElement actualFileNameTxt;

	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement checkHeaderText;
	

	//initialize the driver to all the elements through constructor and make it as public
	public DocumentInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String checkDocumentTitleInfo(WebDriver driver) {
		return actualTitleTxt.getText();
	}
	public String checkDocumentNotesInfo(WebDriver driver) {
		return actualNotesTxt.getText();
	}
	public String checkDocumentFileNameInfo(WebDriver driver) {
		return actualFileNameTxt.getText();
	}
	public WebElement getDocumentHeaderInfo() {
		return checkHeaderText;
	}	
	


}


