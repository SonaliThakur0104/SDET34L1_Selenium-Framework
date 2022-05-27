package com.sdet34l1.genericLibrary;

import org.openqa.selenium.WebDriver;

import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;

public class GetterAndSetterForListener {
	
	private static GetterAndSetterForListener instance;
	
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
	public ExcelFileData excelFileData;
	public PropertyFileData propertyFileData;
	
	public GetterAndSetterForListener() {}
	
	public JavaSpecificData getJavaSpecificData() {
		return javaSpecificData;
	}
	public void setJavaSpecificData(JavaSpecificData javaSpecificData) {
		this.javaSpecificData = javaSpecificData;
	}
	public LoginPage getLoginPage() {
		return loginPage;
	}
	public void setLoginPage(LoginPage loginPage) {
		this.loginPage = loginPage;
	}
	public HomePage getHomePage() {
		return homePage;
	}
	public void setHomePage(HomePage homePage) {
		this.homePage = homePage;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public long getLongTimeout() {
		return longTimeout;
	}
	public void setLongTimeout(long longTimeout) {
		this.longTimeout = longTimeout;
	}
	public WebDriverDataUtility getWebDriverDataUtility() {
		return webDriverDataUtility;
	}
	public void setWebDriverDataUtility(WebDriverDataUtility webDriverDataUtility) {
		this.webDriverDataUtility = webDriverDataUtility;
	}
	public ExcelFileData getExcelFileData() {
		return excelFileData;
	}
	public void setExcelFileData(ExcelFileData excelFileData) {
		this.excelFileData = excelFileData;
	}
	public PropertyFileData getPropertyFileData() {
		return propertyFileData;
	}
	public void setPropertyFileData(PropertyFileData propertyFileData) {
		this.propertyFileData = propertyFileData;
	}
    
	public static GetterAndSetterForListener getInstance() {
		if(instance==null) {
			instance=new GetterAndSetterForListener();
		}
		return instance;
	}
	
	public static void setInstance(GetterAndSetterForListener instance) {
		GetterAndSetterForListener.instance = instance;
	}
	
	

	

}
