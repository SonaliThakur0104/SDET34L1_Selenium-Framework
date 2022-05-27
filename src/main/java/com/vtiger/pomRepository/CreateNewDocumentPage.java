package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericLibrary.WebDriverDataUtility;

public class CreateNewDocumentPage {

	//Initialize the driver address to all the elements through contructors and make it as public

	public CreateNewDocumentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="notes_title")
	private WebElement createDocumentTitleTxt;

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	@FindBy(xpath="//iframe[@title='Rich text editor, notecontent, press ALT 0 for help.']")
	private WebElement iframe;

	@FindBy(xpath="//body[@class='cke_show_borders']")
	private WebElement documentDescriptionTxt;

	@FindBy(xpath="//a[@id='cke_5']")
	private WebElement boldIcon;

	@FindBy(xpath="//a[@id='cke_6']")
	private WebElement inclinedIcon;

	@FindBy(id="filename_I__")
	private WebElement documentPathBtn;

	@FindBy(xpath="//b[text()='File Information']/../../following-sibling::tr[4]//input[@title='Save [Alt+S]']")
	private WebElement save_Btn;


	public void createDocument(String documentTitle) {
		createDocumentTitleTxt.sendKeys(documentTitle);
	}

	public void documentDescription(WebDriver driver,String documentDescription) {
		WebDriverDataUtility.switchToFrame(driver, iframe);
		documentDescriptionTxt.sendKeys(documentDescription,Keys.CONTROL+"a");

	}

	public void clickOnBoldAndIclined() {
		boldIcon.click();
		inclinedIcon.click();
	}

	public void createDocumentPath(String documentPath) {

		documentPathBtn.sendKeys(documentPath);
		save_Btn.click();
	}


}



//driver.findElement(By.xpath("//a[@id='cke_5']")).click();
//driver.findElement(By.xpath("//a[@id='cke_6']")).click();
//
////document path
//driver.findElement(By.id("filename_I__")).sendKeys(documentPath);
//
////save
//driver.findElement(By.xpath("//b[text()='File Information']/../../following-sibling::tr[4]//input[@title='Save [Alt+S]']")).click();
//
