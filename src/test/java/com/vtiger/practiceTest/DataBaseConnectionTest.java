package com.vtiger.practiceTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseConnectionTest {

	public static void main(String[] args) throws SQLException {
		
		//step1:Create object for implementation class
		Driver driver=new Driver();
		
		//Step2: register the driver with jdbc
		DriverManager.registerDriver(driver);
		
		//Step3: Establish the database connection
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/SDET34L1", "root", "root");
		
		//Step4: Create Statement
		Statement statement=connection.createStatement();
		
		//Step5: Execute query
		ResultSet result=statement.executeQuery("select * from emp;");
		
		//Step6: validate(based on test cases)
		while(result.next()) {
			System.out.println(result.getString("empno"));
		}
		
		//Step7: close the window
		connection.close();
	
		
		
		
	}
}
