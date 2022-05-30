package com.sdet34l1.genericLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.WebElement;

/**
 * This class contains only Java specific reusable methods
 * @author Sonali
 * 
 * Main Java class library
 *
 */


public class JavaSpecificData {
	
	/**
	 * This method is used to convert String value to long datatype
	 * 
	 */
	public long stringToLong(String value)
	{
	return Long.parseLong(value);
	}
	
	/**
	 * This method is used to get the random number
	 */
	
	public int getRandomNumber(int limit) 
	{
	Random randomNumber = new Random();
    return randomNumber.nextInt(limit);
	}
	
	/**
	 * This method is used to print the message
	 * @param message
	 */
	public void printStatement(String message) {
		System.out.println(message);
	}
	
	
	/**
	 * validating the datas if actual contains expected or not
	 * @param actualResult
	 * @param expectedResult
	 * @param testCaseName
	 */
	public void assertionThroughIfCondition(String actualResult,String expectedResult,String testCaseName) {
		  if(actualResult.equals(expectedResult))
	        {        
	        	 System.out.println(testCaseName+ " created successfully");
	        	 System.out.println("TC Passed");
	        }
	}
	
	/**
	 * this method is used for custom wait
	 * @param element
	 * @param pollingTime
	 * @param duration
	 */
	public void customWait(WebElement element,long pollingTime,int duration) {
		int count=0;
		while(count<=duration)
		try {
			element.click();
			break;
		}
		catch(Exception e) {
			try {
				Thread.sleep(pollingTime);
				count++;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	/**
	 * This method is used to fetch the data and time.
	 * @return
	 */
	public String getDateTimeInFormat() {
		return new SimpleDateFormat("MM_dd_yyyy_HH_mm_sss").format(new Date());
		
	}


}
