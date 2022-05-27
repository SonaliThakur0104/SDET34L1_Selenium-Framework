package com.vtiger.practiceTest;



	import java.awt.AWTException;
	import java.awt.Robot;
	import java.awt.event.KeyEvent;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
import java.time.Duration;
import java.util.List;
	import java.util.Properties;
	import java.util.Random;
	import java.util.concurrent.TimeUnit;

	import org.apache.poi.EncryptedDocumentException;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sdet34l1.genericLibrary.JavaSpecificData;
import com.sdet34l1.genericLibrary.PropertyFileData;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentsPracticeTest {

		
		public static void main(String[] args) throws IOException, AWTException {
			
			JavaSpecificData jsdata=new JavaSpecificData();
			
			
			String url = PropertyFileData.getDataFromPropertyFile("url");
			String username = PropertyFileData.getDataFromPropertyFile("username");
			String password = PropertyFileData.getDataFromPropertyFile("password");
			String timeout = PropertyFileData.getDataFromPropertyFile("timeout");
			String browser = PropertyFileData.getDataFromPropertyFile("browser");
			
			long longTimeout=jsdata.stringToLong(timeout);
			int randomNumber=jsdata.getRandomNumber(1000);

			FileInputStream fis1 = new FileInputStream("./src/test/resources/TestDataExcl.xlsx");
			Workbook wb = WorkbookFactory.create(fis1);
			
			
			String documentTitle=wb.getSheet("Documents").getRow(2).getCell(1).getStringCellValue()+randomNumber;
			String documentPath=wb.getSheet("Documents").getRow(2).getCell(2).getStringCellValue();
			String documentDescription=wb.getSheet("Documents").getRow(2).getCell(3).getStringCellValue();

			
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
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
			//login
			driver.get(url);
			driver.findElement(By.name("user_name")).sendKeys(username);
			driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			
			//click on create document
			driver.findElement(By.linkText("Documents")).click();
			driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
			driver.findElement(By.name("notes_title")).sendKeys(documentTitle);
			
			//Document Description
			WebElement iframe = driver.findElement(By.xpath("//iframe[@title='Rich text editor, notecontent, press ALT 0 for help.']"));
			driver.switchTo().frame(iframe);
			
			driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(documentDescription, Keys.CONTROL+"a");
			
//			Robot rbt = new Robot();
//			rbt.keyPress(KeyEvent.VK_CONTROL);
//			rbt.keyPress(KeyEvent.VK_A);
//			rbt.keyRelease(KeyEvent.VK_CONTROL);
//			rbt.keyRelease(KeyEvent.VK_A);
//			
			
			driver.switchTo().defaultContent();
			driver.findElement(By.xpath("//a[@id='cke_5']")).click();
			driver.findElement(By.xpath("//a[@id='cke_6']")).click();
			
			//document path
			driver.findElement(By.id("filename_I__")).sendKeys(documentPath);
			
			//save
			driver.findElement(By.xpath("//b[text()='File Information']/../../following-sibling::tr[4]//input[@title='Save [Alt+S]']")).click();
			WebDriverWait wait=new WebDriverWait(driver,longTimeout);
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='dvHeaderText']"))));
			
			//fetch
			String expDocumentTitle=driver.findElement(By.id("dtlview_Title")).getText();
			if(documentTitle.equals(expDocumentTitle)) {
				System.out.println("TC Passed.");
				System.out.println("Document Title is: "+documentTitle);
			}
			//logout
			WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions act = new Actions(driver);
			act.moveToElement(administrator).perform();
			driver.findElement(By.linkText("Sign Out")).click();
					
			driver.quit();
			
			
		}

	}





