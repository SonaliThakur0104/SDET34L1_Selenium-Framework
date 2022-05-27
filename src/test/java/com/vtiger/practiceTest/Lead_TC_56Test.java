package com.vtiger.practiceTest;

import java.io.IOException;

import org.openqa.selenium.Alert;
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
import com.vtiger.pomRepository.CampaignInformationPage;
import com.vtiger.pomRepository.CampaignPage;
import com.vtiger.pomRepository.CreateNewCampaignPage;
import com.vtiger.pomRepository.CreateNewLeadsPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LeadsInfoPage;
import com.vtiger.pomRepository.LeadsPage;
import com.vtiger.pomRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lead_TC_56Test {
	public static void main(String[] args) throws IOException {
		JavaSpecificData jsdata=new JavaSpecificData();
		PropertyFileData.fetchDataFromPropertyFile(IconstantPathUtility.PROPERTYFILEDATA);
		ExcelFileData.openExcel(IconstantPathUtility.LEAD_EXCELFILE_PATH);
		String url = PropertyFileData.getDataFromPropertyFile("url");
		String username = PropertyFileData.getDataFromPropertyFile("username");
		String password = PropertyFileData.getDataFromPropertyFile("password");
		String timeout = PropertyFileData.getDataFromPropertyFile("timeout");
		String browser = PropertyFileData.getDataFromPropertyFile("browser");
		long longTimeout=jsdata.stringToLong(timeout);
		int randomNumber=jsdata.getRandomNumber(1000);
		String leadFirstName=ExcelFileData.getDataFromExcel("Lead",1,1);
		String leadLastName=ExcelFileData.getDataFromExcel("Lead",1,2)+randomNumber;
		String leadCompanyName=ExcelFileData.getDataFromExcel("Lead",1,3)+randomNumber;
		String leadFirstName1=ExcelFileData.getDataFromExcel("Lead",3,1);
		String leadLastName1=ExcelFileData.getDataFromExcel("Lead",3,2)+randomNumber;
		String leadCompanyName1=ExcelFileData.getDataFromExcel("Lead",3,3)+randomNumber;
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
			LeadsPage leadsPage=new LeadsPage(driver);
     		CreateNewLeadsPage createNewLeadsPage=new CreateNewLeadsPage(driver);
			LeadsInfoPage leadsInfoPage=new LeadsInfoPage(driver);
			WebDriverDataUtility.navigateApp(url,driver);
			loginPage.loginAction(username, password);
			homePage.clickLeads(driver);
			leadsPage.clickCreateLeadsImg();
			createNewLeadsPage.createLead(leadFirstName, leadLastName, leadCompanyName);
			leadsInfoPage.clickOnDeleteButton(driver);
			
	//	driver.findElement(By.xpath("//img[@alt='Create Lead...']")).click();
//		driver.findElement(By.name("firstname")).sendKeys(leadFirstName1);
//		driver.findElement(By.name("lastname")).sendKeys(leadLastName1);
//		driver.findElement(By.name("company")).sendKeys(leadCompanyName1);
//		driver.findElement(By.xpath("//b[text()='Description Information']/../../following-sibling::tr[2]//input[1]")).click();
//		driver.findElement(By.xpath("//td[@class='moduleName']/a")).click();
//
//		driver.findElement(By.xpath("//a[text()='Lead No']/../../following-sibling::tr[3]/td[1]//input[@name='selected_id']")).click();
//		driver.findElement(By.xpath("//input[@value='Delete']")).click();
//
//		Alert alt=driver.switchTo().alert();
//		alt.accept();
//
//        System.out.println("TC passed");
			homePage.signout(driver, null);
			WebDriverDataUtility.quitBrowser(driver);
		

	}

}


