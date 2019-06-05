package com.qsp.ohrm.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMDashboardPage {

	@FindBy(id="menu_pim_addEmployee")
	WebElement addemployeeLink;
	
	@FindBy(id="menu_pim_viewEmployeeList")
	WebElement employeelistlink;
	
	
	public void clickonaddEmployee() {
		addemployeeLink.click();
	}
	
	public void clickonEmployeelist() {
		employeelistlink.click();
	}
	
			public PIMDashboardPage(WebDriver driver) {
			PageFactory.initElements(driver, this);
		}
}
