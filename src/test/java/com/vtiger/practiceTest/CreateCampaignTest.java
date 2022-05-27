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
import com.vtiger.pomRepository.CampaignInformationPage;
import com.vtiger.pomRepository.CampaignPage;
import com.vtiger.pomRepository.CreateNewCampaignPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignTest {
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
        long longTimeout=jsdata.stringToLong(timeout);
		int randomNumber=jsdata.getRandomNumber(1000);
        String campaignName=ExcelFileData.getDataFromExcel("Campaign",2,1)+randomNumber;
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
        WebDriverDataUtility.browserSetting(longTimeout, driver);
		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		CampaignPage campaignPage=new CampaignPage(driver);
		CreateNewCampaignPage createNewCampaignPage=new CreateNewCampaignPage(driver);
		CampaignInformationPage campaignInformationPage=new CampaignInformationPage(driver);
		WebDriverDataUtility.navigateApp(url,driver);
		loginPage.loginAction(username, password);
		homePage.clickCampaign(driver,webDriverDataUtility);
		campaignPage.getCreateCampaignImg().click();
		createNewCampaignPage.createCampaign(campaignName);      
		jsdata.assertionThroughIfCondition(campaignInformationPage.checkCampaigninfo(),campaignName,"campaign");
		homePage.signout(driver,webDriverDataUtility);
		WebDriverDataUtility.quitBrowser(driver);
	}

}
