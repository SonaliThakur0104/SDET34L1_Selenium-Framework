package com.sdet34l1.genericLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;
/**
 * this class contains all common actions related to datbase.
 * @author Sonali
 *
 */

public class DataBaseFileClass {
	static Connection connection;
	static Statement statement;
	/**
	 * This method is used to open the database connection and initialize the connectiom,satement
	 * @param DBUrl
	 * @param DBUsername
	 * @param DBPassword
	 * @throws SQLException
	 */

	public static void openDBConnection(String DBUrl,String DBUsername,String DBPassword) throws SQLException {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		connection=DriverManager.getConnection(DBUrl,DBUsername,DBPassword);
		statement=connection.createStatement();
	}


	/**
	 * This method is used to fetch the data from database/to do the DQL actions on database 
	 * @param query
	 * @param columnName
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String>  getDataFromDatabase(String query,String columnName) throws SQLException {
		ArrayList<String> list=new ArrayList();
		ResultSet result=statement.executeQuery(query);
		while(result.next()){
			list.add(result.getString(columnName));

		}
		return list;
	}
	
	/**
	 * This method is used to validate the data whether it is present in database or not
	 * @param query
	 * @param columnName
	 * @param expectedData
	 * @return
	 * @throws SQLException
	 */

	public static boolean validateDataInDatabase(String query,String columnName,String expectedData) throws SQLException {
		ArrayList<String> list=getDataFromDatabase(query,columnName);
		boolean flag =false;
		for(String actualData:list) {
			if(actualData.equals(expectedData)) {
				flag=true;
				break;
			}
		} 
		return flag;
     }
	
	/**
	 * This method is used to set the data into database
	 * @param query
	 * @throws SQLException
	 */
	public static void setDataInDataBase(String query) throws SQLException {
		int result=statement.executeUpdate(query);
		if(result>=1) {
			System.out.println("Data entered /modified successfully");
		}
		}
  
	
	
	/**
	 * This method is used to close the database
	 */
	public static void closeDBConnection() {
		
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		    System.out.println("We got exception while closing the database");
		}
		}
	}