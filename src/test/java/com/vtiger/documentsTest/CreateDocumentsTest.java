package com.vtiger.documentsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.CreateNewDocumentPage;
import com.vtiger.pomRepository.DocumentInfoPage;
import com.vtiger.pomRepository.DocumentsPage;

public class CreateDocumentsTest extends BaseClass {
	String documentTitle;
	String documentPath;
	String documentDescription;
	DocumentsPage documentsPage;
	CreateNewDocumentPage createNewDocumentPage;
	DocumentInfoPage documentInfoPage;

	@Test(groups="sanity")
	public void createDocumentsTest() {


		documentTitle=ExcelFileData.getDataFromExcel("Documents",2,1)+randomNumber;
		documentPath=ExcelFileData.getDataFromExcel("Documents",2,2);
		documentDescription=ExcelFileData.getDataFromExcel("Documents",2,3);

		documentsPage=new DocumentsPage(driver);
	    createNewDocumentPage=new CreateNewDocumentPage(driver);
		documentInfoPage=new DocumentInfoPage(driver);

		homePage.clickDocuments(driver);
		documentsPage.clickOnDocumentImg();
		createNewDocumentPage.createDocument(documentTitle);	
		createNewDocumentPage.documentDescription(driver, documentDescription);
		WebDriverDataUtility.switchToMainPage(driver);
		createNewDocumentPage.clickOnBoldAndIclined();
		createNewDocumentPage.createDocumentPath(documentPath);
		 WebDriverDataUtility.waitUntilElementVisible(documentInfoPage.getDocumentHeaderInfo());

		javaSpecificData.assertionThroughIfCondition(documentInfoPage.checkDocumentTitleInfo(driver),documentTitle,"document");
		javaSpecificData.assertionThroughIfCondition(documentInfoPage.checkDocumentNotesInfo(driver),documentPath,"document");
		javaSpecificData.assertionThroughIfCondition(documentInfoPage.checkDocumentFileNameInfo(driver),documentDescription,"document");


	}

}
