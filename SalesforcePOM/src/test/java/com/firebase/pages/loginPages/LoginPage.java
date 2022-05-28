package com.firebase.pages.loginPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.firebase.base.BasePage;

public class LoginPage extends BasePage{

	WebDriver driver;
	
	@FindBy(id="username")WebElement userName;
	@FindBy(id="password")WebElement password;
	@FindBy(id="Login")WebElement loginButton;
	@FindBy(id="error")WebElement errorElement;
	@FindBy(xpath="//input[@id='rememberUn']")WebElement rememberMeChkBox;
	@FindBy(xpath="//div[@id='userNavButton']")WebElement userMenu;
	@FindBy(xpath="//a[contains(text(),'Logout')]")WebElement logoutLink;
	@FindBy(id="idcard-identity")WebElement userNameField;
	@FindBy(id="forgot_password_link")WebElement forgotPassword;
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String username) {
		waitUntilVisible(userName,"user name");
		clearElement(userName, "UserName field");
		enterText(userName, username, "User Name field");
	}
	
	public void enterPassword(String passWord) {
		clearElement(password, "Password field");
		enterText(password, passWord, "Password field");
	}
	
	
	
	public void clickLogin() {
		clickElement(loginButton, "Login button");
	}
	
	public void selectChkBox() {
		if(rememberMeChkBox.isSelected()== false){
			clickElement(rememberMeChkBox, "Check box");
		}
	}
	public String getErrorMsg() {
		waitUntilVisible(errorElement, "Error Msg");
		String actualMsg= errorElement.getText();
		return actualMsg;
	}
	
	public void login(String username, String password) {
		enterUserName(username);
		enterPassword(password);
		clickLogin();
		
	}
	
	public String loginWithoutPassword(String username, String password)  {
		enterUserName(username);
		enterPassword(password);
		
		clickLogin();
		String errorMsg= getErrorMsg();
		return errorMsg;
		
	}
	
	public void loginWithRemBoxSelect(String username, String password) {
		enterUserName(username);
		enterPassword(password);
		selectChkBox();
		clickLogin();
		pageTimeOut();
				
	}
	
	public String getUserName() {
		String actualUserName= readText(userNameField, "User Name");
		return actualUserName;
	}
	
	public String clickForgotPassword() {
		clickElement(forgotPassword, "Forgot Password");
		pageTimeOut();
		String actualTitle=getTitle();
		return actualTitle;
		
	}
}
