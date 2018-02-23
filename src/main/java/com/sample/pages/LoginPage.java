package com.sample.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sample.base.TestBase;
public class LoginPage extends TestBase{
	// Page Factory

	@FindBy(id = "email")
	WebElement Email;

	@FindBy(id = "pass")
	WebElement password;

	@FindBy(xpath = "//input[@type='submit' and @value='Log In']")
	WebElement BtnLogin;
	
	@FindBy(xpath="//div[@class='_4rbf _53ij']")
	WebElement invalidPopup;
	
	@FindBy(xpath="//a[@href='https://www.facebook.com/']")
	WebElement facebookLogo;

	// Initializing the Page Object
	public LoginPage() {
		PageFactory.initElements(dr, this);
	}
	
	//Methods 
	public String getPageTitle(){
		return dr.getTitle();
	}
	
	public boolean ValidateFacebookLogo(){
		return facebookLogo.isDisplayed();
	}
	
	public boolean LoginFacebook(String un, String pwd){
		Email.sendKeys(un);
		password.sendKeys(pwd);
	//	BtnLogin.click();
		
		JavascriptExecutor js = ((JavascriptExecutor) dr);
		js.executeScript("arguments[0].click();", BtnLogin);
		
		return invalidPopup.isDisplayed();

	}
	
	public boolean ValidateInvalidLogin(){
		return (invalidPopup.getText()).equals("The password that you've entered is incorrect. Forgotten password?");
				
	}

}
