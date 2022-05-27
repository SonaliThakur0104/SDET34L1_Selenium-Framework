package com.vtiger.practiceTest;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Create_OrganizationTest{ 
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		Random r=new Random();
	    int num=r.nextInt(1000);
		driver.findElement(By.name("user_name")).sendKeys("admin",Keys.TAB,"root",Keys.ENTER);
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys("TestYantra"+num);
		driver.findElement(By.name("button")).click();
		
		String actualname=driver.findElement(By.xpath("//span[text()='TestYantra']")).getText();
		System.out.println(actualname);
		if(actualname.contains("TestYantra")) {
			System.out.println("Organization name created successfully");
		}
		else {
		System.out.println("Organization name not created");
	    }
		WebElement logout=driver.findElement(By.xpath("//span[text()='Administrator']/../following-sibling::td[1]"));
		Actions act=new Actions(driver);
    	act.moveToElement(logout).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	
		driver.quit();
		

	}
}



