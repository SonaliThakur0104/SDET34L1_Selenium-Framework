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

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganization_DropdownTest {
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
				
				
			long longTimeOut = Long.parseLong(timeout);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);
			
			//Login
			driver.get(url);
			driver.findElement(By.name("user_name")).sendKeys(username);
			driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			
			//Click on create Organization
			driver.findElement(By.linkText("Organizations")).click();
			driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
			
			
			driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(organizationName);
			
			//select Education from Industry dropdown
			WebElement industryDropdown = driver.findElement(By.xpath("//select[@name='industry']"));
			Select s = new Select(industryDropdown);
			s.selectByValue("Education");
			
			//select Press from type dropdown
			WebElement typeDropdown = driver.findElement(By.xpath("//select[@name='accounttype']"));
			Select s1 = new Select(typeDropdown);
			s1.selectByValue("Press");
			
			//click on save
			driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
			
			//validate
			WebElement actOrganizationName = driver.findElement(By.id("dtlview_Organization Name"));
			
			if(actOrganizationName.getText().equalsIgnoreCase(organizationName))
			{
				System.out.println("education organization created successfully");
			}
			else
			{
				System.out.println("fail");
			}
			
			//logout
			WebElement administrator = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions act = new Actions(driver);
			act.moveToElement(administrator).perform();
			driver.findElement(By.linkText("Sign Out")).click();
			
			driver.quit();
		}

}


