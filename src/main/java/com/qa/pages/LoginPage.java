package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class LoginPage extends TestBase{
	//page factory or OR
	@FindBy(xpath="//a[text()='Draggable']")
	WebElement lnDraggable;
	
	//Intitializing the page object:
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	//Actions:
	public String getTitle()
	{
		return driver.getTitle();
	}
	

}
