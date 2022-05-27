package com.vtiger.ProductsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericLibrary.BaseClass;
import com.sdet34l1.genericLibrary.ExcelFileData;
import com.sdet34l1.genericLibrary.WebDriverDataUtility;
import com.vtiger.pomRepository.CreateNewProductPage;
import com.vtiger.pomRepository.ProductInformationPage;
import com.vtiger.pomRepository.ProductPage;

public class CreateProductsTest extends BaseClass{
	String productName;
	ProductPage productPage;
	CreateNewProductPage createNewProductPage;
	ProductInformationPage productInformationPage;
	
	
	@Test(groups="baseclass")
	public void createProductsTest() {
		
		productName=ExcelFileData.getDataFromExcel("Products",2,1)+randomNumber;
		productPage=new ProductPage(driver);
		createNewProductPage=new CreateNewProductPage(driver);
		productInformationPage=new ProductInformationPage(driver);
		homePage.clickProducts(driver);
		productPage.createProductImg(driver);
		createNewProductPage.createProduct(productName);
		 WebDriverDataUtility.waitUntilElementVisible(productInformationPage.getProductHeaderInfo());

		javaSpecificData.assertionThroughIfCondition(productInformationPage.checkProductNameInfo(),productName,"product");
		
		
		
	}

}
