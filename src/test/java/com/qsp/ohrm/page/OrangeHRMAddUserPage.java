package com.qsp.ohrm.page;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qsp.ohrm.utils.DriverUtils;
import com.qsp.ohrm.utils.Log;

public class OrangeHRMAddUserPage {
	WebDriver driver ; 
	@FindBy(id="systemUser_userType")
	WebElement userRoleDropDown;
	
	@FindBy(id="systemUser_employeeName_empName")
	WebElement employeeName;
	
	@FindBy(id="systemUser_userName")
	WebElement userName;
	
	@FindBy(id="systemUser_status")
	WebElement userStatus;
	
	@FindBy(id="systemUser_password")
	WebElement userPassword;
	
	@FindBy(id="systemUser_confirmPassword")
	WebElement confirmUserPassword;
	
	@FindBy(id="btnSave")
	WebElement addUserSaveButton;
	
	@FindBy(id="systemUser_password_strength_meter")
	WebElement passwordStrength;
	
	@FindBy(xpath="//div[@class='message success fadable'")
	WebElement toastMessage;
	
	@FindBy(id="btnAdd")
	WebElement addbtn;
	
	@FindBy(id="menu_admin_viewAdminModule")
	WebElement adminele;
	
	@FindBy(id="menu_admin_UserManagement")
	WebElement usrmgmt;
	
	@FindBy(id="menu_admin_viewSystemUsers")
	WebElement users;
	
	@FindBy(id="searchSystemUser_userName")
	WebElement usertbox;
	
	@FindBy(id="searchBtn")
	WebElement searchbtn;
	
	
	
	
	public void validateToastMessage(){
		Log.info("validating toast message.........");
		FluentWait<WebElement>  wait =  new FluentWait<WebElement>(driver.findElement(By.xpath("//div[@class='message success fadable']")))
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(1))
				.ignoring(NoSuchElementException.class)
				.ignoring(Exception.class);
		
		Function<WebElement, Boolean> function = new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement toast) {
				boolean flag = false;
				if(toast.getText().contains("Successfully")){
					Log.info("Found success message " + toast.getText());
					flag= true;
				}else{
					Log.info("Waiting for toast message ");
					flag= false;
				}
				return flag;
			}
		};
			
		wait.until(function);
	}
	 public void validateToastUsindJS(){
		 WebElement scriptElement = driver.findElement(By.tagName("script"));
		  String scriptText = ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML", scriptElement).toString();
		  Log.info(scriptText + " ^^^^^^^^^^^^");
	 }
	public void selectUserRoleByText(String text){
		Log.info("selecting value from dropdown");
		DriverUtils.selectDropDownItem(userRoleDropDown, "byVisibleText", text);
	}
	public void selectRoleByIndex(String type, String value)
	{
		Log.info("selecting the Status" + value);
		DriverUtils.selectDropDownItem(userStatus, type, value);
	}
	
	public void enterEmployeeName(String name){
		Log.info("Enter User NAme" + name);
		employeeName.sendKeys(name);
	}
	
	public void enterUserName(String name){
		Log.info("Enter User NAme" + name);
		userName.sendKeys(name);
	}
	
	public void selectStatus(String type, String value)
	{
		Log.info("selecting the Status" + value);
		DriverUtils.selectDropDownItem(userStatus, type, value);
	}
	
	
	public void enterPassword(String name){
		Log.info("Enter Password" + name);
		userPassword.sendKeys(name);
	//	DriverUtils.waitForChangeInText(passwordStrength, "Strong");
	}
	
	public void confirmPassword(String name){
		Log.info("Confirm password" + name);
		confirmUserPassword.sendKeys(name);
	}
	
	public void clickOnSaveUserButton()
	{
		Log.info("Saving the user record " );
		addUserSaveButton.click();
	}
	
	
	public OrangeHRMAddUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	public void clickAddUser()
	{
		DriverUtils.getVisibleElement(driver, addbtn);
		addbtn.click();
	}

	public void clickadminUseradmin()
	{
		Log.info("Mousehover on Adminstrator, UserAdminstrator");
		DriverUtils.getVisibleElement(driver, adminele);
		DriverUtils.moveMouse(adminele);
		DriverUtils.getVisibleElement(driver, usrmgmt);
		DriverUtils.moveMouse(usrmgmt);
	}
	public void clickusers()
	{
		Log.info("Click on Users link");
		DriverUtils.getVisibleElement(driver, users);
		users.click();
	}
	public void enterValueToSearchUser(String name){
		Log.info("Enter User Name for searching user" + name);
		DriverUtils.getVisibleElement(driver, usertbox);
		usertbox.sendKeys(name);
	}
	public void clickSearchButton()
	{
		Log.info("Click on Search Button");
		DriverUtils.getClickableElement(driver, searchbtn);
		searchbtn.click();
	}

}
