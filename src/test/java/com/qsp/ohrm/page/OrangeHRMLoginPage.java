package com.qsp.ohrm.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qsp.ohrm.utils.Log;


public class OrangeHRMLoginPage {

	//Log log = null;
	@FindBy(id="txtUsername")
	WebElement userNameTextBox;
	
	@FindBy(id="txtPassword")
	WebElement passwordTextBox;
	
	@FindBy(id="btnLogin")
	WebElement loginButton;
	
	@FindBy(xpath="//h1")
	WebElement dashboard;
	
	@FindBy(id="spanMessage")
	WebElement invalidErrorMsg;
	
	
	
	
	public void validateErrorMessage(){
		String actualError = invalidErrorMsg.getText();
		
		Assert.assertEquals("Invalid credentials", actualError);
		
	}
	public void enterUserName(String name){
		//Log.info("****Entering UserName " + name);
		userNameTextBox.sendKeys(name);
	}
	
	public void enterPassword(String pwd){
		Log.info("****Entering User Password " + pwd);
		passwordTextBox.sendKeys(pwd);
	}
	
	public void clickOnLoginButton(){
		Log.info("****Clicking on Login Button");
		loginButton.click();
		
	}
	public void loginToOrangeHRM(WebDriver driver, String name,String pwd){
		enterUserName(name);
		enterPassword(pwd);
		clickOnLoginButton();
		try{
			if(dashboard.isDisplayed())
			{
				Log.pass("Login Success");
			}
		}catch(Exception ex){
			Log.writeToText(ex.toString());
//			Log.writeToFailFile(ex.toString());
			Log.fail("Login failed with " + name + " and " + pwd);
			
			
		}
		//Assert.assertTrue(dashboard.isDisplayed());
		//Log.pass("Success login");
	}
	
	public OrangeHRMLoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
}
