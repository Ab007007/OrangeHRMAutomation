
package com.qsp.ohrm.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrangeHRMAddEmployeePage {

	@FindBy(id = "menu_pim_viewPimModule")
	WebElement pim;

	@FindBy(id = "menu_pim_addEmployee")
	WebElement AddEmployee;

	@FindBy(id = "firstName")
	WebElement firstname;

	@FindBy(id = "lastName")
	WebElement lastname;

	@FindBy(id = "btnSave")
	WebElement save;
	
	@FindBy(id="employeeId")
	WebElement empid;

	public void clickonpim() {
		pim.click();
	}

	public void clickAddEmployee() {
		AddEmployee.click();
	}

	public void Enterfirstname(String FirstName) {
		firstname.sendKeys(FirstName);
	}

	public void Enterlastname(String LastName) {
		lastname.sendKeys(LastName);
	}
	public void EnteremployeeID(String employeeID) {
		empid.clear();
		empid.sendKeys(employeeID);
	}

	public void clickonsave() {
		save.click();

	}

	public OrangeHRMAddEmployeePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
