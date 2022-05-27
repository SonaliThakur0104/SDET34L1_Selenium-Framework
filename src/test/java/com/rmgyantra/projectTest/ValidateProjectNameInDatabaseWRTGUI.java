package com.rmgyantra.projectTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;
import com.sdet34l1.genericLibrary.DataBaseFileClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.IconstantPathUtility;
import com.sdet34l1.genericLibrary.JavaSpecificData;
import com.sdet34l1.genericLibrary.PropertyFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectNameInDatabaseWRTGUI {
	public static void main(String[] args) throws IOException, SQLException {
		JavaSpecificData jsdata=new JavaSpecificData();
		PropertyFileData.fetchDataFromPropertyFile(IconstantPathUtility.RMGYANTRA_PROPERTYFILE_PATH);
		ExcelFileData.openExcel(IconstantPathUtility.RMGYANTRA_EXCELFILE_PATH);
		int randomNumber=jsdata.getRandomNumber(1000);
		
		  
        DataBaseFileClass.openDBConnection(IconstantPathUtility.DATABASEURL+PropertyFileData.getDataFromPropertyFile("DBProjectName"),
        		PropertyFileData.getDataFromPropertyFile("DBUsername"),PropertyFileData.getDataFromPropertyFile("DBPassword"));
        
        DataBaseFileClass.closeDBConnection();
        
        
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		WebDriverDataUtility.browserSetting(10, driver);
		
		WebDriverDataUtility.navigateApp(PropertyFileData.getDataFromPropertyFile("url"), driver);
		 
		driver.findElement(By.id("usernmae")).sendKeys(PropertyFileData.getDataFromPropertyFile("rmgUserName"));
		driver.findElement(By.id("inputPassword")).sendKeys(PropertyFileData.getDataFromPropertyFile("rmgPassword"));
		driver.findElement(By.xpath("//button[text()=\"Sign in\"]")).click();
		
		driver.findElement(By.linkText("Projects")).click();
		
		driver.findElement(By.xpath("//span[text()='Create Project']")).getText();
		driver.findElement(By.name("projectName")).sendKeys(ExcelFileData.getDataFromExcel("projects", 1,1)+"_"+randomNumber);
		driver.findElement(By.name("createdBy")).sendKeys(ExcelFileData.getDataFromExcel("projects", 1,2));
		WebElement dropdown=driver.findElement(By.name("status"));
		Select s=new Select(dropdown);
		s.selectByVisibleText("On Goging");
		
		
		
		//WebDriverDataUtility.quitBrowser(driver);
	}
}
