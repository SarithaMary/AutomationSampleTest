package com.sample.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.sample.base.TestBase;

public class SignUpPage extends TestBase {
	// Page Factory

	@FindBy(xpath = "//a[@href='/r.php?locale=en_GB']")
	WebElement BtnSignUp;

	@FindBy(name = "firstname")
	WebElement txtFName;

	@FindBy(name = "lastname")
	WebElement txtSurName;

	@FindBy(name = "reg_email__")
	WebElement txteMail;

	@FindBy(name = "reg_email_confirmation__")
	WebElement txteMail_ReEnter;

	@FindBy(name = "reg_passwd__")
	WebElement txtNewPwd;

	@FindBy(name = "sex")
	WebElement optGender;

	@FindBy(xpath = "//button[@type='submit' and @id ='u_0_14']")
	WebElement btnCreateAccount;

	@FindBy(xpath = "//div[@class='_5633 _5634 _53ij']")
	WebElement msgPopup;

	SoftAssert softAssert = new SoftAssert();

	public SignUpPage() {
		PageFactory.initElements(dr, this);
	}

	// Methods
	public String getPageTitle() {
		return dr.getTitle();
	}

	public void signUpBtnClick() {
		BtnSignUp.click();

		/*
		 * JavascriptExecutor js = ((JavascriptExecutor) dr);
		 * js.executeScript("arguments[0].click();", BtnSignUp);
		 */
	}

	public void signUpFieldValidation(String fName, String surName, String eMail, String eMail_ReEnter, String newPwd,
			String gender, String mandatoryMsg) {
		txtFName.clear();
		txtFName.sendKeys(fName);

		txtSurName.clear();
		txtSurName.sendKeys(surName);

		txteMail.clear();
		txteMail.sendKeys(eMail);

		if (txteMail_ReEnter.isDisplayed()) {
			txteMail_ReEnter.clear();
			txteMail_ReEnter.sendKeys(eMail_ReEnter);
		}

		txtNewPwd.clear();
		txtNewPwd.sendKeys(newPwd);

		int count = dr.findElements(By.xpath("//input[@name='sex']")).size();
		for (int i = 0; i < count; i++) {
			String txtVal = dr.findElements(By.xpath("//label[@class='_58mt']")).get(i).getText();
			if (txtVal.equals(gender)) {
				dr.findElements(By.xpath("//label[@class='_58mt']/preceding-sibling::input[@name='sex']")).get(i)
						.click();
			}
		}

		btnCreateAccount.click();

		if (msgPopup.isDisplayed()) {
			softAssert.assertEquals(msgPopup.getText(), mandatoryMsg, "Mandatory Message doesn't match");
			
		}

		softAssert.assertAll();
	}

}
