package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(name="user_name")
	private WebElement userNameTxt;
	
	@FindBy(name="user_password")
	private WebElement passwordTxt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
    public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	} 
	
	//business library
    public void loginAction(String username,String password) {
		userNameTxt.sendKeys(username);
		passwordTxt.sendKeys(password);
		loginBtn.click();
    }
	
	
	
	
}
