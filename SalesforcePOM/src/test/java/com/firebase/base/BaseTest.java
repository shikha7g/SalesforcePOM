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

public class BaseTest {

	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static WebElement element;
	 public static ExtentHtmlReporter htmlReporter;
	 public static ExtentReports extent;
	 
	 public static ExtentTest logger;
	 public static GenerateReports report;
	
	 @BeforeTest
	 public static void initialTestSetup() {
		report= GenerateReports.getInstance();
		report.startExtentReport();
	System.out.println("create report object");
	 }  

	
	@BeforeMethod	
	@Parameters("browser")
	public static void setUp(Method method, String browser) {
	
		report.startSingleTestReport(method.getName());
		System.out.println("Before Method started and browser name="+browser);
		getDriver(browser);
   
	//	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String url=CommonUtilities.getPropertyValue("url");
			
		goToURL(url);
	}
	
	 
	@AfterTest
	public static void finalTestTearDown() {
		report.endReport();
	}
	
	
	
	 /* name of the method:   getDriver
	 * BriefDescription  :   create firefox browser instance
	 * Arguments         :  
	 *            
	 *  createdby        :  Automation team 
	 *  created date     :5/5/22 
	 *  LastModified Date:5/5/22        
	 */
	
	
	
	public static void getDriver(String browser) {
		if (browser.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup(); //instead of hardcoding starting the server with .pom xml
			driver=new FirefoxDriver();
		
			report.logTestInfo("Firefox Driver Instance Created");
		}else if (browser.equalsIgnoreCase("chrome")){
			WebDriverManager.chromedriver().setup(); //instead of hardcoding starting the server with .pom xml
			driver=new ChromeDriver();
			report.logTestInfo("Chrome Driver Instance Created");
		}else if(browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup(); //instead of hardcoding starting the server with .pom xml
			driver=new EdgeDriver();
			report.logTestInfo("Edge Driver Instance Created");
		}
		
		
	}
	
	public static WebDriver getDriverInstance(){
		return driver;
	}
	
	public static void goToURL(String url) {
		driver.get(url);
		report.logTestInfo("Url entered is :"+url);
	}
	


	
	
	public static void closeDriver() {
		driver.close();
		report.logTestInfo("Close the driver");
	}
	
	@AfterMethod
	public static void closeAllDriver() {
		driver.quit();
		report.logTestInfo("Closed all drivers");
	}

	public static void getScreenshot(WebDriver driver, String fileName ) {
		TakesScreenshot screenCapture= (TakesScreenshot)driver;
		File sourceFile= screenCapture.getScreenshotAs(OutputType.FILE);
		String path= Constants.SCRRENSHOT_PATH+fileName;
		File destFile= new File(path);
		try {
			FileUtils.copyFile(sourceFile, destFile);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.logTestFailedWithException(e);
		}
		report.logTestInfo("Screenshot captured successfully");
	}
	

	

}
