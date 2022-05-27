package com.vtiger.practiceTest;
    
    import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.util.Properties;
	import java.util.Random;
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
	import org.openqa.selenium.support.ui.Select;

import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.IconstantPathUtility;
import com.sdet34l1.genericLibrary.JavaSpecificData;
import com.sdet34l1.genericLibrary.PropertyFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.CreateNewOrganizationPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;
import com.vtiger.pomRepository.OrganizationInfoPage;
import com.vtiger.pomRepository.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

	public class CreateOrganizationDropdownTest {
		public static void main(String[] args) throws IOException {
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
			String organizationName=ExcelFileData.getDataFromExcel("Organization",2,1)+randomNumber;
			String industryName=ExcelFileData.getDataFromExcel("Organization",2,2);
			String typeName=ExcelFileData.getDataFromExcel("Organization",2,3);

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
			OrganizationPage organizationPage=new OrganizationPage(driver);
			CreateNewOrganizationPage createNewOrganizationPage=new CreateNewOrganizationPage(driver);
			OrganizationInfoPage organizationInfoPage=new OrganizationInfoPage(driver);
			
			WebDriverDataUtility.navigateApp(url,driver);
			loginPage.loginAction(username, password);
			homePage.clickOrganization(driver);
			organizationPage.clickOnOrganizationImg();
			createNewOrganizationPage.createNeworganizationName(driver, organizationName);
			createNewOrganizationPage.industryDropdown("Education","Press");
			createNewOrganizationPage.saveNewOrganizationName();
			jsdata.assertionThroughIfCondition(organizationInfoPage.checkOrganizationname(),organizationName,"organization with dropdown");
			jsdata.assertionThroughIfCondition(organizationInfoPage.checkIndustryName(),industryName,"");
			jsdata.assertionThroughIfCondition(organizationInfoPage.checkPressName(),typeName,"");
			homePage.signout(driver, null);
      		WebDriverDataUtility.quitBrowser(driver);
		}
}
