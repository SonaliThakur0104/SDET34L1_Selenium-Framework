package com.vtiger.practiceTest;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.IconstantPathUtility;
import com.sdet34l1.genericLibrary.JavaSpecificData;
import com.sdet34l1.genericLibrary.PropertyFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.CreateNewLeadsPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LeadsInfoPage;
import com.vtiger.pomRepository.LeadsPage;
import com.vtiger.pomRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Lead_TC_61Test extends BaseClass {
	
	@Test
	public void leadTc61Test() {
		String leadFirstName=ExcelFileData.getDataFromExcel("Lead",1,1);
		String leadLastName=ExcelFileData.getDataFromExcel("Lead",1,2)+randomNumber;
		String leadCompanyName=ExcelFileData.getDataFromExcel("Lead",1,3)+randomNumber;

		String leadFirstName1=ExcelFileData.getDataFromExcel("Lead",3,1);
		String leadLastName1=ExcelFileData.getDataFromExcel("Lead",3,2)+randomNumber;
		String leadCompanyName1=ExcelFileData.getDataFromExcel("Lead",3,3)+randomNumber;



		 LeadsPage leadsPage=new LeadsPage(driver);
    		CreateNewLeadsPage createNewLeadsPage=new CreateNewLeadsPage(driver);
			LeadsInfoPage leadsInfoPage=new LeadsInfoPage(driver);
			homePage.clickLeads(driver);
			leadsPage.clickCreateLeadsImg();
			createNewLeadsPage.createLead(leadFirstName, leadLastName, leadCompanyName);
			leadsInfoPage.clickOnLeadsLink(driver);
			leadsPage.clickCreateLeadsImg();
			createNewLeadsPage.createLead(leadFirstName1, leadLastName1, leadCompanyName1);
			leadsInfoPage.clickOnLeadsLink(driver);
			leadsPage.clickCheckBoxAndDelete();
			WebDriverDataUtility.alertHandling(driver);
			javaSpecificData.printStatement("Tc Passed");
	}

	}

