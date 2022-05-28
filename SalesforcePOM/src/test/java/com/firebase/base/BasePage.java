package com.firebase.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.firebase.utility.CommonUtilities;
import com.firebase.utility.Constants;
import com.firebase.utility.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static WebElement element;
	
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public static void enterText(WebElement element,String text,String objName) {
		if(element.isDisplayed()) {
			clearElement(element,objName);
			element.sendKeys(text);
			
	//		report.logTestInfo("text entered in "+ objName +" field");
		}
		else {
			
		//	report.logTestInfo( objName +"element not displayed");
		
		}
	}
	public static void clickElement(WebElement element,String objName) {
		if(element.isDisplayed()) {
			element.click();
	//		report.logTestInfo( objName +"element clicked");
			
		}
		else {
		//	report.logTestInfo(objName +"element not displayed");
			
		}
	}
	
	public void pageTimeOut() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	public static void clearElement(WebElement element,String objName) {
		if(element.isDisplayed()) {
			element.clear();
		//	report.logTestInfo(objName +"elment value is cleared");
			
		}
		else {
		//	report.logTestInfo(objName +"elment not displayed");
		}
	}
	public static WebElement elementToBeFind(By locator) {
		element = driver.findElement(locator);
		return element;
	}
	
	public static List elementsToBeFind(By locator) {
		List<WebElement> listOfElements=driver.findElements(locator);
		return listOfElements;
	}
	
	
	
	public static void waitUntilVisible(WebElement element,String objName) {
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(element));
	//	report.logTestInfo("Waiting for the "+objName +"to be visible");
		}
	public static void waitUntilvisibilityOfElementLocated(By locator,String objName) {
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	//	report.logTestInfo("Waiting for the locator "+objName +"to be visible");
		}
	
	public static void waitUntilAlertIsPresent() {
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.alertIsPresent());
	//	report.logTestInfo("Waiting for the alert to be visible");
	}
	public static void waitUntilElementToBeClickable(By locator,String objName) {
		wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	//	report.logTestInfo("Waiting until the "+objName +"to be clicked");
	}
	
	public static void mouseOver(WebElement element,String objName) {
		waitUntilVisible(element,objName);
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
	//	report.logTestInfo("Moving the mouse to the "+objName );
	}
	private static Alert switchToAlert() {
		// TODO Auto-generated method stub
		 return driver.switchTo().alert();
		 
	}
	
	public static String getTitle() {
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		String title=driver.getTitle();
		return title;
	}	
	
	public static void AcceptAlert() {
		waitUntilAlertIsPresent();
		Alert alert=switchToAlert();
		alert.accept();
	//	report.logTestInfo("Alert accepted: "+alert.getText());
	}
	public static void dismisAlert() {
		waitUntilAlertIsPresent();
		Alert alert=switchToAlert();
		alert.dismiss();
//		report.logTestInfo("Alert dismissed: "+alert.getText());
		
	}
	public static void selectByTextData(WebElement element,String text,String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByVisibleText(text);
	//	report.logTestInfo(objName+" selected "+text);
		
		
	}
	public static void selectCheckbox(WebElement element,String objName) {
			if(element.isSelected()==false) {
				element.click();
			}
	//		report.logTestInfo(objName+ " selected ");
		
		
	}
	public static void selectByIndexData(WebElement element,int index,String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByIndex(index);
//		report.logTestInfo(objName+ " selected by index "+index);
	}
	public static void selectByValueData(WebElement element,String text) {
		Select selectCity = new Select(element);
		selectCity.selectByValue(text);
//		report.logTestInfo(element+ " selected by value "+text);
	}
	
	public static String readText(WebElement element, String objName) {
		waitUntilVisible(element, objName);
		String text= element.getText();
		return text;
	}	

	public static void switchTonewWindow(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
	}
	

	
}
