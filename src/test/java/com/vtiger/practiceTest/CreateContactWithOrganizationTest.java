package com.vtiger.practiceTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.IconstantPathUtility;
import com.sdet34l1.genericLibrary.JavaSpecificData;
import com.sdet34l1.genericLibrary.PropertyFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.ContactInfoPage;
import com.vtiger.pomRepository.ContactPage;
import com.vtiger.pomRepository.CreateNewContactPage;
import com.vtiger.pomRepository.CreateNewOrganizationPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;
import com.vtiger.pomRepository.OrganizationInfoPage;
import com.vtiger.pomRepository.OrganizationPage;
import com.vtiger.pomRepository.SearchOrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		JavaSpecificData jsdata=new JavaSpecificData();
		WebDriver driver=null;
		PropertyFileData.fetchDataFromPropertyFile(IconstantPathUtility.PROPERTYFILEDATA);
		ExcelFileData.openExcel(IconstantPathUtility.EXCELFILEDATA);
		String url = PropertyFileData.getDataFromPropertyFile("url");
		String username = PropertyFileData.getDataFromPropertyFile("username");
		String password = PropertyFileData.getDataFromPropertyFile("password");
		String timeout = PropertyFileData.getDataFromPropertyFile("timeout");
		String browser = PropertyFileData.getDataFromPropertyFile("browser");
		long longTimeout=jsdata.stringToLong(timeout);
		int randomNumber=jsdata.getRandomNumber(1000);
		String contactName=ExcelFileData.getDataFromExcel("Contacts",5,2)+randomNumber;
		String organizationName=ExcelFileData.getDataFromExcel("Contacts",5,1)+randomNumber;
		String organizationNameCreated=ExcelFileData.getDataFromExcel("Contacts",5,1);

		if(browser.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new FirefoxDriver();
		}
		WebDriverDataUtility.browserSetting(longTimeout, driver);
		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		OrganizationPage organizationPage=new OrganizationPage(driver);
		CreateNewOrganizationPage createNewOrganizationPage=new CreateNewOrganizationPage(driver);
		OrganizationInfoPage organizationInfoPage=new OrganizationInfoPage(driver);
		ContactPage contactPage=new ContactPage(driver);
		CreateNewContactPage createNewContactPage= new CreateNewContactPage(driver);
		ContactInfoPage contactInfoPage=new ContactInfoPage(driver);
		SearchOrganizationPage searchOrganizationPage=new SearchOrganizationPage(driver);
		WebDriverDataUtility.navigateApp(url,driver);
		loginPage.loginAction(username, password);
		homePage.clickOrganization(driver);
		organizationPage.clickOnOrganizationImg();
		createNewOrganizationPage.createNeworganizationName(driver, organizationName);
		WebDriverDataUtility.explicitlyWait(driver,longTimeout);
		WebDriverDataUtility.waitUntilElementClickable(organizationInfoPage.getOrganizationname());
		homePage.clickContacts(driver);
		//        if(driver.getTitle().contains("Home")) {
		//        	wb.getSheet("Contacts").getRow(9).createCell(5).setCellValue("Homepage is dsplayed");
		//        	wb.getSheet("Contacts").getRow(9).createCell(6).setCellValue("pass");
		//        	}
		contactPage.createNewContact(driver);
		//        if(driver.getTitle().contains("Contacts")) {
		//
		//        	wb.getSheet("Contacts").getRow(10).createCell(5).setCellValue("Contacts page is dsplayed");
		//        	wb.getSheet("Contacts").getRow(10).createCell(6).setCellValue("pass");
		// }
		createNewContactPage.enterContactNameAndSwitchToSearchOrganization(contactName, driver);

		searchOrganizationPage.selectOrganization(driver, organizationNameCreated);
		WebDriverDataUtility.switchToWindowBasedOnTitle(driver,"Contacts");
		createNewContactPage.saveContactPage(driver);

		jsdata.assertionThroughIfCondition(contactInfoPage.checkContactInfo(driver),contactName,"contact with organization");
		jsdata.assertionThroughIfCondition(contactInfoPage.checkOrganizationInfo(driver),organizationNameCreated,"");
		homePage.signout(driver, null);
		WebDriverDataUtility.quitBrowser(driver);


	}

}
