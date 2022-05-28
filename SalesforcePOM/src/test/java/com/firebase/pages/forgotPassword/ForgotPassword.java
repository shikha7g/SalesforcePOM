package com.firebase.pages.forgotPassword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.firebase.base.BasePage;

public class ForgotPassword extends BasePage {

	WebDriver driver;
	
	@FindBy(id="un")WebElement userName;
	@FindBy(id="continue")WebElement continueButton;
	@FindBy(xpath="//p[contains(text(),'We’ve sent you an email with a link to')]")WebElement infoMsg;
	
	public ForgotPassword(WebDriver driver) {
		super(driver);
	}
	
	public void enterUsername(String username) {
		enterText(userName, username, "User Name");
	}
	
	public String clickContinueButton() {
		clickElement(continueButton, "Continue Button");
		waitUntilVisible(infoMsg, "Message");
		String msg= readText(infoMsg, "Message");
		return msg;
		
	}
}
