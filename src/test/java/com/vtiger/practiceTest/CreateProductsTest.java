package com.vtiger.practiceTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
import com.vtiger.pomRepository.CampaignInformationPage;
import com.vtiger.pomRepository.CampaignPage;
import com.vtiger.pomRepository.CreateNewCampaignPage;
import com.vtiger.pomRepository.CreateNewProductPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;
import com.vtiger.pomRepository.ProductInformationPage;
import com.vtiger.pomRepository.ProductPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductsTest {
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
		String productName=ExcelFileData.getDataFromExcel("Products",2,1)+randomNumber;


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
		ProductPage productPage=new ProductPage(driver);
		CreateNewProductPage createNewProductPage=new CreateNewProductPage(driver);
		ProductInformationPage productInformationPage=new ProductInformationPage(driver);
		WebDriverDataUtility.navigateApp(url,driver);

		loginPage.loginAction(username, password);
		homePage.clickProducts(driver);
		productPage.createProductImg(driver);
		createNewProductPage.createProduct(productName);

		jsdata.assertionThroughIfCondition(productInformationPage.checkProductNameInfo(),productName,"product");
		homePage.signout(driver, null);
		WebDriverDataUtility.quitBrowser(driver);





	}
}
