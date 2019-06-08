package com.qsp.ohrm.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qsp.ohrm.page.EmployeeListpage;
import com.qsp.ohrm.page.OrangeDashboardPage;

import com.qsp.ohrm.page.OrangeHRMAddEmployeePage;

import com.qsp.ohrm.page.OrangeHRMAddUserPage;

import com.qsp.ohrm.page.OrangeHRMLoginPage;
import com.qsp.ohrm.page.PIMDashboardPage;
import com.qsp.ohrm.utils.ConfigFileReader;
import com.qsp.ohrm.utils.DriverUtils;
import com.qsp.ohrm.utils.ExtentReportFactory;
import com.qsp.ohrm.utils.Log;
import com.qsp.ohrm.utils.OrangeHRMUtils;
import com.qsp.ohrm.utils.Screenshots;
import com.sun.xml.internal.ws.wsdl.ActionBasedOperationSignature;

public class ScriptDevelopment extends BaseTest{

	OrangeHRMLoginPage oLoginpage= null;
	OrangeDashboardPage odp = null;

	OrangeHRMAddEmployeePage oae = null;
	ConfigFileReader filereader= new ConfigFileReader();
	PIMDashboardPage PIMpage = null;
	EmployeeListpage emplist=null;

	OrangeHRMAddUserPage Adduserpage=null;

	
	@BeforeClass
 	public void preConfig(){
 		Log.startReport("setup");
 		
		driver = DriverUtils.getWebDriver();
		oLoginpage = new OrangeHRMLoginPage(driver);
		odp = new OrangeDashboardPage(driver);

		oae = new OrangeHRMAddEmployeePage(driver);
		PIMpage = new PIMDashboardPage(driver);
		emplist = new EmployeeListpage(driver);
		Adduserpage=new OrangeHRMAddUserPage(driver);
 	}
	
	@Test(priority=1)
	public void validateLoginTest() throws Exception{

		Log.startReport(DriverUtils.getMethodName());
		OrangeHRMUtils.launchApp(driver, config.getApplicationUrl());
		Log.pass("Login to Applicaiton Success");
		Log.pass("login with valid credentials");
		oLoginpage.loginToOrangeHRM(driver, config.getUserName(), config.getPasswordName());
		Log.pass("Clicking on PIM");
		oae.clickonpim();
		Log.pass("Clicking on addEmployee");
		oae.clickAddEmployee();
		Log.pass("creating a employee");
		oae.Enterfirstname(filereader.getFirstName());
		oae.Enterlastname(filereader.getLastName());
		oae.EnteremployeeID(filereader.getemployeeID());
		oae.clickonsave();
		PIMpage.clickonEmployeelist();
		emplist.empsearch(filereader.getFirstName()+" "+filereader.getLastName(),filereader.getemployeeID());
		emplist.clickonsearchBtn();
		Thread.sleep(3000);
		String expempname = emplist.VerifyEmployee();
		System.out.println(expempname);
		Assert.assertEquals(filereader.getFirstName()+" "+filereader.getLastName(), expempname,"Employee Does not exit");
		

		
		
		Log.info("--Completeds Executing Test ");
 		//Log.endReport("validateLoginTest");

		oLoginpage.loginToOrangeHRM(driver, "Admin", "admin123");
		Adduserpage.clickadminUseradmin();
		Adduserpage.clickusers();
		Adduserpage.clickAddUser();
		Adduserpage.selectRoleByIndex("byIndex", "1");
		Adduserpage.enterEmployeeName("Rajesh Krishna");
		Adduserpage.enterUserName("RajeshK1");
		Adduserpage.selectStatus("byIndex", "0");
		Adduserpage.enterPassword("April@2019@2018@2017");
		Adduserpage.confirmPassword("April@2019@2018@2017");
		Adduserpage.clickOnSaveUserButton();

	}
	@Test(priority=2)
	public void searchUsers()
	{
		Log.startReport(DriverUtils.getMethodName());
		OrangeHRMUtils.launchApp(driver, config.getApplicationUrl());
		Log.pass("Login to Applicaiton Success");
		Log.info("--Completeds Executing Test - validateLoginTest");
		oLoginpage.loginToOrangeHRM(driver, "Admin", "admin123");
		Adduserpage.clickadminUseradmin();
		Adduserpage.clickusers();
		Adduserpage.enterValueToSearchUser("RajeshK");
		Adduserpage.clickSearchButton();
	}
	
	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		Log.info("inside after method with " +testResult.getStatus());
		Log.info(String.valueOf(ITestResult.FAILURE));
		if (testResult.getStatus() == ITestResult.FAILURE) {
			String path = Screenshots.takeScreenshot(driver, testResult.getName()+ExtentReportFactory.getCurrentDateAndTime());
			Log.info("Path " + path);
			Log.ssPath.add(path);
			Log.attachScreenShot(path);
		}
		
		Log.endReport();
	}
	
	
	@AfterClass
	public void tearDown(){
		try{
			ExtentReportFactory.sendReportByGMail();
		}catch(Exception ex){
			System.out.println(ex);
		}finally{
			if(driver!=null){
					driver.close();
					driver = null;
			}
		}
	}
}
