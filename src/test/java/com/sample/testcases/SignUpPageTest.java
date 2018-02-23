package com.sample.testcases;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import java.util.ArrayList;
import java.util.Iterator;

import com.sample.base.TestBase;
import com.sample.pages.LoginPage;
import com.sample.pages.SignUpPage;
import com.sample.utils.TestUtil;

public class SignUpPageTest extends TestBase {
	LoginPage loginPage;
	SignUpPage signUpPage;
	TestUtil testUtil; 
	SoftAssert softAssert = new SoftAssert();
	Logger log = Logger.getLogger(SignUpPageTest.class);
	public SignUpPageTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		init();
		loginPage = new LoginPage();
		signUpPage = new SignUpPage();
		testUtil = new TestUtil();
		boolean invalidLogin = loginPage.LoginFacebook(prop.getProperty("Email"), prop.getProperty("password"));

		if (invalidLogin) {
			signUpPage.signUpBtnClick();
		}
	}

	@Test(priority = 1)
	public void SignUpPageTitleTest() {
		log.info("----------------------TC_SignUp_01: Test SignUp Page Title----------------------");
		String title = signUpPage.getPageTitle();
		softAssert.assertEquals(title, "Sign up for Facebook | Facebook", "SignUp Page Title is not matched");
		
		softAssert.assertAll();
	}
	
	@DataProvider
	public Iterator<Object[]> getExcelData(){
		ArrayList<Object[]> data = TestUtil.getTestDatafromXL("RegData");
		return data.iterator();
	}
	
	@Test (priority=2, dataProvider="getExcelData")
	public void registerFacebook(String fName, String surName, String eMail,String eMail_ReEnter, String newPwd, String gender, String mandatoryMsg){
		log.info("----------------------TC_SignUp_02: Test SignUp Page Required Field Validation----------------------");
		signUpPage.signUpFieldValidation(fName, surName, eMail, eMail_ReEnter, newPwd, gender, mandatoryMsg);
	}

	@AfterMethod
	public void tearDown() {
		dr.quit();
	}
}
