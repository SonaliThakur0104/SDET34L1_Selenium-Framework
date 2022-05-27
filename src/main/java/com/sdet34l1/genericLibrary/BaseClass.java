package com.sdet34l1.genericLibrary;

import java.io.IOException;


import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;


/**
 * this class is common to all the test scripts and contains all the configuration TestNG annotations.
 * @author Sonali
 *Baseclass is parent class of all sub classes
 */
public class BaseClass {
	public JavaSpecificData javaSpecificData;
	public LoginPage loginPage;
	public HomePage homePage;
	public String url;
	public String username;
	public String password;
	public String browser;
	public int randomNumber;
	public WebDriver driver;
	public long longTimeout;
	public WebDriverDataUtility webDriverDataUtility;
	public static WebDriver staticdriver;
	
	/**
	 * This method is used to open excel file and property file
	 */
	@BeforeSuite(groups="baseclass")
	public void beforeSuite1Test() {
		//open database if it is required
		try {
			PropertyFileData.fetchDataFromPropertyFile(IconstantPathUtility.PROPERTYFILEDATA);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			ExcelFileData.openExcel(IconstantPathUtility.EXCELFILEDATA);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		}
	
	
	/**
	 * This method is used to do normal execution configuration like  launching the browser,
	 * navigate to app,read the common data,browser settings,implicit wait,instance of explicit
	 * wait.
	 */
	
	//@Parameters("browser")
	@BeforeClass(groups="baseclass")
	public void beforeClass1Test(/*String browser*/) {
		javaSpecificData=new JavaSpecificData();
	    url = PropertyFileData.getDataFromPropertyFile("url");
	    
		//username = PropertyFileData.getDataFromPropertyFile("username");
		username=System.getProperty("USERNAME");

		// = PropertyFileData.getDataFromPropertyFile("password");
		password=System.getProperty("PASSWORD");

		String timeout = PropertyFileData.getDataFromPropertyFile("timeout");
		
		//String browser = PropertyFileData.getDataFromPropertyFile("browser");
		browser=System.getProperty("BROWSER");
		
        longTimeout=javaSpecificData.stringToLong(timeout);
		randomNumber=javaSpecificData.getRandomNumber(1000);
        switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			break;
		default:
			System.out.println("please specify proper browser key");
			break;
         }
        staticdriver=driver;
        WebDriverDataUtility.browserSetting(longTimeout, driver);
        webDriverDataUtility=new WebDriverDataUtility(driver);
        webDriverDataUtility.initializeActions(driver);
        WebDriverDataUtility.explicitlyWait(driver, longTimeout);
	    
        loginPage=new LoginPage(driver);
		homePage=new HomePage(driver);
		
		WebDriverDataUtility.navigateApp(url,driver);

		}

	/**
	 *this method contains login actions. 
	 */
	@BeforeMethod(groups="baseclass")
	public void beforeMethod1Test() {
		loginPage.loginAction(username, password);

		}

	/**
	 * this method contains logout actions.
	 */
	@AfterMethod(groups="baseclass")
	public void afterMethod1Test() {
		homePage.signout(driver,webDriverDataUtility);

		}

	
	/**
	 * This method is used to quit the browser
	 */
	@AfterClass(groups="baseclass")
	public void afterClass1Test() {
		WebDriverDataUtility.quitBrowser(driver);

		}

	
	/**
	 * This method is used to save the workbook and  close the workbook. 
	 */
	@AfterSuite(groups="baseclass")
	public void afterSuite1Test() {
		ExcelFileData.writeDataIntoExcel(IconstantPathUtility.EXCELFILEDATA);
		ExcelFileData.closeWorkbook();
		}
	
	


}
