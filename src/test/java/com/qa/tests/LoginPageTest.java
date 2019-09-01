package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	
	public LoginPageTest()
	{
		super();
	}
/*	@BeforeMethod
	public void setup()
	{
		//initialization();
		loginPage = new LoginPage();
	}*/
	@Test
	public void verifyLoginPageTitle()
	{
		loginPage = new LoginPage();
		String title = loginPage.getTitle();
		Assert.assertEquals(title, "jQuery UI1");

	}
	@Test
	public void verifyLoginPageTitle1()
	{
		loginPage = new LoginPage();
		String title = loginPage.getTitle();
		Assert.assertEquals(title, "jQuery UI1");

	}
	/*@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}*/

}
