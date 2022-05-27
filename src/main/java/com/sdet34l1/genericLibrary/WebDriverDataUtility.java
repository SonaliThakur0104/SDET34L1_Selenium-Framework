package com.sdet34l1.genericLibrary;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class is used to maintain all web driver common actions
 * @author Sonali
 *
 */

public class WebDriverDataUtility {
	static WebDriverWait wait;
	static WebDriver driver;
	static Select select;
    JavascriptExecutor javaScriptExecutor;
	static JavaSpecificData javaSpecificData=new JavaSpecificData();
	 Actions actions;
	/**
	 * This method is used to initialize and launch the browser
	 
	public static void launchBrowser(String browser){ 
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
		
	}}
	*/
	
	/**
	 * This method is used to return the driver instance
	 * @param driver
	 * @return
	
	public static WebDriver getDriver(WebDriver driver) {
		return driver;
	}
	 */
	
	public WebDriverDataUtility(WebDriver staticdriver) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method is used to navigate the app
	 */
	public static void navigateApp(String url,WebDriver driver) {
		driver.get(url);
	}
	
	/**
	 * This method is used to maximize the browser and implicitly wait 
	 */
	public static void browserSetting(long longTimeOut,WebDriver driver) {
		maximizeBrowser(driver);
		waitTillPageLoad(longTimeOut,driver);
	}
	
	/**
	 * This method is used to maximize the browser
	 * @param driver
	 */
	public static void maximizeBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used to implicitly wait till page load
	 * @param longTimeOut
	 * @param driver
	 */
	public static void waitTillPageLoad(long longTimeOut,WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(longTimeOut, TimeUnit.SECONDS);

	}
	
	/**
	 * This method is used to initialize actions class
	 * @param more
	 * @param driver
	 */
	public void initializeActions(WebDriver driver) {
		actions = new Actions(driver);

	}
	
	/**
	 * This method is used to move the mouse hover on the element
	 * @param more
	 * @param driver
	 */
	public void mouseHoverOnTheElement(WebElement element,WebDriver driver) {
		actions.moveToElement(element).perform();
	}
	
	/**
	 * this method is used to double click in web page
	 */
	public void doubleClick(WebDriver driver) {
         actions.doubleClick().perform();
		
	}
	
