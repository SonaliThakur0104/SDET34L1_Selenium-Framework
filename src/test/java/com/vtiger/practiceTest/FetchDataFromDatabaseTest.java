package com.vtiger.practiceTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FetchDataFromDatabaseTest {
	public static void main(String[] args) {
		String url=null;
		WebDriver driver=null;
		Connection connection=null;
		
			try {
			    Driver d = new Driver();
				DriverManager.registerDriver(d);
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/vtiger", "root", "root");
				Statement statement=connection.createStatement();
				ResultSet result=statement.executeQuery("select * from vtigerdata;");
				while(result.next())
				{
					url=result.getString("url"); 
					String userName=result.getString("username"); 
					String password=result.getString("password"); 
					//String timeout
				}
	            } 
			catch (SQLException e) {
				e.printStackTrace();
				e.getMessage();
				}
			finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
					e.getMessage();
				}
				
			}
			
			
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("url");
			//driver.findElement(null)
			
			
	
	}

}
