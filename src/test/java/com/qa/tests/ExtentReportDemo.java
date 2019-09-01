package com.qa.tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportDemo {
	@BeforeSuite
	@BeforeClass
	@BeforeTest
	@BeforeMethod
	
	@Test
	public void login() throws IOException
	{
		
		ExtentHtmlReporter reporter =  new ExtentHtmlReporter("./Reports/learn_automation.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		//it is not necessary to create multiple @Test annotation to create nultiple test in extent report
		ExtentTest logger = extent.createTest("TC_001");
		logger.log(Status.INFO, "Login to amazon");
		
		ExtentTest logger2 = extent.createTest("TC_002");
		logger2.log(Status.FAIL, "Title verified");
		//logger2.addScreenCaptureFromPath("C:\\Users\\home\\Desktop\\jini.jpg");
		logger2.fail("failed because of exception", MediaEntityBuilder.createScreenCaptureFromPath("C:\\Users\\home\\Desktop\\jini.jpg").build());
		
		extent.flush();
				
		System.out.println("Login to amzon");
	}

}
