package com.vtiger.practiceTest;

import java.io.FileInputStream;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.IconstantPathUtility;
import com.sdet34l1.genericLibrary.JavaSpecificData;
import com.sdet34l1.genericLibrary.PropertyFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.CampaignInformationPage;
import com.vtiger.pomRepository.CampaignPage;
import com.vtiger.pomRepository.CreateNewCampaignPage;
import com.vtiger.pomRepository.CreateNewProductPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;
import com.vtiger.pomRepository.ProductPage;
import com.vtiger.pomRepository.SearchProductspage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProduct {
	public static void main(String[] args) throws IOException {
		JavaSpecificData jsdata=new JavaSpecificData();
		WebDriverDataUtility webDriverDataUtility=new WebDriverDataUtility(null);

		PropertyFileData.fetchDataFromPropertyFile(IconstantPathUtility.PROPERTYFILEDATA);
		ExcelFileData.openExcel(IconstantPathUtility.EXCELFILEDATA);
		String url = PropertyFileData.getDataFromPropertyFile("url");
		String username = PropertyFileData.getDataFromPropertyFile("username");
		String password = PropertyFileData.getDataFromPropertyFile("password");
		String timeout = PropertyFileData.getDataFromPropertyFile("timeout");
		String browser = PropertyFileData.getDataFromPropertyFile("browser");

		long longTimeOut=jsdata.stringToLong(timeout);
		int randomNumber=jsdata.getRandomNumber(1000);
		String campaignName=ExcelFileData.getDataFromExcel("Campaign",5,1)+randomNumber;
		String productName=ExcelFileData.getDataFromExcel("Campaign",5,2);
		WebDriver driver=null;

		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		default:
			System.out.println("please specify proper browser key");
			break;
		}
		WebDriverDataUtility.browserSetting(longTimeOut, driver);
		WebDriverDataUtility.explicitlyWait(driver,longTimeOut);
		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		CampaignPage campaignPage=new CampaignPage(driver);
		CreateNewCampaignPage createNewCampaignPage=new CreateNewCampaignPage(driver);
		CampaignInformationPage campaignInformationPage=new CampaignInformationPage(driver);
		ProductPage productPage=new ProductPage(driver);
		CreateNewProductPage createNewProductPage=new CreateNewProductPage(driver);
		SearchProductspage searchProductspage=new SearchProductspage(driver);
		WebDriverDataUtility.navigateApp(url,driver);
		loginPage.loginAction(username, password);
		//	        if(driver.getTitle().contains("Home")) {
		//	        	wb.getSheet("Campaign").createRow(9).createCell(5).setCellValue("Homepage is dsplayed");
		//	        	wb.getSheet("Campaign").createRow(9).createCell(6).setCellValue("pass");
		//	        	}
		//	        //click on campaign
		//	        if(driver.getTitle().contains("Campaigns")) {
		//	        	wb.getSheet("Campaign").createRow(10).createCell(5).setCellValue("Campaigns page is dsplayed");
		//	        	wb.getSheet("Campaign").createRow(10).createCell(6).setCellValue("pass");
		//	        	}
		homePage.clickProducts(driver);
		productPage.createProductImg(driver);
		createNewProductPage.createProduct(productName);
		homePage.clickProducts(driver);
		WebDriverDataUtility.waitUntilElementClickable(homePage.getMoreDropDown(driver));
		homePage.clickCampaign(driver,webDriverDataUtility);
		campaignPage.getCreateCampaignImg().click();
		createNewCampaignPage.enterCampaignNameAndSwitchToSearchProduct(campaignName, driver);
		searchProductspage.selectProduct(driver, productName);
		WebDriverDataUtility.switchToWindowBasedOnTitle(driver,"Campaigns");
		createNewCampaignPage.saveCampaignPage(driver);
		jsdata.assertionThroughIfCondition(campaignInformationPage.checkCampaigninfo(),campaignName,"campaign with product");
		jsdata.assertionThroughIfCondition(campaignInformationPage.checkProductInfo(),productName,"");
		homePage.signout(driver, webDriverDataUtility);
		WebDriverDataUtility.quitBrowser(driver);
	}
}
