package com.sdet34l1.genericLibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains only Property File specific common methods.
 * @author Sonali

 *This is propertyfile class
 *

 */

public class PropertyFileData {
	
	static Properties property;
	
	/**
	 * This method is used to open the property file
	 * @throws IOException 
	 * 
	 */
	public static void fetchDataFromPropertyFile(String filepath) throws IOException {
		FileInputStream fis = new FileInputStream(filepath);
		property = new Properties();
		property.load(fis);
		
	}
	
	/**
	 * This method is used to fetch the data from Property File
	 * 
	 */
	
     public static String getDataFromPropertyFile(String key) {
    	 String value=property.getProperty(key);
    	 return value;
     }
}
