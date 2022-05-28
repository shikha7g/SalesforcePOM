package login.firebase.tests;


import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.firebase.base.BaseTest;
import com.firebase.pages.forgotPassword.ForgotPassword;
import com.firebase.pages.homePages.HomePage;
import com.firebase.pages.loginPages.LoginPage;
import com.firebase.utility.CommonUtilities;
import com.firebase.utility.Constants;

//@Listeners(com.firebase.utility.GenerateReportListener.class)
public class AutomationScript extends BaseTest{

	@Test
	public static void testCase1()  {
		
		LoginPage loginPage= new LoginPage(driver);
		String user= CommonUtilities.getPropertyValue(Constants.USER_ID);
		String password="";
		String actualErrorMsg=loginPage.loginWithoutPassword(user, password);
		String expectedErrorMsg=Constants.LOGIN_ERROR_MESSAGE;
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	
	}
			
		@Test
		public static void testCase2() {
		
		LoginPage login= new LoginPage(driver);
		String user= CommonUtilities.getPropertyValue(Constants.USER_ID);
		String password= CommonUtilities.getPropertyValue(Constants.PASSWORD);
		String expectedTitle= CommonUtilities.getPropertyValue(Constants.HOME_PAGE_TITLE);
		login.login(user,password);
		HomePage homePage= new HomePage(driver);
		String actualTitle=homePage.getactualTitleHomePage();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
		
	@Test
	public static void testCase3() throws InterruptedException {
			LoginPage loginPage= new LoginPage(driver);
			String user= CommonUtilities.getPropertyValue(Constants.USER_ID);
			String password= CommonUtilities.getPropertyValue(Constants.PASSWORD);
			loginPage.loginWithRemBoxSelect(user, password);
			HomePage homePage= new HomePage(driver);
			homePage.clickUserMenu();
			homePage.selectLogoutLink();
			String actualUserName=loginPage.getUserName();
			
			String expectedUserName=Constants.LOGIN_USERNAME;
			Assert.assertEquals(actualUserName, expectedUserName);
		
		
	}
	
	@Test
	public static void testCase4a()  {
		
		LoginPage loginPage= new LoginPage(driver);
		String user= CommonUtilities.getPropertyValue(Constants.USER_ID);
		String actualTitle=loginPage.clickForgotPassword();
		String expectedTitle= Constants.FORGOT_PAGE_TITLE;
		Assert.assertEquals(actualTitle, expectedTitle);
		
		ForgotPassword forgotPassword= new ForgotPassword(driver);
		forgotPassword.enterUsername(user);
		String actualMsg= forgotPassword.clickContinueButton();
		String expectedMsg= Constants.FORGOT_PASSWORD_MSG;
		Assert.assertNotSame(actualMsg, expectedMsg);
		
		
		}
	
	@Test
	public static void testCase4b() {
		
			LoginPage loginPage= new LoginPage(driver);
			String userName= CommonUtilities.getPropertyValue(Constants.USERID_TESTCASE4B);
			String password= CommonUtilities.getPropertyValue(Constants.PASSWORD_TESTCASE4B);
			loginPage.login(userName, password);
			String actualMsg= loginPage.getErrorMsg();
				
			String expectedMsg=Constants.INVALID_USERNAME_PASSWORD;
			
			Assert.assertEquals(actualMsg, expectedMsg);
		
		
	}
}
