package com.vtiger.practiceTest;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.sdet34l1.genericLibrary.IconstantPathUtility;
import com.sdet34l1.genericLibrary.JavaSpecificData;
import com.sdet34l1.genericLibrary.PropertyFileData;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Create_CampaignTest 
{
	public static void main(String[] args) throws IOException {
		JavaSpecificData jsdata=new JavaSpecificData();
		PropertyFileData.fetchDataFromPropertyFile(IconstantPathUtility.PROPERTYFILEDATA);

		
		String url = PropertyFileData.getDataFromPropertyFile("url");
		String username = PropertyFileData.getDataFromPropertyFile("username");
		String password = PropertyFileData.getDataFromPropertyFile("password");
		String timeout = PropertyFileData.getDataFromPropertyFile("timeout");
		String browser = PropertyFileData.getDataFromPropertyFile("browser");
		
		long longTimeout=jsdata.stringToLong(timeout);
		int randomNumber=jsdata.getRandomNumber(1000);

	    FileInputStream fis1 = new FileInputStream("./src/test/resources/TestDataExcl.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		
		
		String actCampaignName=wb.getSheet("Camapaign").getRow(2).getCell(1).getStringCellValue()+randomNumber;
		
		WebDriver driver=null;
//		if(browser.equalsIgnoreCase("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver=new ChromeDriver();
//			}
//		else if(browser.equalsIgnoreCase("firefox")) {
//			WebDriverManager.firefoxdriver().setup();
//			driver=new FirefoxDriver();
	//	}
		
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
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
		
		
		driver.findElement(By.name("user_name")).sendKeys(username);
        driver.findElement(By.name("user_password")).sendKeys(password);
        driver.findElement(By.id("submitButton")).click();
        
        WebElement more=driver.findElement(By.linkText("More"));
        Actions act=new Actions(driver);
        act.moveToElement(more).click().perform();
        driver.findElement(By.name("Campaigns")).click();
        driver.findElement(By.xpath("//img[@alt='Create Campaign...']")).click();
        driver.findElement(By.name("campaignname")).sendKeys(actCampaignName);
        driver.findElement(By.cssSelector(".crmButton")).click();
        
        String expCampaignName=driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
		System.out.println("Expected campaign name is: "+expCampaignName);
		if(actCampaignName.equals(expCampaignName)) {
			System.out.println("TC Passed");
		}
		else {
			System.out.println("TC Failed");
		}
		driver.quit();
		}

}



