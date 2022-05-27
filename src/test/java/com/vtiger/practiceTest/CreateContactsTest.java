package com.vtiger.practiceTest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.IconstantPathUtility;
import com.sdet34l1.genericLibrary.JavaSpecificData;
import com.sdet34l1.genericLibrary.PropertyFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.ContactInfoPage;
import com.vtiger.pomRepository.ContactPage;
import com.vtiger.pomRepository.CreateNewContactPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactsTest {
	public static void main(String[] args) throws InterruptedException, IOException {
		JavaSpecificData jsdata=new JavaSpecificData();
		PropertyFileData.fetchDataFromPropertyFile(IconstantPathUtility.PROPERTYFILEDATA);
		ExcelFileData.openExcel(IconstantPathUtility.EXCELFILEDATA);
		String url = PropertyFileData.getDataFromPropertyFile("url");
		String username = PropertyFileData.getDataFromPropertyFile("username");
		String password = PropertyFileData.getDataFromPropertyFile("password");
		String timeout = PropertyFileData.getDataFromPropertyFile("timeout");
		String browser = PropertyFileData.getDataFromPropertyFile("browser");
		long longTimeout=jsdata.stringToLong(timeout);
		int randomNumber=jsdata.getRandomNumber(1000);
		String contactName=ExcelFileData.getDataFromExcel("Contacts",2,1)+randomNumber;
		WebDriver driver=null;
		switch(browser)
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			System.out.println("please specify proper browser key");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		WebDriverDataUtility.browserSetting(longTimeout, driver);
		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		ContactPage contactPage=new ContactPage(driver);
		CreateNewContactPage createNewContactPage= new CreateNewContactPage(driver);
		ContactInfoPage contactInfoPage=new ContactInfoPage(driver);
		WebDriverDataUtility.navigateApp(url,driver);
		loginPage.loginAction(username, password);
		//	    if(driver.getTitle().contains("Home")) {
		//	    	ExcelFileData.setDataIntoExcel("Contacts", 9,5,"Homepage is displayed");
		//	    	ExcelFileData.setDataIntoExcel("Contacts", 9,6,"pass");
		//
		//	    }
		homePage.clickContacts(driver);
		// if(driver.getTitle().contains("Contacts")) {
		//	    	ExcelFileData.setDataIntoExcel("Contacts", 10,5,"Contact page is displayed");
		//	    	ExcelFileData.setDataIntoExcel("Contacts", 10,6,"pass");
		//          }  
		contactPage.createNewContact(driver);
		createNewContactPage.createContact(contactName);
		jsdata.assertionThroughIfCondition(contactInfoPage.checkContactInfo(driver),contactName,"contact");
		//		ExcelFileData.writeDataIntoExcel(IconstantPathUtility.EXCELFILEDATA);
//		ExcelFileData.closeWorkbook();
//    	WebDriverDataUtility.quitBrowser(driver);
		homePage.signout(driver, null);
		WebDriverDataUtility.quitBrowser(driver);
	}
}


