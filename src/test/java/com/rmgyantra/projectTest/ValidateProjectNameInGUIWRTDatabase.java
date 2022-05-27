package com.rmgyantra.projectTest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;
import com.sdet34l1.genericLibrary.DataBaseFileClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.IconstantPathUtility;
import com.sdet34l1.genericLibrary.JavaSpecificData;
import com.sdet34l1.genericLibrary.PropertyFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectNameInGUIWRTDatabase {
	public static void main(String[] args) throws IOException, SQLException {
				JavaSpecificData jsdata=new JavaSpecificData();
				PropertyFileData.fetchDataFromPropertyFile(IconstantPathUtility.RMGYANTRA_PROPERTYFILE_PATH);
				ExcelFileData.openExcel(IconstantPathUtility.RMGYANTRA_EXCELFILE_PATH);
				int randomNumber=jsdata.getRandomNumber(1000);
				
				String projectName=ExcelFileData.getDataFromExcel("projects", 1, 1)+"_"+randomNumber;
		        System.out.println(projectName);
		        
		        DataBaseFileClass.openDBConnection(IconstantPathUtility.DATABASEURL+PropertyFileData.getDataFromPropertyFile("DBProjectName"),
		        		PropertyFileData.getDataFromPropertyFile("DBUsername"),PropertyFileData.getDataFromPropertyFile("DBPassword"));
		        
		        DataBaseFileClass.setDataInDataBase("insert into project values('TY_Proj_"+randomNumber+"','Sanjay','09/05/2022','"+projectName+"','On Going',12)");
		        DataBaseFileClass.closeDBConnection();
		        
		        
				WebDriverManager.chromedriver().setup();
				WebDriver driver=new ChromeDriver();
				WebDriverDataUtility.browserSetting(10, driver);
				
				WebDriverDataUtility.navigateApp(PropertyFileData.getDataFromPropertyFile("url"), driver);
				 
				driver.findElement(By.id("usernmae")).sendKeys(PropertyFileData.getDataFromPropertyFile("rmgUserName"));
				driver.findElement(By.id("inputPassword")).sendKeys(PropertyFileData.getDataFromPropertyFile("rmgPassword"));
				driver.findElement(By.xpath("//button[text()=\"Sign in\"]")).click();
				
				driver.findElement(By.linkText("Projects")).click();
				List<WebElement> listOfProjects=driver.findElements(By.xpath("//table//tbody//tr//td[2]"));
				
				for(WebElement projects:listOfProjects) {
					if(projects.getText().equalsIgnoreCase(projectName)) {
						System.out.println("ProjectName is visible in GUI");
						System.out.println("TC Passed");
						break;
						
					}
				}
				
				WebDriverDataUtility.quitBrowser(driver);
			}
		


}
