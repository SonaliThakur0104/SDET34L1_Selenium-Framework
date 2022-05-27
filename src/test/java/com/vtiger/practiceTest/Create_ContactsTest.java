package com.vtiger.practiceTest;

	

	import java.io.FileInputStream;
	import java.io.IOException;
	import java.util.concurrent.TimeUnit;

	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.apache.poi.util.SystemOutLogger;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	import com.sdet34l1.genericLibrary.IconstantPathUtility;
	import com.sdet34l1.genericLibrary.JavaSpecificData;
	import com.sdet34l1.genericLibrary.PropertyFileData;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class Create_ContactsTest {

		public static void main(String[] args) throws InterruptedException, IOException {
	        JavaSpecificData jsdata=new JavaSpecificData();
	        PropertyFileData.fetchDataFromPropertyFile(IconstantPathUtility.PROPERTYFILEDATA);

			
			String url = PropertyFileData.getDataFromPropertyFile("url");
			String username = PropertyFileData.getDataFromPropertyFile("username");
			String password = PropertyFileData.getDataFromPropertyFile("password");
			String timeout = PropertyFileData.getDataFromPropertyFile("timeout");
			String browser = PropertyFileData.getDataFromPropertyFile("browser");
			
			long longTimeout=jsdata.stringToLong(timeout);
			int randomNumber=jsdata.getRandomNumber(1000);

			//fetch contact name from excel
			FileInputStream fis1 = new FileInputStream("./src/test/resources/TestDataExcl.xlsx");
			Workbook book = WorkbookFactory.create(fis1);
			
			
		    String lastName = book.getSheet("Contacts").getRow(2).getCell(1).getStringCellValue()+randomNumber;
			
		    WebDriver driver= null;
			
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
			//testcase step 1:==login to the app
			driver.findElement(By.name("user_name")).sendKeys(username);
	        driver.findElement(By.name("user_password")).sendKeys(password);
	        driver.findElement(By.id("submitButton")).click();
	        
		    
		    if(driver.getTitle().contains("Home")) {
	          book.getSheet("Contacts").createRow(9).createCell(5).setCellValue("Homepage is dsplayed");
	          book.getSheet("Contacts").createRow(9).createCell(6).setCellValue("pass");
	        	}
		    
		  //click on contacts
	        driver.findElement(By.linkText("Contacts")).click();

	        if(driver.getTitle().contains("Contacts")) {

	        	book.getSheet("Contacts").createRow(10).createCell(5).setCellValue("Contacts page is dsplayed");
	        	book.getSheet("Contacts").createRow(10).createCell(6).setCellValue("pass");

	        }
		    
	        driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	        driver.findElement(By.name("lastname")).sendKeys(lastName);
	        //save
	        driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();
	        
	        String actLastName=driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
	        
			
	        if(lastName.contains(actLastName)) {
				System.out.println("TC Passed");
				System.out.println("Contact created successfully");
			}
			
			WebElement logout=driver.findElement(By.xpath("//span[text()=' Administrator']/../following-sibling::td[1]"));
			Actions act=new Actions(driver);
			act.moveToElement(logout).perform();
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			
			driver.quit();
		}

	}