	/**
	 * this method is used to double click the particular element
	 */
	public void doubleClick(WebElement element,WebDriver driver) {
		actions.doubleClick(element).perform();
	}
	
	
	/**
	 * This method is used to close the browser instance
	 * @param driver
	 */
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}
	
	/**
	 * TThis method is used to wait the control till the particular element is clickable
	 * @param element
	 */
	public static void waitUntilElementClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method is used to wait the control till the particular element is visible
	 * @param element
	 */
	public static void waitUntilElementVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used to initialize the wait instance
	 * @param driver
	 * @param TimeOut
	 */
	public static void explicitlyWait(WebDriver driver,long longTimeOut) {
		wait=new WebDriverWait(driver,longTimeOut);
	}
	
	/**
	 * This method is used to switch the window based on title
	 * @param driver
	 * @param partialText
	 */
	public static void switchToWindowBasedOnTitle(WebDriver driver,String partialText) {
		Set<String> allid=driver.getWindowHandles();
        for(String id:allid) {
    		driver.switchTo().window(id);
            if(driver.getTitle().contains(partialText)) {
        		break;
        	}
	     }
        }
	
        /**
         * This method is used to select the element in the dropdown by using value
         * @param element
         * @param value
         */
        public static void listDropDown(WebElement element, String value) {
        	select = new Select(element);
        	select.selectByValue(value);
			}
	
        
        /**
         * This method is used to select the value in the dropdown by using index
         * @param element
         * @param index
         */
        public static void listDropDown(WebElement element, int index) {
        	select = new Select(element);
        	select.selectByIndex(index);
		}
	
        /**
         * This method is used to select the value in the dropdown by using visible text.
         * @param visibleText
         * @param element
         */
        public static void listDropDown(String visibleText,WebElement element) {
        	select = new Select(element);
        	select.selectByVisibleText(visibleText);
			
        }
        
        /**
         * This is used to switch to child frame by using index.
         * @param driver
         * @param index
         */
        public static void switchToFrame(WebDriver driver,int index) {
		  driver.switchTo().frame(index);
        }
        
        /**
         * This method is used to switch to frame by using name or id
         * @param driver
         * @param nameOrId
         */
        public static void switchToFrame(WebDriver driver,String nameOrId) {
  		  driver.switchTo().frame(nameOrId);
          }
        
        /**
         * This method is used to switch to frame by using web element
         * @param driver
         * @param element
         */
        public static void switchToFrame(WebDriver driver,WebElement element) {
  		  driver.switchTo().frame(element);
          }
        
        /**
         * This method is used to switch the driver from frame to main page
         * @param driver
         */
        public static void switchToMainPage(WebDriver driver) {
			driver.switchTo().defaultContent();

        }
        
        /**
         * this method is used to initialize java script executor
         * @param driver
         */
        public  void initializejse(WebDriver driver) {
        	javaScriptExecutor =(JavascriptExecutor)driver;
        }
        
        /**
         * this method is used to enter the data through java script
         * @param element
         * @param data
         */
        public  void enterDataThroughjse(WebElement element,String data,WebDriver driver) {
        	javaScriptExecutor =(JavascriptExecutor)driver;

        	javaScriptExecutor.executeScript("arguments[0].value=arguments[1]", element, data);
        }
        
        /**
         * This method used to click the data through java script
         * @param element
         */
       public void clickThroughjse(WebElement element,WebDriver driver) {
       	javaScriptExecutor =(JavascriptExecutor)driver;

    	   javaScriptExecutor.executeScript("arguments[0].click()", element);
    	   
       } 
       
       
      /**
       * this method is used to provide the url of the application through java script
       * @param url
       */
       public  void navigateAppThroughjse(String url,WebDriver driver) {
       	javaScriptExecutor =(JavascriptExecutor)driver;
       javaScriptExecutor.executeScript("window.location=arguments[0]", url);
    	   
       } 
       
       /**
        * This method is used to scroll the web page from top to bottom through java script
        * @param height
        */
       public  void scrollToSpecificHeight(WebDriver driver,String height) {
       	javaScriptExecutor =(JavascriptExecutor)driver;
         javaScriptExecutor.executeScript("window.scrollBy(0,"+height+")");
    	   
       } 
       
       /**
        * This method is used to scroll the web page from top to bottom through java script
        */
       public  void scrollTopToBottom(WebDriver driver) {
       	javaScriptExecutor =(JavascriptExecutor)driver;
       	javaScriptExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
    	   
       } 
       
       /**
        * this method is used to scroll the web page till the particular element through java script
        * @param element
        */
       public void scrollTillElement(WebDriver driver,WebElement element) {
       	javaScriptExecutor =(JavascriptExecutor)driver;

    	   javaScriptExecutor.executeScript("arguments[0].scrollIntoView()",element);
    	   
       }
       
        /**
         * This method is used to take screenshot of a web page.
         * @param fileName
         * @param driver
         * @throws IOException
         */
        public static String takeScreenShot(String fileName,WebDriver driver) throws IOException {
        	TakesScreenshot ts=(TakesScreenshot)driver;
        	File src=ts.getScreenshotAs(OutputType.FILE);
			File dst=new File("./screenshot/"+fileName+"_"+javaSpecificData.getDateTimeInFormat()+".png");
			FileUtils.copyFile(src, dst);
			return dst.getAbsolutePath();
        }
        	
        /**
         * This method is used to handle alert pop up
         * @param driver
         */
       public static void alertHandling(WebDriver driver) {
        		  Alert alrt=driver.switchTo().alert();
       			  alrt.accept();
        	  	
        	}
        	
       }
        

