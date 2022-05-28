package com.firebase.pages.homePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.firebase.base.BasePage;

public class HomePage extends BasePage {

	WebDriver driver;
	
	@FindBy(xpath="//div[@id='userNavButton']")WebElement userMenu;
	@FindBy(xpath="//a[contains(text(),'Logout')]")WebElement logoutLink;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	public String getactualTitleHomePage() {
		
		String title= getTitle();
		return title;
		
	}
	
	public void clickUserMenu() {
		mouseOver(userMenu, "UserMenu");
		clickElement(userMenu, "User Menu");
	}
	
	public void selectLogoutLink() {
		waitUntilVisible(logoutLink, "logout ");
		clickElement(logoutLink, "logout link");
		pageTimeOut();
	//	Thread.sleep(5000);
		
	}
}
