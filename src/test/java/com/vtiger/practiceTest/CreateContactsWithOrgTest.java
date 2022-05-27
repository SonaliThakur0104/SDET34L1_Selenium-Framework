package com.vtiger.practiceTest;


	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.util.Properties;
	import java.util.Random;
	import java.util.Set;
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

	import com.sdet34l1.genericLibrary.ExcelFileData;
	import com.sdet34l1.genericLibrary.IconstantPathUtility;
	import com.sdet34l1.genericLibrary.JavaSpecificData;
	import com.sdet34l1.genericLibrary.PropertyFileData;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class CreateContactsWithOrgTest {

		public static void main(String[] args) throws IOException, InterruptedException {
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

			//fetch data from excel file
			FileInputStream fis1=new FileInputStream("./src/test/resources/TestDataExcl.xlsx");
			Workbook wb=WorkbookFactory.create(fis1);
			Sheet sh=wb.getSheet("Contacts");
			//organization
			Row row1=sh.getRow(5);
			Cell cell1=row1.getCell(1);
			String data1=cell1.getStringCellValue();
		    String actOrganizationname=data1;
		    System.out.println(actOrganizationname);
		    
		//contact
		    Row row2=sh.getRow(5);
		    Cell cell2=row2.getCell(2);
		    String data2=cell2.getStringCellValue();
	        String actContactLastName=data2+randomNumber;
	        System.out.println(actContactLastName);
	        
	        if(browser.equalsIgnoreCase("chrome")) 
	        {
	        WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
	        }
	        else if(browser.equalsIgnoreCase("firefox"))
	        {
	            WebDriverManager.chromedriver().setup();
	    		driver=new FirefoxDriver();
	         }
	        
	        driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
			driver.get(url);
			//testcase step 1:==login to the app
			driver.findElement(By.name("user_name")).sendKeys(username);
	        driver.findElement(By.name("user_password")).sendKeys(password);
	        driver.findElement(By.id("submitButton")).click();
	        
	        if(driver.getTitle().contains("Home")) {
	        	wb.getSheet("Contacts").getRow(9).createCell(5).setCellValue("Homepage is dsplayed");
	        	wb.getSheet("Contacts").getRow(9).createCell(6).setCellValue("pass");
	        	}
	        //click on contacts
	        driver.findElement(By.linkText("Contacts")).click();

	        if(driver.getTitle().contains("Contacts")) {

	        	wb.getSheet("Contacts").getRow(10).createCell(5).setCellValue("Contacts page is dsplayed");
	        	wb.getSheet("Contacts").getRow(10).createCell(6).setCellValue("pass");

	        }
	        
	        driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	        driver.findElement(By.name("lastname")).sendKeys(actContactLastName);
	        driver.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td/img")).click();
	        //String mainid=driver.getWindowHandle();
	        Set<String> allid=driver.getWindowHandles();
	        for(String id:allid) {
	    		driver.switchTo().window(id);
	            if(driver.getTitle().contains("Accounts&action")) {
	        		break;
	        	}
	        	}
	        	
	        driver.findElement(By.name("search_text")).sendKeys(actOrganizationname);
	        driver.findElement(By.name("search")).click();
	        driver.findElement(By.linkText("TestYantra")).click();
	        Thread.sleep(2000);
	        
	       Set<String> allid1=driver.getWindowHandles();
	       for(String id1:allid1) {
	       	   driver.switchTo().window(id1);
	       	   if(driver.getTitle().contains("Contacts&action")) {
	                 break;
	        	}
	        	}
	        
	       // driver.switchTo().window(mainid);
	       
	        driver.findElement(By.cssSelector(".crmButton")).click();
	        
	        //fetch
	        String expContactLastName=driver.findElement(By.id("dtlview_Last Name")).getText();
	        String expOrganizationName=driver.findElement(By.linkText("TestYantra")).getText();

	        
	        if(actContactLastName.equals(expContactLastName)&& actOrganizationname.equals(expOrganizationName))
	        {        System.out.println("TC passed");
	        System.out.println("Contact Lastname is created");
	        }
	        
//	        if(actOrganizationname.equals(expOrganizationName)) {
//	        	System.out.println("Expected Orgnization Name is: "+expOrganizationName);
//	        }
//	       
	        WebElement administrator=driver.findElement(By.xpath("//span[text()=' Administrator']/../following-sibling::td[1]"));
	        Actions act=new Actions(driver);
	        act.moveToElement(administrator).perform();
	        driver.findElement(By.linkText("Sign Out")).click();
	        
	        FileOutputStream fos=new FileOutputStream("./src/test/resources/TestDataExcl.xlsx");
	        wb.write(fos);
	        wb.close();
	        driver.quit();
		}

	}



