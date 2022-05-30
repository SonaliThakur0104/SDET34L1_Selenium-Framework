package com.sdet34l1.genericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class contains excel related common data
 * @author Sonali
 *This excel class is updated..... ..30 may
 */

public class ExcelFileData {
	static Workbook wb;
	/**
	 * This method is used to open the excel file
	 * @param filePath
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static void openExcel(String filePath) throws EncryptedDocumentException, IOException {
		
		FileInputStream fis1 = new FileInputStream(filePath);
		wb = WorkbookFactory.create(fis1);
	}
	
	/**
	 * This method is used to fetch the data from excel sheet
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 */
	public static String getDataFromExcel(String sheetName,int rowNum,int cellNum) {
		String data=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
		return data;

	}
	
	/**
	 * this method is used to get multiple data from excel
	 * @param sheetName
	 * @return
	 */
	public static Object[][] getMultipleDataFromExcel(String sheetName){
		
		Sheet sh=wb.getSheet(sheetName);
		Object [][] arr=new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
		for(int i=0; i<sh.getLastRowNum(); i++) 
		{
			for(int j=0; j<sh.getRow(i).getLastCellNum(); j++) 
			{
				arr[i][j]=sh.getRow(i+1).getCell(j).getStringCellValue();
		    }
		}
		return arr;
	}
	
	/**
	 * This method is used to set the data to excel
	 */
	public static void setDataIntoExcel(String sheetName, int rowNum, int cellNum, String message) {
		wb.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(message);
	}
	
	/**
	 * this method is used to write the data into excel
	 * @param saveFilePath
	 */
	public static void writeDataIntoExcel(String saveFilePath) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(saveFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This method is used to close the workbook.
	 */
	public static void closeWorkbook() {
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
