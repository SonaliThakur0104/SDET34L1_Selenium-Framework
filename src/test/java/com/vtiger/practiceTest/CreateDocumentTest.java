package com.vtiger.practiceTest;


import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import com.vtiger.pomRepository.CreateNewDocumentPage;
import com.vtiger.pomRepository.DocumentInfoPage;
import com.vtiger.pomRepository.DocumentsPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentTest {

	public static void main(String[] args) throws IOException, AWTException {
		JavaSpecificData jsdata=new JavaSpecificData();
		PropertyFileData.fetchDataFromPropertyFile(IconstantPathUtility.PROPERTYFILEDATA);
		ExcelFileData.openExcel(IconstantPathUtility.EXCELFILEDATA);
		String url = PropertyFileData.getDataFromPropertyFile("url");
		String userName = PropertyFileData.getDataFromPropertyFile("username");
		String password = PropertyFileData.getDataFromPropertyFile("password");
		String timeout = PropertyFileData.getDataFromPropertyFile("timeout");
		String browser = PropertyFileData.getDataFromPropertyFile("browser");
		long longTimeout=jsdata.stringToLong(timeout);
		int randomNumber=jsdata.getRandomNumber(1000);
		String documentTitle=ExcelFileData.getDataFromExcel("Documents",2,1)+randomNumber;
		String documentPath=ExcelFileData.getDataFromExcel("Documents",2,2);
		String documentDescription=ExcelFileData.getDataFromExcel("Documents",2,3);
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
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		WebDriverDataUtility.browserSetting(longTimeout, driver);
		//WebDriverDataUtility.explicitlyWait(driver,longTimeout);

		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
	    DocumentsPage documentsPage=new DocumentsPage(driver);
		CreateNewDocumentPage createNewDocumentPage=new CreateNewDocumentPage(driver);
		DocumentInfoPage documentInfoPage=new DocumentInfoPage(driver);
		WebDriverDataUtility.navigateApp(url,driver);
		loginPage.loginAction(userName, password);
		homePage.clickDocuments(driver);
		documentsPage.clickOnDocumentImg();
		createNewDocumentPage.createDocument(documentTitle);	
		createNewDocumentPage.documentDescription(driver, documentDescription);
         WebDriverDataUtility.switchToMainPage(driver);
	createNewDocumentPage.clickOnBoldAndIclined();
	createNewDocumentPage.createDocumentPath(documentPath);
	
	jsdata.assertionThroughIfCondition(documentInfoPage.checkDocumentTitleInfo(driver),documentTitle,"document");
	jsdata.assertionThroughIfCondition(documentInfoPage.checkDocumentNotesInfo(driver),documentPath,"document");
	jsdata.assertionThroughIfCondition(documentInfoPage.checkDocumentFileNameInfo(driver),documentDescription,"document");

	//explicit wait
//	WebElement wait=driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
//	WebDriverDataUtility.waitUntilElementVisible(wait);
     //WebDriverDataUtility.waitUntilElementClickable(homePage.getMoreDropDown(driver));
	
	homePage.signout(driver, null);
	WebDriverDataUtility.quitBrowser(driver);
}

}


