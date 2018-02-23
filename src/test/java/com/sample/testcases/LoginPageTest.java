package com.sample.testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.apache.log4j.Logger;
import com.sample.base.TestBase;
import com.sample.pages.LoginPage;

public class LoginPageTest extends TestBase {

	SoftAssert softAssert = new SoftAssert();
	LoginPage loginPage;
	Logger log = Logger.getLogger(LoginPageTest.class);
	// HomePage homePage;

	public LoginPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		init();
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void LoginPageTitleTest() {
		log.info("----------------------TC_Login_01: Test Login Page Title----------------------");
		String title = loginPage.getPageTitle();
		softAssert.assertEquals(title, "Facebook – log in or sign up", "Login Page Title is not matched");
		softAssert.assertAll();
	}

	@Test(priority = 2)
	public void VerifyFacebookLogo() {
		log.info("----------------------TC_Login_02: Test Login Page Logo----------------------");
		boolean logo = loginPage.ValidateFacebookLogo();
		softAssert.assertTrue(logo);
		softAssert.assertAll();
	}

	@Test(priority = 3)
	public void LoginFacebook() {
		log.info("----------------------TC_Login_03: Test Login Credential----------------------");
		boolean invalidLoginPopup = loginPage.LoginFacebook(prop.getProperty("Email"), prop.getProperty("password"));

		if (invalidLoginPopup) {
			if (loginPage.ValidateInvalidLogin()) {
				softAssert.assertTrue(loginPage.ValidateInvalidLogin(),
						"Invalid credential Popup message matches with expected");
			} else
				softAssert.assertFalse(loginPage.ValidateInvalidLogin(),
						"Invalid credential Popup message doesn't match");
		}
		softAssert.assertAll();
	}

	@AfterMethod
	public void tearDown() {
		dr.quit();
	}

}
