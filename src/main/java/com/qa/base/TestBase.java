package com.qa.base;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.qa.util.TestUtil;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	protected static ExtentReports extent;
	protected ExtentTest pNode;
	protected WebDriverWait wait;
	protected Assertion assertions;

	TestUtil var = new TestUtil();

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("./src/main/java/com/qa/config/config.properties");
			prop.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String strBrowserName = prop.getProperty("browser");
		if (strBrowserName.equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver", "d:/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (strBrowserName.equalsIgnoreCase("ie")) {
			// System.setProperty("webdriver.ie.driver", "d:/chromedriver.exe");
			// driver = new ChromeDriver();
		} else {
			// System.setProperty("webdriver.firefox.driver",
			// "d:/chromedriver.exe");
			// driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.page_load_timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
	}

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext suite) throws Exception {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("Reports/extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Application URL", prop.getProperty("url"));
		extent.setSystemInfo("Browser", prop.getProperty("browser"));
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClassRun() {
		pNode = extent.createTest(getClass().getSimpleName());
		Markup m = MarkupHelper.createLabel("Setup for Test: " + getClass().getSimpleName(), ExtentColor.BLUE);
		pNode.info(m);
	}

	@BeforeTest(alwaysRun = true)
	public void beforeTest() {

	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method) {
		initialization();
		System.out.print("START TEST: " + method.getName() + "\n");
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.print(result.getName() + ": Executed Successfully");
			 pNode.pass(result.getName() + ": Test Case ExecutedSuccessfully!");
		} 
		if (result.getStatus() == ITestResult.FAILURE) {
			System.out.print(result.getName() + ": Execution Failed");
			String code = "Method: " + result.getName() + "\n" + "Reason: " + result.getThrowable().toString();
			Markup m = MarkupHelper.createCodeBlock(code);
			 pNode.fail(m);
		} 
		
		
		/*else if (result.getStatus() == ITestResult.FAILURE) {
			System.out.print(result.getName() + ": Execution Failed");
			String code = "Method: " + result.getName() + "\n" + "Reason: " + result.getThrowable().toString();
			Markup m = MarkupHelper.createCodeBlock(code);
			// pNode.fail(m);
		} else if (result.getStatus() == ITestResult.SKIP) {
			System.out.print(result.getName() + ": Execution Skipped");
			// pNode.skip(result.getName() + ":Test Case Executed Skipped!");
		}*/

		extent.flush();
		System.out.print("\nEND TEST: " + result.getName());
	}

	public void afterTest() {
		// todo write your code here
	}

	@AfterClass(alwaysRun = true)
	public void afterClassRun() {
		// Quit the driver
		driver.close();
		driver = null;
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		// todo write your code here
	}

	protected void markSetupAsFailure(Exception e) {
		pNode.fail("Exception: " + e.toString());
		Assert.fail(e.getMessage());
	}

	public void markTestAsFailure(Exception e, ExtentTest t1) {
		e.printStackTrace();
		t1.fail("Test Case Failed due to Exception");
		t1.error(e);
		Assert.fail(e.getMessage());
	}

}
