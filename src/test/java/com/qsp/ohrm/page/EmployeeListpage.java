package com.qsp.ohrm.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeeListpage {

	@FindBy(id="empsearch_employee_name_empName")
	WebElement searchbyemployeename;
	
	@FindBy(id="empsearch_id")
	WebElement empsearch_id;
	
	@FindBy(id="searchBtn")
	WebElement searchBtn;
	
	@FindBy(xpath="((//tr)[2]//td)[3]")
	WebElement verifyfirstname;
	
	@FindBy(xpath="((//tr)[2]//td)[4]")
	WebElement verifylastname;
	
	public void empsearch(String employeename, String empid) {
		searchbyemployeename.sendKeys(employeename);
		empsearch_id.sendKeys(empid);
	}
	
	public void clickonsearchBtn()
	{
		searchBtn.click();
	}
	
	public String VerifyEmployee() {
		String temp_firstname = verifyfirstname.getText();
		String temp_lastname = verifylastname.getText();
		return temp_firstname+" "+temp_lastname;
	}
	public EmployeeListpage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	
}
